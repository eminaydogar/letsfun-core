package com.easoft.letsfun.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.easoft.letsfun.common.CoreQueryService;
import com.easoft.letsfun.entity.resultset.ObjectValue;
import com.easoft.letsfun.util.ObjectUtilty;

public class CacheManager {

	private static Map<Long, ObjectValue> cacheObjectMap = new HashMap<>();


	private CacheManager() {

	}

	public static void load() {
		if (ObjectUtilty.isEmpty(cacheObjectMap)) {
			List<ObjectValue> itemList = CoreQueryService
					.findListByNativeQuery("select * from object_value where status=?", ObjectValue.class, 'Y');
			if (itemList != null && itemList.size() > 0) {
				for (ObjectValue item : itemList) {
					cacheObjectMap.put(item.getId(), item);
				}
			}
		}
	}

	public static void addItem(Long id, ObjectValue item) {
		cacheObjectMap.put(id, item);
	}

	public static void addItem(ObjectValue item) {
		cacheObjectMap.put(item.getId(), item);
	}

	public static ObjectValue getItemById(Long id) {
		return cacheObjectMap.get(id);
	}

	public static ObjectValue getItemByObjectCode(String objectCode) {
		for (Entry<Long, ObjectValue> entry : cacheObjectMap.entrySet()) {
			if (entry.getValue().getObjectCode().equalsIgnoreCase(objectCode)) {
				return entry.getValue();
			}

		}
		return null;
	}

	public static List<ObjectValue> getItemsByObjectType(String objectCode) {
		List<ObjectValue> itemList = new ArrayList<>();
		for (Entry<Long, ObjectValue> entry : cacheObjectMap.entrySet()) {
			if (entry.getValue().getObjectType().equalsIgnoreCase(objectCode)) {
				itemList.add(entry.getValue());
			}

		}
		return itemList;
	}

	public static void refresh(Map<Long, ObjectValue> newCacheObjectMap) {
		cacheObjectMap = newCacheObjectMap;
	}

	public static void refresh() {
		cacheObjectMap.clear();
		List<ObjectValue> itemList = CoreQueryService
				.findListByNativeQuery("select * from object_value where status=?", ObjectValue.class, 'Y');
		if (itemList != null && itemList.size() > 0) {
			for (ObjectValue item : itemList) {
				cacheObjectMap.put(item.getId(), item);
			}
		}

	}


}
