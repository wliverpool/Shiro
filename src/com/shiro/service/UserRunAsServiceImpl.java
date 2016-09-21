package com.shiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dao.RoleDao;
import com.shiro.dao.UserDao;
import com.shiro.dao.UserRunAsDao;
import com.shiro.pojo.User;

@Service
public class UserRunAsServiceImpl implements UserRunAsService {
	
    @Autowired
    private UserRunAsDao userRunAsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public UserRunAsDao getUserRunAsDao() {
		return userRunAsDao;
	}

	public void setUserRunAsDao(UserRunAsDao userRunAsDao) {
		this.userRunAsDao = userRunAsDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
    public void grantRunAs(Long fromUserId, Long toUserId) {
        userRunAsDao.grantRunAs(fromUserId, toUserId);
    }

    @Override
    public void revokeRunAs(Long fromUserId, Long toUserId) {
        userRunAsDao.revokeRunAs(fromUserId, toUserId);
    }

    @Override
    public boolean exists(Long fromUserId, Long toUserId) {
        return userRunAsDao.exists(fromUserId, toUserId);
    }

    @Override
    public List<Long> findFromUserIds(Long toUserId) {
        return userRunAsDao.findFromUserIds(toUserId);
    }

    @Override
    public List<User> findToUserList() {
    	List<User> users = userDao.findAll();
    	List<User> canChangeToUserList = new ArrayList<User>();
    	if(null!=users&&users.size()>0){
    		for(User user : users){
    			List<Long> roleIds = user.getRoleIds();
    			if(roleIds.contains(1L)){
    				continue;
    			}
    			canChangeToUserList.add(user);
    		}
    	}
        return canChangeToUserList;
    }
}
