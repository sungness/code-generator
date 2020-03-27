package com.sungness.core.dao;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * mybatis数据层接口泛型基类，对数据层通用接口进行统一封装
 * @author wanghongwei
 * @since v2.0 2015-04-14
 *
 * @param <E> 要操作的数据表对应的实体类
 * @param <PK> 表的主键类型
 */
public interface GenericMapper<E, PK extends Serializable> {

    /**
     * 根据主键获取实体对象
     * @param primaryKey PK 主键
     * @return E 实体对象
     */
    E get(PK primaryKey);

    /**
     * 根据非主键条件获取实体对象（必须保证结果集只有一条记录，否则会抛异常，请使用getList）
     * @param conditions Map<String, Object> 条件map
     * @return E 实体对象
     */
    E getByDynamicWhere(Map<String, Object> conditions);

    /**
     * 查询总记录数
     * @return int 总记录数
     */
    int getCount();

    /**
     * 根据条件查询总记录数
     * @param conditions Map<String, Object> 条件map
     * @return int 匹配记录数
     */
    int getCount(Map<String, Object> conditions);

    /**
     * 查询所有记录列表
     * @return List<E> 记录列表
     */
    List<E> getList();

    /**
     * 根据条件查询记录列表
     * @param conditions Map<String, Object> 条件map
     * @return List<E> 记录列表
     */
    List<E> getList(Map<String, Object> conditions);

    /**
     * 查询全部记录列表，带翻页
     * @param rowBounds RowBounds 翻页对象：起始位置、返回条数
     * @return List<E> 记录列表
     */
    List<E> getList(RowBounds rowBounds);

    /**
     * 根据条件查询记录列表，带翻页
     * @param rowBounds RowBounds 翻页对象：起始位置、返回条数
     * @param conditions Map<String, Object> 条件map
     * @return List<E> 记录列表
     */
    List<E> getList(RowBounds rowBounds, Map<String, Object> conditions);

    /**
     * 插入新记录
     * @param entity E 待插入实体对象
     * @return int
     */
    int insert(E entity);

    /**
     * 更新记录
     * @param entity E 待更新实体对象
     * @return int 受影响的记录数
     */
    int update(E entity);

    /**
     * 根据主键删除记录
     * @param primaryKey PK 主键值
     * @return int 删除条数
     */
    int delete(PK primaryKey);

    /**
     * 根据主键列表批量删除记录
     * @param primaryKeys List<PK>主键列表
     * @return int 删除条数
     */
    int batchDelete(List<PK> primaryKeys);
}
