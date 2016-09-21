package com.shiro.service;

import java.util.List;

import com.shiro.pojo.Organization;

/**
 * ����service
 * @author  �⸣��
 */
public interface OrganizationService {

	public Organization createOrganization(Organization organization);

	public Organization updateOrganization(Organization organization);

	public void deleteOrganization(Long organizationId);

	Organization findOne(Long organizationId);

	List<Organization> findAll();

	Object findAllWithExclude(Organization excludeOraganization);

	void move(Organization source, Organization target);
}