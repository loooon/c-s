package com.credit.web;


import com.credit.common.util.http.HttpUtil;
import org.junit.Test;


public class DunNumberMark {

	public static String uploadCallDetail(final String number) throws Exception {
//		String reqUrl = "http://localhost:8080/service?t=dun_number_mark&vkey=20170314114300123&number=" + number;
		String reqUrl = "http://localhost:7777/service?t=dun_number_mark&vkey=20170314114300123&number=" + number;
//		String reqUrl = "http://localhost:7777/service?t=test&vkey=20170314114300123";
		return HttpUtil.getReq(reqUrl, null);
	}

	public static void main(String[] args) throws Exception {
		long s = System.currentTimeMillis();
		Integer count = 1000;

//			System.out.println(i+"haoren");
//		System.out.println(uploadCallDetail("0951201000,01050871027"));



		for(int i = 0; i < 1000; i++) {

			System.out.println(uploadCallDetail("0951201000,01050871027"));
		}
		long e =System.currentTimeMillis();
		System.out.println((e-s)/count);
		double a = (double) ((e - s)/1000);
		System.out.println("平均"+a);
		// http://192.168.108.149:7777/service?t=dun_number_mark&number=0951201000
//		File sampleFileDir = new File("/Users/sunzhao/Workspace/Source/ContactStat/sample_data/test_mark_sample");
//		Map<String, String> paramMap = null;
//		for (File tmpFile : sampleFileDir.listFiles()) {
//			String fname = tmpFile.getName().replaceAll(".txt", "");
//			String[] cols = fname.split("_");
//			paramMap = new HashMap<String, String>();
//			paramMap.put("phone", cols[0].trim());
//			paramMap.put("name", cols[2].trim());
//			paramMap.put("idcard", cols[1].trim());
//			paramMap.put("imei", "");
//			paramMap.put("imsi", "");
//			paramMap.put("vkey", "20170314114300123");
//			paramMap.put("contacts", "");
//			paramMap.put("apply_date", "");
//			paramMap.put("call_details", "");
//			paramMap.put("file_charset", "");
//			System.out.println(uploadCallDetail(paramMap, tmpFile));
//		}
	}

}
