# zjjtvHashMap
 
自己 动手写一个HashMap

 
```java
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
```
 
结果 如下：
```java
key: key998  value: value998
key: key999  value: value999
zjjtv Map 耗时时间为： 153
=====================HashMap=================

key: key998  value: value998
key: key999  value: value999
JDK Map 耗时时间为： 246
```
