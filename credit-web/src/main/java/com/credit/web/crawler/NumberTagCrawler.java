package com.credit.web.crawler;

import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.credit.common.util.CmdUtils;
import com.credit.common.util.CollectionUtil;
import com.credit.common.util.file.PathUtil;
import com.credit.entity.NumberTag;
import com.credit.enumeration.DemandTypeEnum;

/**
 * Created by wangjunling on 2017/3/24.
 */
public class NumberTagCrawler
{
    private static String SCRIPT_PATH = "";

    private static final String CHARSET = "UTF-8";

    static
    {
        try
        {
            SCRIPT_PATH = PathUtil.getRootPath(NumberTagCrawler.class) + "/script/request_tag360.py";
//            SCRIPT_PATH = "f:/request_tag360.py";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<NumberTag> searchNumberTag(List<String> numberList)
    {
        if (CollectionUtil.isEmpty(numberList))
        {
            return null;
        }
        Set<String> stringSet = new HashSet<>(numberList);
        List<String> resultList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
		try
		{
        for (String s : stringSet)
        {
            stringBuilder.append(s);
            stringBuilder.append(" ");
            if(i%10==0)
            {
				String cmd =String.format("python %s %s", SCRIPT_PATH, stringBuilder.toString());
				stringBuilder = new StringBuilder();
				List<String> strings = CmdUtils.execute(cmd, null, CHARSET);
				resultList.addAll(strings);
			}
            i++;
        }
        if (stringBuilder.length()>0){
			String cmd =String.format("python %s %s", SCRIPT_PATH, stringBuilder.toString());
			List<String> strings = CmdUtils.execute(cmd, null, CHARSET);
			resultList.addAll(strings);
		}
//        String cmd = String.format("python %s %s", SCRIPT_PATH, stringBuilder.toString());

//            List<String> strings = CmdUtils.execute(cmd, null, CHARSET);
            if (resultList.size() <= 0)
            {
                return null;
            }
            List<NumberTag> numberTags = new ArrayList<>();
            for (String string : resultList)
            {
                JSONObject jsonObject = JSON.parseObject(string);
                if (jsonObject != null)
                {
                    for (Map.Entry<String, Object> entry : jsonObject.entrySet())
                    {
                        NumberTag numberTag = new NumberTag();
                        numberTag.setPhoneNumber(entry.getKey());
                        numberTag.setOrgName(entry.getValue().toString());
                        numberTag.setDemandName(DemandTypeEnum.GENERAL.getText());
                        numberTag.setDemandImg(DemandTypeEnum.GENERAL.getImgPath());
                        numberTags.add(numberTag);
                    }
                }
            }

            return numberTags;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static NumberTag searchNumberTag(String number)
    {
        List<NumberTag> numberTags = searchNumberTag(Arrays.asList(number));
        if (numberTags == null || numberTags.size() <= 0)
        {
            return null;
        }
        return numberTags.get(0);
    }

	public static void main(String[] args) {
//		NumberTag numberTag = searchNumberTag("10010");
//		System.out.println(numberTag.getOrgName());
		List<String> strings = Arrays.asList("xx","22","22","22","22","22","22","22","22","22","22","22");
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < strings.size(); i++) {
			stringBuilder.append(strings.get(i));
			stringBuilder.append(" ");
			if(i!=0&&i%10==0){
				System.out.println(stringBuilder.toString());
			}
		}

	}
}
