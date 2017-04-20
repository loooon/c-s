package com.credit.web;

import com.credit.common.util.RandomUtil;
import org.junit.Test;

/**
 * Created by wangjunling on 2017/3/24.
 */
public class DunProviceTest
{
    static String[] arrFrom = new String[]{"河北", "广东", "辽宁", "浙江", "黑龙江", "江苏", "四川", "宁夏", "福建", "陕西", "吉林", "北京",
            "湖北", "上海", "山东", "甘肃", "山西", "安徽", "天津", "河南", "云南", "重庆", "湖南", "海南", "贵州", "西藏", "青海", "江西", "广西", "新疆",
            "内蒙古"};



    @Test
	public void testSql()
	{
		String sql = "insert into tb_dun_number_dist(user_phone,user_phone_province,call_from_province,from_province_number) values ('','%s','%s',%d);";
		for (int i = 0; i < arrFrom.length; i++) {
			for (int j = i+1; j < arrFrom.length; j++) {
				String format = String.format(sql, arrFrom[i], arrFrom[j], RandomUtil.genRandomNumber(10, 100));
				String format2 = String.format(sql, arrFrom[j], arrFrom[i], RandomUtil.genRandomNumber(10, 100));
				System.out.println(format);
				System.out.println(format2);
			}
		}
	}
}
