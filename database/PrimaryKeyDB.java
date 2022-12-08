package com.bilibili.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * 线程不安全的，带主键索引的数据库。
 */
public class PrimaryKeyDB<K, V extends PrimaryKeyAware<K> & CopyAware<V>> {
	
	/** 数据库对象 */
	protected HashMap<K, V> db = new HashMap<>();

	/** 获取数据 */
	public V findById(K id) {
		V o = db.get(id);
		if (o == null) return null;
		else return o.copy();
	}

	/** 插入数据，插入失败时返回null，否则返回插入的值 */
	public V insert(V value) {
		V result = db.putIfAbsent(value.getKey(), value.copy());
		if(result == null) return value;
		else return null;
	}

	/** 更新数据库，若要更新的 Key 不存在，返回 null */
	public V update(V value) {
		if (exist(value.getKey())) return null;
		return db.put(value.getKey(), value);
	}

	
	public boolean exist(K id) {
		return db.get(id) != null;
	}

	/** 根据 id 移除数据 */
	public V delete(K id) {
		return db.remove(id);
	}

	/**
	 * 获得所有值的列表。
	 */
	public List<V> findAll() {
		Iterator<Entry<K, V>> iter = db.entrySet().iterator();
		List<V> res = new ArrayList<>();
		while(iter.hasNext()) {
			res.add(iter.next().getValue());
		}
		return res;
	}
}
