package com.shiro.service;

import java.util.List;

import com.shiro.pojo.User;

public interface UserRunAsService {

    public void grantRunAs(Long fromUserId, Long toUserId);
    public void revokeRunAs(Long fromUserId, Long toUserId);

    public boolean exists(Long fromUserId, Long toUserId);

    public List<Long> findFromUserIds(Long toUserId);
    /**
     * 获取可以被切换的人员身份列表,除了有admin角色之外的用户都可以被切换
     * @return  可以被切换的人员身份列表
     */
    public List<User> findToUserList();

}
