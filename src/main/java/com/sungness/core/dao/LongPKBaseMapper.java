package com.sungness.core.dao;

/**
 * mybatis数据层接口泛型，指定主键为Long型
 * @author wanghongwei
 * @since v2.0 2015-04-14
 *
 * @param <E> 要操作的数据表对应的实体类
 */
public interface LongPKBaseMapper<E> extends GenericMapper<E, Long> {
}
