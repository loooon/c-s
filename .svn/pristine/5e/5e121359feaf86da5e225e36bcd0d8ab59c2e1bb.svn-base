package com.credit.web;

import com.credit.common.util.time.TimeUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wangjunling on 2017/3/16.
 */
public class PeriodicTest
{
    @Test
    public void test()
    {
        int[] array = {5, 6, 8, 9};
        int num = array.length;


		double average = getAverage(array, num);
		System.out.println(average);

		double sum = 0;
		for (int i : array) {
			sum += Math.pow(((double) i - average), 2);
		}

		System.out.println(sum);
		double i = sum / (num-1);
		System.out.println(i);
		double sqrt = Math.sqrt(i);
		System.out.println();

		double v = average / sqrt;
		System.out.println(v);

	}

    public double getAverage(int[] array, int num)
    {
        int sum = 0;
        for (int i = 0; i < num; i++)
        {
            sum += array[i];
        }
		System.out.println(sum);
        return (double) (sum / num);
    }

    @Test
	public void test2(){
		String time = "2016-08-01 12:55:20";
		Date parse = TimeUtil.parse(time, TimeUtil.FORMAT_DATE_ONLY);
		System.out.println(TimeUtil.format(parse,TimeUtil.FORMAT_NORMAL));
		System.out.println(parse.getTime());
		Set<Integer> set = new TreeSet<>();
		set.add(3);
		set.add(2);
		System.out.println(set.size());
		Integer[] a = new Integer[set.size()];
		set.toArray(a);
		System.out.println(Arrays.toString(a));
	}
}
