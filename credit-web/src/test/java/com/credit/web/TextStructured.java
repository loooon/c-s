package com.credit.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.credit.common.util.http.HttpUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



public class TextStructured {
	
	/**
	 * 上传文本
	 * */
	public static String uploadText(final String text, final File textFile) throws Exception {
		Map<String, File> fileMap = null;
		if(textFile != null) {
			fileMap = new HashMap<String, File>();
			fileMap.put("text", textFile);
		}
		Map<String, String> paramMap = null;
		if(text != null && text.trim().length() > 0) {
			paramMap = new HashMap<String, String>();
			paramMap.put("text", "【易到】-车辆到达-松乘车，(白)现代朗动，粤L52L17，韩师傅，电话15919413239。免费等待10分钟。");	
		}
		String respResult = HttpUtil.postReq("http://192.168.108.103:5000/sms/parse", paramMap, fileMap);
		JSONObject jsonObject = JSON.parseObject(respResult);
		return jsonObject.getString("data");
	}
	
	/**
	 * 上传文本
	 * */
	public static String structuredResult(final String tradeNo) throws Exception {
		if(tradeNo == null || tradeNo.trim().length() == 0) {
			throw new Exception("交易编号不能为空");
		}
		return HttpUtil.getReq("http://192.168.108.103:5000/sms/task/" + tradeNo + "/json", null);
	}
	
	public static void main(String[] args) throws Exception {
		long totalTime = 0l;
		int count = 1000;
		for (int idx = 0; idx < count; idx++) {
			// 上传短信
			String text = "【易到】-车辆到达-松乘车，(白)现代朗动，粤L52L17，韩师傅，电话15919413239。免费等待10分钟。";
			File textFile = new File("/Users/sunzhao/sms_sample.txt");
			String tradeNo = uploadText(text, textFile);
			long start = System.currentTimeMillis();
			while(true) {
				// 获取结构化结果
				String respResult = structuredResult(tradeNo);
				JSONObject jsonObject = JSON.parseObject(respResult);
				if(jsonObject.getString("message").equals("任务尚在处理中")) {
					continue;
				}
				long end = System.currentTimeMillis();
				//System.out.println("耗时:" + (end - start) + "\t" + respResult);
				System.out.println("耗时:" + (end - start));
				totalTime += (end - start);
				break;
			}
		}
		System.out.println("总耗时:" + totalTime + "\t平均每条:" + (totalTime / count));
	}
}
