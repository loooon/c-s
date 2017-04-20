package com.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.credit.repository.SiteResourceRepository;
import org.springframework.stereotype.Service;

import com.credit.entity.SiteResource;
import com.credit.entity.UserPermission;
import com.credit.repository.UserPermissionRepository;
import com.credit.service.UserPermissionService;

/**
 * Created by Michael Chan on 3/31/2017.
 */
@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService<UserPermission>
{
    @Resource
    private UserPermissionRepository userPermissionRepository;

    @Resource
	private SiteResourceRepository siteResourceRepository;

    @Override
    public List<UserPermission> searchPermissionByVKey(String vKey)
    {
        return userPermissionRepository.findByVKey(vKey);
    }

    @Override
    public SiteResource searchSiteResourceByVkeyAndPath(String vKey, String path)
    {
        return siteResourceRepository.findByVkeyAndResourcePath(vKey,path);
    }

    @Override
    public SiteResource searchSiteResourceByPath(String resourcePath)
    {
        return siteResourceRepository.findByResourcePath(resourcePath);
    }
}
