package com.credit.web.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.credit.web.model.ContactCheck;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by wangjunling on 2017/3/14.
 */
public class ContactCheckUtil
{
	public static void readContacts(List<ContactCheck> contactCheckList, String contactsJson) throws Exception
	{
		if (StringUtils.isNotBlank(contactsJson))
		{
			List<JSONObject> jsonObjectList = JSONArray.parseArray(contactsJson, JSONObject.class);
			for (JSONObject jsonObject : jsonObjectList)
			{
				if (contactCheckList.size() > 5)
				{
					break;
				}
				ContactCheck contactCheck = new ContactCheck();
				String contactNumber = jsonObject.getString("contact_number");
				if (StringUtils.isBlank(contactNumber))
				{
					continue;
				}
				contactCheck.setName(jsonObject.getString("contact_name"));
				contactCheck.setNumber(contactNumber);
				contactCheck.setRelation(jsonObject.getString("relation"));
				contactCheckList.add(contactCheck);
			}

		}
	}
}
