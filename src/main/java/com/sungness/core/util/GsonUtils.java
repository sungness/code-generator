package com.sungness.core.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GsonUtils {
    /**
     * 将给定类型的对象转换城json字符串
     * @param <T> 对象类型
     * @param obj T 指定类型的对象
     * @return String json字符串
     */
    public static <T> String toJson(T obj) {
        return toJson(obj, null, null);
    }

    /**
     * 将给定类型的对象转换城json字符串
     * @param <T> 对象类型
     * @param obj T 指定类型的对象
     * @param pretty boolean 是否格式化输出，true-格式化，false-不格式化
     * @return String json字符串
     */
    public static <T> String toJson(T obj, boolean pretty) {
        return toJson(obj, null, null, pretty);
    }

    /**
     * 将对象转换成json字符串 使用相应的排除策略
     * @param obj T 源对象
     * @param strategy ExclusionStrategy 排除字段策略
     * @param <T> 目标对象类型
     * @return String json字符串
     */
    public static <T> String toJson(T obj, ExclusionStrategy strategy) {
        return toJson(obj, strategy, null);
    }

    /**
     * 将对象转换成json字符串 使用相应的排除策略
     * @param obj T 源对象
     * @param strategy ExclusionStrategy 排除字段策略
     * @param pretty boolean 是否格式化输出，true-格式化，false-不格式化
     * @param <T> 目标对象类型
     * @return String json字符串
     */
    public static <T> String toJson(
            T obj, ExclusionStrategy strategy, boolean pretty) {
        return toJson(obj, strategy, null, pretty);
    }

    /**
     * 将对象转换成json字符串 使用相应的字段名策略
     * @param obj T 源对象
     * @param policy FieldNamingPolicy 字段名转换策略
     * @param <T> 目标对象类型
     * @return String json字符串
     */
    public static <T> String toJson(T obj, FieldNamingPolicy policy) {
        return toJson(obj, null, policy);
    }

    /**
     * 将对象转换成json字符串 使用相应的字段名策略
     * @param obj T 源对象
     * @param policy FieldNamingPolicy 字段名转换策略
     * @param pretty boolean 是否格式化输出，true-格式化，false-不格式化
     * @param <T> 目标对象类型
     * @return String json字符串
     */
    public static <T> String toJson(
            T obj, FieldNamingPolicy policy, boolean pretty) {
        return toJson(obj, null, policy, pretty);
    }

    /**
     * 将对象转换成json字符串 使用相应的排除策略和字段名策略，默认格式化输出
     * @param obj T 源对象
     * @param strategy ExclusionStrategy 排除字段策略
     * @param policy FieldNamingPolicy 字段名转换策略
     * @param <T> 目标对象类型
     * @return String json字符串
     */
    public static <T> String toJson(T obj,
                                    ExclusionStrategy strategy,
                                    FieldNamingPolicy policy) {
        return toJson(obj, strategy, policy, true);
    }

    /**
     * 将对象转换成json字符串 使用相应的排除策略和字段名策略
     * @param obj T 源对象
     * @param strategy ExclusionStrategy 排除字段策略
     * @param policy FieldNamingPolicy 字段名转换策略
     * @param pretty boolean 是否格式化输出，true-格式化，false-不格式化
     * @param <T> 目标对象类型
     * @return String json字符串
     */
    public static <T> String toJson(T obj,
                                    ExclusionStrategy strategy,
                                    FieldNamingPolicy policy, boolean pretty) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (strategy != null) {
            gsonBuilder.setExclusionStrategies(strategy);
        }
        if (policy != null) {
            gsonBuilder.setFieldNamingPolicy(policy);
        }
        if (pretty) {
            gsonBuilder.setPrettyPrinting();
        }
        Type type = new TypeToken<T>() {}.getType();
        return gsonBuilder.create().toJson(obj, type);
    }

    /**
     * 将对象转换成Json字符串，默认格式化输出
     * @param obj Object 目标对象
     * @param type Type 对象类型
     * @return String json字符串
     */
    public static String toJson(Object obj, Type type) {
        return toJson(obj, type, true);
    }

    /**
     * 将对象转换成Json字符串
     * @param obj Object 目标对象
     * @param type Type 对象类型
     * @param pretty boolean 是否格式化输出，true-格式化，false-不格式化
     * @return String json字符串
     */
    public static String toJson(Object obj, Type type, boolean pretty) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (pretty) {
            gsonBuilder.setPrettyPrinting();
        }
        return gsonBuilder.create().toJson(obj, type);
    }

    /**
     * 根据给定的类型，将json字符串转换成对象
     *
     * @param <T>      目标对象类型
     * @param jsonStr  String json字符串
     * @param classOfT Class<T> 目标类型
     * @return T 目标对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> classOfT) {
        return fromJson(jsonStr, classOfT, null);
    }

    /**
     * 根据给定的类型，将json字符串转换成对象，并应用相应的字段名称转换策略
     * @param jsonStr String 源json字符串
     * @param classOfT Class<T> 目标对象所属类类型对象
     * @param policy FieldNamingPolicy 字段名策略
     * @param <T> 目标对象类型
     * @return T 目标对象
     */
    public static <T> T fromJson(
            String jsonStr, Class<T> classOfT, FieldNamingPolicy policy) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (policy != null) {
            gsonBuilder.setFieldNamingPolicy(policy);
        }
        return gsonBuilder.create().fromJson(jsonStr, classOfT);
    }

    public static <T> T fromJson(
            String jsonStr,Type type, FieldNamingPolicy policy) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(policy).create();
        return gson.fromJson(jsonStr, type);
    }

    public static <T> T fromJson(String jsonStr,Type type) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonStr, type);
    }

    public static <T> T fromJsonNoException(String jsonStr,Type type) {
        try {
            return fromJson(jsonStr, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把json格式字符串转换成Map<String ,Object>对象
     * @param json json格式字符串
     * @return Map
     */
    public static Map<String, Object> toStrObjMap(String json){
        Type type = new TypeToken<Map<String ,Object>>() {}.getType();
        return GsonUtils.fromJson(json, type);
    }

    /**
     * 把json格式字符串转换成Map<String ,String>对象
     * @param json json格式字符串
     * @return Map
     */
    public static Map<String, String> toStrStrMap(String json){
        Type type = new TypeToken<Map<String ,String>>() {}.getType();
        return GsonUtils.fromJson(json, type);
    }

    /**
     * 把json格式字符串转换成Map<String ,Long>对象
     * @param json json格式字符串
     * @return Map
     */
    public static Map<String, Long> toStrLongMap(String json){
        Type type = new TypeToken<Map<String ,Long>>() {}.getType();
        return GsonUtils.fromJson(json, type);
    }

    /**
     * 把json格式字符串转换成Map<String ,Integer>对象
     * @param json json格式字符串
     * @return Map
     */
    public static Map<String, Integer> toStrIntegerMap(String json){
        Type type = new TypeToken<Map<String ,Integer>>() {}.getType();
        return GsonUtils.fromJson(json, type);
    }

    /**
     * 把json格式字符串转换成Map<String ,List<Long>>对象
     * @param json json格式字符串
     * @return Map
     */
    public static Map<String ,List<Long>> toStrLongListMap(String json){
        Type type = new TypeToken<Map<String ,List<Long>>>() {}.getType();
        return GsonUtils.fromJson(json, type);
    }

    /**
     * 把json格式字符串转换成List<Long>对象
     * @param json json格式字符串
     * @return Map
     */
    public static List<Long> toLongList(String json){
        Type type = new TypeToken<List<Long>>() {}.getType();
        return GsonUtils.fromJson(json, type);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "aaasdfsf");
        map.put("b", "sfsdfsdfsdf");
        System.out.println(GsonUtils.toJson(map));
        System.out.println(GsonUtils.toJson(map, false));
    }
}
