package com.trimhelp.starter.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	public static Object sendReturn(int code,String message){
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("message",message);
		return json;
	}

	public static Object success(Object data){
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("data",data);
		return json;
	}

	public static Object success(String msg){
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg",msg);
		return json;
	}

	public static Object fail(String msg){
		JSONObject json = new JSONObject();
		json.put("code", 500);
		json.put("msg",msg);
		return json;
	}

	public static Object fail(String code, String msg){
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("msg",msg);
		return json;
	}

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串。
	 * <p>Title: pojoToJson</p>
	 * <p>Description: </p>
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			logger.error("cn.stylefeng.guns.core.util.JsonUtil.objectToJson",e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 *
	 * @param jsonData json数据
	 * @param beanType 对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			logger.error("cn.stylefeng.guns.core.util.JsonUtil.jsonToPojo",e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * <p>Title: jsonToList</p>
	 * <p>Description: </p>
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			logger.error("cn.stylefeng.guns.core.util.JsonUtil.jsonToList",e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * json字符串转化为数组
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> jsonToListMap(Object json) {
		JSONArray jsonArr = (JSONArray) json;
		List<Map<String, Object>> arrList = new ArrayList<>();
		for (int i = 0; i < jsonArr.size(); ++i) {
			arrList.add(strJson2Map(jsonArr.getString(i)));
		}
		return arrList;
	}

	/**
	 * json字符串转化为Map
	 *
	 * @param json
	 * @return
	 */
	public static Map<String, Object> strJson2Map(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		Map<String, Object> resMap = new HashMap<>(16);
		Iterator<Map.Entry<String, Object>> it = jsonObject.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> param = (Map.Entry<String, Object>) it.next();
			if (param.getValue() instanceof JSONObject) {
				resMap.put(param.getKey(), strJson2Map(param.getValue().toString()));
			} else if (param.getValue() instanceof JSONArray) {
				resMap.put(param.getKey(), jsonToListMap(param.getValue()));
			} else {
				resMap.put(param.getKey().replaceAll("\"", ""),
						JSONObject.toJSONString(param.getValue(), SerializerFeature.WriteClassName).replaceAll("\"", ""));
			}
		}
		return resMap;
	}

}
