package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tree;
import com.example.demo.model.web.WebTree;
import com.example.demo.service.TreeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin({"http://test1.com:8084","http://localhost:8080","http://127.0.0.1:8084","*"})
//@CrossOrigin({"*"})
@RestController
@RequestMapping(value="tree",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TreeController {

	@Autowired
	private TreeService treeService;
	
	@ApiOperation(value="查询菜单",notes="递归查询菜单")
	@RequestMapping(value="query",method=RequestMethod.GET)
	public List<WebTree> queryTree(@CookieValue(value="test1",required=false)String cookie){
		String name = Thread.currentThread().getName();
//		int a=1/0;
		System.out.println(name);
		System.err.println(cookie);
		return treeService.queryTree();
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ApiOperation(value="增加节点",notes="在选择的位置下增加节点，可选择增加同层级的节点，位置为选中节点下添加。</br>增加选中节点的子节点，在选中节点中添加一个排序第一的子节点。")
	public String addTree(@RequestBody Tree tree){
		return treeService.addTree(tree);
	}
	
	@ApiOperation(value="树修改",notes="树修改，包含拖拉拽的位置修改")
	@RequestMapping(value="modify",method=RequestMethod.PUT)
	public String modifyTree(@RequestBody Tree tree){
		if(tree.getId()==null)
			return "needId";
		return treeService.modifyTree(tree);
	}
	
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	@ApiOperation(value="节点删除",notes="同时删除节点下的所有节点。")
	public String deleteTree(@RequestBody Integer id){
		return treeService.deleteTree(id);
	}
	@RequestMapping(value="redirect",method=RequestMethod.GET)
	@ApiOperation(value="测试重定向",notes="测试重定向")
	public void deleteTree(HttpServletResponse response) throws IOException{
//		response.sendRedirect("https://www.baidu.com/");
//		response.sendRedirect("http://localhost:8080/tree#/tree/query");
		response.sendRedirect("http://test1.com:8084/tree/query?id=1"); 
		
//		response.CrossOrigin
//		return null;
	}
	
	
}
