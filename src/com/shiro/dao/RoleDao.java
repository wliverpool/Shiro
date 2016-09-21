package com.shiro.dao;

import java.util.List;

import com.shiro.pojo.Role;

/**
 * 角色dao
 * @author 吴福明
 *
 */
public interface RoleDao {

	/**
	 * 创建角色
	 * @param role  角色
	 * @return   角色
	 */
	public Role createRole(Role role);

	/**
	 * 删除角色
	 * @param roleId   角色id
	 */
	public void deleteRole(Long roleId);
	
    public Role updateRole(Role role);

    public Role findOne(Long roleId);
    
    public List<Role> findAll();

}
