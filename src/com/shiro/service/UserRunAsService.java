package com.shiro.service;

import java.util.List;

import com.shiro.pojo.User;

public interface UserRunAsService {

    public void grantRunAs(Long fromUserId, Long toUserId);
    public void revokeRunAs(Long fromUserId, Long toUserId);

    public boolean exists(Long fromUserId, Long toUserId);

    public List<Long> findFromUserIds(Long toUserId);
    /**
     * ��ȡ���Ա��л�����Ա����б�,������admin��ɫ֮����û������Ա��л�
     * @return  ���Ա��л�����Ա����б�
     */
    public List<User> findToUserList();

}
