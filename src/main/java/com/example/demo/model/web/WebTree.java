package com.example.demo.model.web;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.Tree;

public class WebTree extends Tree implements Serializable {
	private static final long serialVersionUID = 8319537054285253678L;
	private List<WebTree> children;

    

    public List<WebTree> getChildren() {
		return children;
	}



	public void setChildren(List<WebTree> children) {
		this.children = children;
	}



	@Override
    public String toString() {
		return super.toString()+"children:"+children;
    }
}