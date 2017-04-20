package com.credit.service;

import com.credit.entity.SiteResource;
import com.credit.entity.UserPermission;

import java.util.List;

/**
 * Created by Michael Chan on 3/31/2017.
 */
public interface UserPermissionService<T extends UserPermission>
{
    List<UserPermission> searchPermissionByVKey(String vKey);

	SiteResource searchSiteResourceByVkeyAndPath(String vKeyValues, String requestUri);

    SiteResource searchSiteResourceByPath(String requestUri);
}
