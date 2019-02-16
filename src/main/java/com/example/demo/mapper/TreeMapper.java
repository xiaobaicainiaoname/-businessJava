package com.example.demo.mapper;

import com.example.demo.model.Tree;
import com.example.demo.model.web.WebTree;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TreeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tree record);
    /**
     * 更新不为空的字段
     * @param id
     * @return
     */
    Tree selectByPrimaryKey(Integer id);
    
    List<WebTree> selectByParentId(Integer parentId);

    List<Tree> selectAll();

    int updateByPrimaryKey(Tree record);

	void updateSortByParentId(@Param("parentId")Integer parentId, 
			@Param("minSort")Integer minSort, @Param("maxSort")Integer maxSort, @Param("move")int move);

	void updateSortByAsyncParentId(@Param("parentId")Integer parentId, 
			@Param("sort")Integer sort,  @Param("move")int move);
	
}