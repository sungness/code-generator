package com.sungness.core.service;

import com.sungness.core.dao.GenericMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Service层泛型基类，对Service层通用方法进行统一封装
 * @author wanghongwei
 * @since v2.0 2014-04-02
 *
 * @param <E> 要操作的数据表对应的实体类
 * @param <PK> 表的主键类型
 */
public abstract class GenericService<E, PK extends Serializable> {
    protected Logger logger = LoggerFactory.getLogger(GenericService.class);

    /**
     * 获取数据层mapper接口对象，子类必须实现该方法
     * @return GenericMapper<E, PK> 数据层mapper接口对象
     */
    protected abstract GenericMapper<E, PK> getMapper();

    /**
     * 增加记录
     * @param entity E 要增加的记录对象
     * @return int 受影响的记录条数
     */
    public int insert(E entity) throws ServiceProcessException {
        return getMapper().insert(entity);
    }

    /**
     * 以主健删除记录
     * @param id PK 要删除的记录主健
     * @return int 受影响的记录条数
     */
    public int delete(PK id) throws ServiceProcessException {
        return getMapper().delete(id);
    }

    /**
     * 以主健批量伤处记录
     * @param primaryKeys List<PK> 要删除的记录主健列表
     * @return int 受影响的记录条数
     */
    public int batchDelete(List<PK> primaryKeys) throws ServiceProcessException {
        return getMapper().batchDelete(primaryKeys);
    }

    /**
     * 更新记录
     * @param entity E 要更新的记录对象
     * @return int 受影响的记录条数
     */
    public int update(E entity) throws ServiceProcessException {
        return getMapper().update(entity);
    }

    /**
     * 根据主健查找记录
     * @param primaryKey PK 主健值
     * @return E 查找的记录对象
     */
    public E get(PK primaryKey) {
        return getMapper().get(primaryKey);
    }

    /**
     * 获取符合条件的唯一记录，如果结果记录不唯一，将抛出异常
     * @param conditions Map<String, Object> conditions 条件Map
     * @return E 符合条件的单个记录
     */
    public E getByDynamicWhere(Map<String, Object> conditions) {
        return getMapper().getByDynamicWhere(conditions);
    }

    /**
     * 获取总记录条数
     * @return int 记录总数
     */
    public int getCount() {
        return getMapper().getCount();
    }

    /**
     * 获取符合条件的记录条数
     * @param conditions Map<String, Object> 查询条件
     * @return int 符合条件的记录条数
     */
    public int getCount(Map<String, Object> conditions) {
        return getMapper().getCount(conditions);
    }

    /**
     * 获取所有记录列表
     * @return List<E> 记录列表
     */
    public List<E> getList() {
        return getMapper().getList();
    }

    /**
     * 获取符合条件的所有记录
     * @param conditions Map<String, Object> conditions 条件Map
     * @return List<E> 符合条件的记录列表
     */
    public List<E> getList(Map<String, Object> conditions) {
        return getMapper().getList(conditions);
    }
}
