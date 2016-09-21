package com.shiro.pojo;

import java.io.Serializable;

/**
 * �л����pojo
 * @author �⸣��
 *
 */
public class UserRunAs implements Serializable {
	
	private static final long serialVersionUID = 7641761480348130606L;
	
	private Long fromUserId;//��������ʺ�
    private Long toUserId;//����������ʺ�

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRunAs userRunAs = (UserRunAs) o;

        if (fromUserId != null ? !fromUserId.equals(userRunAs.fromUserId) : userRunAs.fromUserId != null) return false;
        if (toUserId != null ? !toUserId.equals(userRunAs.toUserId) : userRunAs.toUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromUserId != null ? fromUserId.hashCode() : 0;
        result = 31 * result + (toUserId != null ? toUserId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRunAs{" +
                "fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                '}';
    }
}