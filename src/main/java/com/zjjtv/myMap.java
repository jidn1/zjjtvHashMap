package com.zjjtv;

/**   
 * @Copyright © 正经吉
 * @email: zjjtv@gmail.com 
 * @author: zjjtv   
 * @date: 2018年9月21日 上午10:56:58 
 */
public interface myMap<K,V> {

	public V put(K k,V v);
	
	public V get(K k);
	
	public int size();
	
	public interface Entry<K,V>{
		public K getKey();
		
		public V getValue();
		
	}
}
