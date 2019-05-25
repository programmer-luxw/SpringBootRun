package cn.luxw.app.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;

/**
 *第三方请求
 * @author luxiaowen
 * @date 2019/3/22
 */
public class JsonUtil {


    private static ObjectMapper om = new ObjectMapper();


    public static String objToString(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        	Throwables.throwIfUnchecked(e);
        }
        return null;
    }

    public static <T> T parseObj(String json, Class<T> clazz) {
        try {
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om.readValue(json.getBytes(), clazz);
        } catch (IOException e) {
        	Throwables.throwIfUnchecked(e);
        }
        return null;
    }

    public static <T> T mapToObj(Map map, Class<T> clazz) {
        return parseObj(objToString(map), clazz);
    }

    /**Alibaba*/
    private static final SerializerFeature[] features = {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteDateUseDateFormat
    };
    public static String toString(Object object) {
        return JSON.toJSONString(object, features);
    }
    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }
    public static <T> List<T> toList(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

}