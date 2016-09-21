package com.shiro.permission;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * 自定义实现位移方式的权限 权限字符串格式：+资源字符串+权限位+实例ID；以+开头中间通过+分割；
 * 权限：0 表示所有权限；1 新增（二进制：0001）、2 修改（二进制：0010）、4 删除（二进制：0100）、 8 查看（二进制：1000）；
 * 如+user+10表示对资源user拥有修改/查看权限
 * 
 * @author 吴福明
 * 
 */
public class BitPermission implements Permission {

	private String resourceIdentify;
	private int permissionBit;
	private String instanceId;

	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");
		//获取资源id,如果资源id为空则此资源权限可针对任何资源使用,并设置资源id为*
		if (array.length > 1) {
			resourceIdentify = array[1];
		}
		if (StringUtils.isEmpty(resourceIdentify)) {
			resourceIdentify = "*";
		}
		//获取权限位
		if (array.length > 2) {
			permissionBit = Integer.valueOf(array[2]);
		}
		//获取实例id,如果实例id为空则此资源权限可针对任何实例使用,并设置实例id为*
		if (array.length > 3) {
			instanceId = array[3];
		}
		if (StringUtils.isEmpty(instanceId)) {
			instanceId = "*";
		}
	}

	/*
	 * 判断有无权限
	 * @see org.apache.shiro.authz.Permission#implies(org.apache.shiro.authz.Permission)
	 */
	@Override
	public boolean implies(Permission p) {
		//如果权限p不是BitPermission类的实例时,返回没有权限
		if (!(p instanceof BitPermission)) {
			return false;
		}
		BitPermission other = (BitPermission) p;
		//资源id不为*或者当前资源id不等于被比较的权限p的资源id时,返回没有权限
		if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
			return false;
		}
		//权限为不为0或者当前资源的权限位与被比较资源的权限位做&运算的结果不为0时,返回没有权限
		if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}
		//实例id不为*或者当前实例id不等于被比较的权限p的实例id时,返回没有权限
		if (!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			return false;
		}
		return true;
	}

}
