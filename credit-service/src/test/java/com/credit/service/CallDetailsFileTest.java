package com.credit.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangjunling on 2017/3/13.
 */
public class CallDetailsFileTest
{
	public static void main(String[] args) {
		File file = new File("D:\\IdeaProject\\credit-site\\credit-service\\src\\test\\resources\\13001103239.txt");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			List<String> strings = IOUtils.readLines(fileInputStream);
			List<String> newStrings = new ArrayList<>(strings.size());
			FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\IdeaProject\\credit-site\\credit-service\\src\\test\\resources\\calldetails.txt"));
			for (String string : strings) {
				String[] split = string.split("\t");
				split = Arrays.copyOfRange(split,1,8);
				for (String s : split) {
					IOUtils.write(s+"\t",fileOutputStream,"utf-8");
				}
				IOUtils.write("\r\n",fileOutputStream);
			}
			System.out.println(newStrings);


			IOUtils.closeQuietly(fileInputStream);
			IOUtils.closeQuietly(fileOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testReadFile()
	{
		File file = new File("D:\\IdeaProject\\credit-site\\credit-service\\src\\test\\resources\\calldetails.txt");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			List<String> strings = IOUtils.readLines(fileInputStream,"utf-8");

			for (String string : strings) {

				System.out.println(string);
			}
			IOUtils.closeQuietly(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
