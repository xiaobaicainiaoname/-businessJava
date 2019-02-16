package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Tree;
import com.example.demo.model.web.WebTree;

public interface TreeService {

	List<WebTree> queryTree();

	String addTree(Tree tree);

	String modifyTree(Tree tree);

	String deleteTree(Integer id);

}
