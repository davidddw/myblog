package org.tcloud.support;

import java.util.Date;

public class Test {
	public static void main(String[] args) {
		java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyy/MM/dd");
        String s = format2.format(new Date());
        System.out.println(s); 
//		/timedir= datetime.datetime.now().strftime('%Y-%m-%d')
	}
}
