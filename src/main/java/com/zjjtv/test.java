package com.zjjtv;

import java.util.HashMap;
import java.util.Map;

public class test {

	public static void main(String[] args) {
		Map< String, String> ma = new HashMap<String, String>();
		myMap<String,String> map= new myHashMap<String,String>();
		Long t = System.currentTimeMillis();
		for(int i = 0; i< 1000;i++){
			map.put("key"+i, "value"+i);
		}
		
		for(int i = 0; i< 1000;i++){
			System.out.println("key: " + "key" + i + "  value: " +map.get("key"+i));
		}
		Long t2 = System.currentTimeMillis();
		System.out.println("zjjtv Map 耗时时间为： "+(t2-t));
		System.out.println("=====================HashMap=================");
		Long t3 = System.currentTimeMillis();
		for(int i = 0; i< 1000;i++){
			ma.put("key"+i, "value"+i);
		}
		
		for(int i = 0; i< 1000;i++){
			System.out.println("key: " + "key" + i + "  value: " +ma.get("key"+i));
		}
		Long t4 = System.currentTimeMillis();
		System.out.println("JDK Map 耗时时间为： "+(t4-t3));
	}
	
}
