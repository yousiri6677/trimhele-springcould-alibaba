package com.trimhelp.starter.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hrimhelp
 */
public class MapUtil {




	/**
	 * 判断该集合中入参是否传值
	 * @param params 入参
	 * @return true or false
	 */
	public static Boolean isHaveValue( Map<String,Object> params) {

		if(params.isEmpty()){

			return  false;
		}else{

			for(String key:params.keySet()){

				if("limit".equals(key) || "offset".equals(key) || "order".equals(key)){

					continue;
				}

				String value = params.get(key).toString();

				if(StringUtils.isNotBlank(value)){

					return true;
				}

			}
		}

		return false;
	}




	/**
	 * 实体对象转成Map
	 *
	 * @param obj 实体对象
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj) {
		Map<String, Object> map = new LinkedHashMap<>();
		if (obj == null) {
			return map;
		}
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(),field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	/**
	 * 实体对象转成Map   针对  设计
	 * @param obj 实体对象
	 * @return
	 */
	public static LinkedHashMap<String, Object> object2LinkedMap(Object obj) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		if (obj == null) {
			return map;
		}
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (!"transfer_user_id".equals(field.get(obj))&&!"attachName".equals(field.get(obj))&&!"filePath".equals(field.get(obj))&&!"".equals(field.get(obj))&&!"creatTime".equals(field.getName())&&!"isTransfer".equals(field.getName())&&!"serialVersionUID".equals(field.getName())&&!"id".equals(field.getName())&&!"issuePendingId".equals(field.getName())&&null!=field.get(obj)){
					map.put(field.getName(),field.get(obj));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * Map转成实体对象
	 *
	 * @param map   实体对象包含属性
	 * @param clazz 实体对象类型
	 * @return
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
		if (map == null) {
			return null;
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();

			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
