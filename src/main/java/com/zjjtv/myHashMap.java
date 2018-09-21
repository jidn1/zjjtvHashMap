package com.zjjtv;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Copyright © 正经吉
 * @email: zjjtv@gmail.com 
 * @author: zjjtv   
 * @date: 2018年9月21日 上午10:56:16 
 */
public class myHashMap<K,V> implements myMap<K, V>  {

	private static int defaultLength = 16; //hashMap 的默认长度是 16
	//就是一个警戒值 
	private static double defaultLoader = 0.75;//hashmap 的默认负载因子是 0.75
	
	private Entry<K,V>[] table = null;
	
	private int size = 0;
	
	@SuppressWarnings("unchecked")
	public myHashMap(int length,double loader){
		defaultLength = length;
		defaultLoader = loader;
		table = new Entry[defaultLength];
	}
	
	public myHashMap(){
		this(defaultLength,defaultLoader);
	}
	
	public V put(K k, V v) {
		//在这里要判断一个size是否达到了一个扩容的标准
		if(size >= defaultLength * defaultLoader){
			up2size();
		}
		// 1 创建一个hash函数 根据key和hash函数算出数组下标
		int index = getIndext(k);
		Entry<K,V> entry = table[index];
		if(entry == null){
			//如果entry为null 说明table的index位置上没有元素 （如果没有数据这个 entry指针是空的）
			table[index] = newEntry(k,v,null);
			size++;
		}else{
			//如果有数据  就把 entry 拿到的老数据 放进这个新的 newEntry里面  （有数据就把指针指向老的数据）
			//如果indext位置不为空 说明indext位置有元素 那么就要进行一个替换，然后next指针指向老的数据
			table[index] = newEntry(k,v,entry);
		}
		return table[index].getValue();
	}
	
	@SuppressWarnings("unchecked")
	private void up2size(){//扩容是默认长度的两倍
		Entry<K,V>[] newTable = new Entry[2 * defaultLength];
		
		//新创建数组以后 以前老数组里面的元素要对新数组进行再散列
		againHash(newTable);
	}
	
	private void againHash(Entry<K,V>[] newTable){
		List<Entry<K,V>> list = new ArrayList<Entry<K,V>>();
		
		for(int i=0 ; i<table.length;i++){
			if(table[i] == null){
				continue;
			}
			findEntryByNext(table[i],list);
		}
		//查看list是否已经拿到老table里面的数据
		if(list.size() > 0){
			//要进行一个新数组的在散列
			size = 0;
			defaultLength = defaultLength * 2;
			table = newTable;
			for(Entry<K,V> entry : list){
				if(entry.next != null){
					entry.next = null;
				}
				put(entry.getKey(),entry.getValue());
			}
		}
		
	}
	
	private void findEntryByNext(Entry<K,V> entry,List<Entry<K,V>> list){
		//判断entry的指针是否为空 如果不为空 进行递归操作
		if(entry != null && entry.next != null){
			list.add(entry);
			findEntryByNext(entry.next, list);
		}else{
			list.add(entry);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Entry<K,V> newEntry(K k,V v,Entry<K,V> next){
		return new Entry(k,v,next);
	} 
	
	private int getIndext(K k){
		int m = defaultLength;
		int index = k.hashCode() % m;
		
		//这里用到三元表达式  主要是应为这个 k在对m取模时  这个index 可能会为 负数
		return index >=0 ? index : -index;
	}

	public V get(K k) {
		// 1 创建一个hash函数 根据key和hash函数算出数组下标
		int indext = getIndext(k);
		
		if(table[indext] == null){
			return null;
		}
		return findValueByEqualKey(k,table[indext]);
	}
	
	public V findValueByEqualKey(K k,Entry<K,V> entry){
		
		if(k == entry.getKey() || k.equals(entry.getKey())){
		 return entry.getValue();
		}else{
			if(entry.next != null){
				return findValueByEqualKey(k, entry.next);
			}
		}
		
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}


@SuppressWarnings("hiding")
class Entry<K, V> implements myMap.Entry<K, V>{
	
	K k;
	
	V v;
	
	Entry<K,V> next;
	
	public Entry(K k,V v,Entry<K, V > next){
		this.k = k;
		this.v = v;
		this.next = next;
	}
	
	public K getKey() {
		return k;
	}

	public V getValue() {
		return v;
	}
	
}	
	
}
