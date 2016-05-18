package com.sungness.core.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


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
     * 将对象转换成json字符串 使用相应的排除策略和字段名策略
     * @param obj T 源对象
     * @param strategy ExclusionStrategy 排除字段策略
     * @param policy FieldNamingPolicy 字段名转换策略
     * @param <T> 目标对象类型
     * @return String json字符串
     */
    public static <T> String toJson(T obj,
                                    ExclusionStrategy strategy,
                                    FieldNamingPolicy policy) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (strategy != null) {
            gsonBuilder.setExclusionStrategies(strategy);
        }
        if (policy != null) {
            gsonBuilder.setFieldNamingPolicy(policy);
        }
        Type type = new TypeToken<T>() {}.getType();
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

    public static void main(String[] args) {

    }
}
