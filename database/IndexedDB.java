package com.bilibili.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bilibili.util.Logger;
import com.bilibili.util.LoggerFactory;

import java.util.List;

/**
 * 线程不安全的，带若干自定义索引和主键索引的数据库。
 */
public class IndexedDB<K, V extends PrimaryKeyAware<K> & CopyAware<V>> extends PrimaryKeyDB<K, V> {

	private static final Logger LOG = LoggerFactory.getLogger(IndexedDB.class);
	
	/**
	 * <p> 用列表模拟数据库 B+树 索引。<code> HashMap[索引字段名，Map[对象，List[主键]]]</code>。</p>
	 * <p> 在 MySQL 中，B+ 树索引是通过 Value 找 Key 列表。 </p>
	 * <p> 理解：HashMap<索引名，Map<对象，List<主键>>> </p>
	 */
	private HashMap<String, Map<Object, List<K>>> indexMap;

	/**
	 * 传入索引名，构建索引列表。
	 */
	public IndexedDB(String... indexNames) {
		indexMap = new HashMap<>();
		for(String s: indexNames) {
			indexMap.put(s, new HashMap<Object, List<K>>());
		}
	}


	/**
	 * 插入的同时还插入索引
	 */
	@Override
	public V insert(V value) {
		value = value.copy();	// 保证数据库中的对象与外界是独立的
		V result = null;
		result = super.insert(value);
		for(String s: indexMap.keySet()) {
			// 反射获取属性
			Object v = getFieldValue(value, s);
			
			Map<Object, List<K>> index = indexMap.get(s);
			List<K> m;
			if((m = index.get(v)) == null) {
				// 首次插入，插入一个空数组
				m = new ArrayList<>();
				index.put(v, m);
			}
			// 然后再插入 K
			m.add(value.getKey());
		}
		return result;
	}

	/**
	 * 删除主键和索引。
	 */
	@Override
	public V delete(K id) {
		V result = super.delete(id);
		if (result != null) {
			// 删除索引 
			for(String s: indexMap.keySet()) {
				Object v = null;
				v = getFieldValue(result, s);
				List<K> li = indexMap.get(s).get(v);
				if(li!=null && li.size() > 0) {
					li.remove(id);
				} else {
					indexMap.get(s).remove(result);
				}
			}
		}
		return result;
	}

	/**
	 * 更新值。
	 */
	@Override
	public V update(V value) {
		delete(value.getKey());
		return insert(value);
	}

	/**
	 * 根据索引名和值找出 key 列表
	 */
	protected List<K> findKeyListByIndex(String indexName, Object value) {
		return indexMap.get(indexName).get(value);
	}

	/**
	 * 用索引检索值相等的对象
	 */
	public List<V> findAllByIndex(String indexName, Object value) {
		List<V> result = new ArrayList<>();
		for(K key : this.findKeyListByIndex(indexName, value)) {
			result.add(this.findById(key));
		}
		return result;
	}

	/**
	 * 反射获取某对象的特定属性值。若给定的字段不合法，将导致致命错误退出。
	 */
	private Object getFieldValue(Object target, String fieldName) {
		try {
			Class<?> tmpclazz = target.getClass();
			Field f = null;
			boolean found = false;
			while(tmpclazz != Object.class) {
				try {
					f = tmpclazz.getDeclaredField(fieldName);
					found = true;
					break;
				} catch (NoSuchFieldException e) {
					tmpclazz = tmpclazz.getSuperclass();
				}
			}
			if (!found) {
				LOG.ERROR("Critial! Index name not found!");
				System.exit(-1);
			}
			f.setAccessible(true);
			return f.get(target);
		} catch(Exception e) {
			LOG.ERROR("Critial! Index name not found!");
			System.exit(-1);
		}
		return null;
	}
}
