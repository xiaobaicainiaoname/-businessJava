package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.TreeMapper;
import com.example.demo.model.Tree;
import com.example.demo.model.web.WebTree;
import com.example.demo.service.TreeService;
@Service
public class TreeServiceImpl implements TreeService {
	@Autowired
	private TreeMapper treeMapper;
	
	@Override
	public List<WebTree> queryTree() {
		List<WebTree> tree = createTree(0);
		return tree;
	}
	//递归
	private List<WebTree> createTree(Integer parentId) {
		List<WebTree> list = treeMapper.selectByParentId(parentId);
		for (WebTree webTree : list) {
			List<WebTree> createTree = createTree(webTree.getId());
			webTree.setChildren(createTree);
		}
		return list;
	}

	//将新增加的数据排行
	@Override
	public String addTree(Tree tree) {
		//移动
		treeMapper.updateSortByAsyncParentId(tree.getParentId(),tree.getSort(),1);
		treeMapper.insert(tree);
		return "ok";
	}
	
	private void moveTree(Tree newTree) {
		//获取旧的位置
		Tree oldTree = treeMapper.selectByPrimaryKey(newTree.getId());
		//比较是否同级
		if(newTree.getParentId().equals(oldTree.getParentId())) {
			//上移还是下移
			if(newTree.getSort()>oldTree.getSort()) {//下移
				//更新位置
				treeMapper.updateSortByParentId(newTree.getParentId(),newTree.getSort(),oldTree.getSort(),-1);
			}else {
				//更新位置
				treeMapper.updateSortByParentId(newTree.getParentId(),newTree.getSort(),oldTree.getSort(),1);
			}
		}else {//跨级
			treeMapper.updateSortByAsyncParentId(newTree.getParentId(),newTree.getSort(),1);
			treeMapper.updateSortByAsyncParentId(oldTree.getParentId(),oldTree.getSort(),-1);
		}//跟新自己的最终位置在各个调用方法中执行
	}

	/**
	 * 修改和和拖拉拽用同样的方法
	 */
	@Override
	@Transactional
	public String modifyTree(Tree tree) {
		if(tree.getSort()!=null)//是否需要位移
			moveTree(tree);
		//跟新自己，位移和非位移两种情况做集中更新，
		treeMapper.updateByPrimaryKey(tree);
		return "ok";
	}

	/**
	 * 删除需要删除所有的子节点
	 */
	@Override
	@Transactional
	public String deleteTree(Integer id) {
		Tree tree = treeMapper.selectByPrimaryKey(id);
		//删除所有子节点 递归
		deleteChildTree(id);
		//删除自己
		treeMapper.deleteByPrimaryKey(id);
		
		//移动排序
		treeMapper.updateSortByAsyncParentId(tree.getParentId(),tree.getSort(),-1);
		return "ok";
	}
	private void deleteChildTree(Integer id) {
		List<WebTree> childrens = treeMapper.selectByParentId(id);
		for (WebTree webTree : childrens) {
			deleteChildTree(webTree.getId());
			treeMapper.deleteByPrimaryKey(webTree.getId());
		}
	}
	
}
