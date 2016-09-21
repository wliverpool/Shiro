package com.shiro.dao;

import java.util.List;

import com.shiro.pojo.Resource;

/**
 * ×ÊÔ´dao
 * @author   Îâ¸£Ã÷
 */
public interface ResourceDao {

	public Resource createResource(Resource resource);

	public Resource updateResource(Resource resource);

	public void deleteResource(Long resourceId);

	Resource findOne(Long resourceId);

	List<Resource> findAll();

}