package com.credit.repository;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.SiteResource;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Michael Chan on 3/31/2017.
 */
public interface SiteResourceRepository extends JpaBaseRepository<SiteResource,Integer>
{
	@Query("from SiteResource sr where sr.resourcePath = ?2 and sr.resourceId in (select up.resourceId from UserPermission up where up.vKey= ?1 )")
	SiteResource findByVkeyAndResourcePath(String vKey, String path);

    SiteResource findByResourcePath(String resourcePath);
}
