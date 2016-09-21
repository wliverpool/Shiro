package com.shiro.permission;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * �Զ���ʵ��λ�Ʒ�ʽ��Ȩ�� Ȩ���ַ�����ʽ��+��Դ�ַ���+Ȩ��λ+ʵ��ID����+��ͷ�м�ͨ��+�ָ
 * Ȩ�ޣ�0 ��ʾ����Ȩ�ޣ�1 �����������ƣ�0001����2 �޸ģ������ƣ�0010����4 ɾ���������ƣ�0100���� 8 �鿴�������ƣ�1000����
 * ��+user+10��ʾ����Դuserӵ���޸�/�鿴Ȩ��
 * 
 * @author �⸣��
 * 
 */
public class BitPermission implements Permission {

	private String resourceIdentify;
	private int permissionBit;
	private String instanceId;

	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");
		//��ȡ��Դid,�����ԴidΪ�������ԴȨ�޿�����κ���Դʹ��,��������ԴidΪ*
		if (array.length > 1) {
			resourceIdentify = array[1];
		}
		if (StringUtils.isEmpty(resourceIdentify)) {
			resourceIdentify = "*";
		}
		//��ȡȨ��λ
		if (array.length > 2) {
			permissionBit = Integer.valueOf(array[2]);
		}
		//��ȡʵ��id,���ʵ��idΪ�������ԴȨ�޿�����κ�ʵ��ʹ��,������ʵ��idΪ*
		if (array.length > 3) {
			instanceId = array[3];
		}
		if (StringUtils.isEmpty(instanceId)) {
			instanceId = "*";
		}
	}

	/*
	 * �ж�����Ȩ��
	 * @see org.apache.shiro.authz.Permission#implies(org.apache.shiro.authz.Permission)
	 */
	@Override
	public boolean implies(Permission p) {
		//���Ȩ��p����BitPermission���ʵ��ʱ,����û��Ȩ��
		if (!(p instanceof BitPermission)) {
			return false;
		}
		BitPermission other = (BitPermission) p;
		//��Դid��Ϊ*���ߵ�ǰ��Դid�����ڱ��Ƚϵ�Ȩ��p����Դidʱ,����û��Ȩ��
		if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
			return false;
		}
		//Ȩ��Ϊ��Ϊ0���ߵ�ǰ��Դ��Ȩ��λ�뱻�Ƚ���Դ��Ȩ��λ��&����Ľ����Ϊ0ʱ,����û��Ȩ��
		if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}
		//ʵ��id��Ϊ*���ߵ�ǰʵ��id�����ڱ��Ƚϵ�Ȩ��p��ʵ��idʱ,����û��Ȩ��
		if (!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			return false;
		}
		return true;
	}

}
