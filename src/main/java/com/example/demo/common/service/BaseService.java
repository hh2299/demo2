package com.example.demo.common.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import com.example.demo.common.entity.BaseEntity;
import com.example.demo.common.util.ConverterUtils;

import org.apache.ibatis.reflection.property.PropertyNamer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: DaleShay
 * @Date: 2020/6/17 16:00
 * @Description: 和数据库交互的baseService
 */
public abstract class BaseService {

    /**
     *
     * @param wrapper
     * @param column 排序字段
     * @param fieldValue 需要排序在最后的字段值
     * @param <T>
     * @return
     */
    protected <T extends BaseEntity> QueryWrapper <T> fieldOrderByAsc(QueryWrapper <T> wrapper, SFunction <T, ?> column, List <String> fieldValue) {
        return this.fieldOrderBy(wrapper, column, fieldValue, true);
    }

    /**
     *
     * @param wrapper
     * @param column 排序字段
     * @param fieldValue 需要排序在最前的字段值
     * @param <T>
     * @return
     */
    protected <T extends BaseEntity> QueryWrapper <T> fieldOrderByDesc(QueryWrapper <T> wrapper, SFunction <T, ?> column, List <String> fieldValue) {
        return this.fieldOrderBy(wrapper, column, fieldValue, false);
    }

    protected <T extends BaseEntity> QueryWrapper <T> fieldOrderBy(QueryWrapper <T> wrapper, SFunction <T, ?> column, List <String> fieldValue, boolean isAsc) {

        SerializedLambda lambda = LambdaUtils.resolve(column);
        // 从lambda信息取出method、field、class等

        String a = PropertyNamer.methodToProperty(lambda.getImplMethodName());

        Map<String, ColumnCache>  map = LambdaUtils.getColumnMap(lambda.getInstantiatedMethodType());
        ColumnCache columnCache = map.get(LambdaUtils.formatKey(a));
        String field = columnCache.getColumn();
        String str = "field(" + field;

        for (String value : fieldValue) {
            str += "," + value;
        }
        str += ")";

        wrapper.orderBy(true, isAsc, str);

        //数据库order by排序不唯一，的确会导致最终的数据排序是不是稳定的，表现出来的结果，就是分页时会重复显示；
        //建议在 order by 最后，增加 PK 列或 ROWID；
        wrapper.orderByAsc("id");

        return wrapper;
    }

    /**
     * 获取 主表 关联一对多 的 关联表数据
     *
     * @param mapper
     * @param fkColumn 外键关联列
     * @param fkValue  外键关联值
     * @param <T>      实体泛型
     * @param <M>      mapper 泛型
     * @param <FK>     外键泛型
     * @return
     */
    protected <T extends BaseEntity, M extends BaseMapper <T>, FK> List <T> getRelationList(M mapper, SFunction <T, ?> fkColumn, FK fkValue) {
        LambdaQueryWrapper <T> wrapper = new LambdaQueryWrapper <>();
        wrapper.eq(fkColumn, fkValue);
        List <T> result = mapper.selectList(wrapper);

        return result;
    }


    /**
     * 获取 主表 关联一对多 的 关联表数据 并且 将 T 转为 R 类型
     *
     * @param mapper
     * @param fkColumn
     * @param fkValue
     * @param clazz
     * @param <T>
     * @param <R>
     * @param <M>
     * @param <FK>
     * @return
     */
    protected <T extends BaseEntity, R, M extends BaseMapper <T>, FK> List <R> getRelationList(M mapper, SFunction <T, ?> fkColumn, FK fkValue, Class <R> clazz) {
        List <T> result = this.getRelationList(mapper, fkColumn, fkValue);
        return ConverterUtils.convertList(result, clazz);
    }


    /**
     * 保存主表 关联一对多 的 关联表
     *
     * @param mapper
     * @param fkColumn
     * @param fkValue
     * @param insertList
     * @param <T>
     * @param <M>
     * @param <FK>
     */
    protected <T extends BaseEntity, M extends BaseMapper <T>, FK> void saveRelationList(M mapper, SFunction <T, ?> fkColumn, FK fkValue, List <T> insertList) {
        //根据关联值查询
        LambdaQueryWrapper <T> wrapper = new LambdaQueryWrapper <>();
        wrapper.eq(fkColumn, fkValue);
        List <T> dbList = mapper.selectList(wrapper);


        Iterator<T> it = dbList.iterator();

        while (it.hasNext()) {
            T db = it.next();
            Boolean flag = false;

            Iterator <T> insertIt = insertList.iterator();
            while (insertIt.hasNext()) {
                T entity = insertIt.next();
                if (db.getId().equals(entity.getId())) {
                    this.setFkValue(fkColumn, fkValue, entity);
                    //update
                    // System.out.println(db.getId().equals(entity.getId()));

                    mapper.updateById(entity);
                    insertIt.remove();
                    flag = true;
                    break;
                }
            }
            if (flag) {
                it.remove();
            }
        }
        if(!insertList.isEmpty()){
            for(T entity : insertList) {
                this.setFkValue(fkColumn, fkValue, entity);
                //insert
                entity.setId(null);
                mapper.insert(entity);

            }
        }

        if(!dbList.isEmpty()) {
            List<Long> deleteIds = dbList.stream().map(BaseEntity::getId).collect(Collectors.toList());
            mapper.deleteBatchIds(deleteIds);
        }
    }

    /**
     * 将 R 类型 转成 T类型
     * 保存主表 关联一对多 的 关联表
     *
     * @param mapper
     * @param fkColumn   外键关联列
     * @param fkValue    外键关联值
     * @param insertList 传入的 DTO 数据
     * @param clazz      数据库 Entity 类型
     * @param <T>        数据库 Entity 类型泛型
     * @param <R>        传入的 DTO 数据类型泛型
     * @param <M>        Mapper 的数据类型泛型
     */
    protected <T extends BaseEntity, R, M extends BaseMapper <T>, FK> void saveRelationList(M mapper, SFunction <T, ?> fkColumn, FK fkValue, List <R> insertList, Class <T> clazz) {

        List <T> insertListProp = ConverterUtils.convertList(insertList, clazz);

        this.saveRelationList(mapper, fkColumn, fkValue, insertListProp);
    }

    /**
     * 删除外键关联列表
     * @param mapper
     * @param fkColumn
     * @param fkValue
     * @param <T>
     * @param <M>
     * @param <FK>
     */
    protected <T extends BaseEntity, M extends BaseMapper <T>,FK> void deleteRelationList(M mapper, SFunction <T, ?> fkColumn,FK fkValue) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper <>();
        wrapper.eq(fkColumn,fkValue);
        mapper.delete(wrapper);
    }

    /**
     * 根据 entity 的 get方法 找出 field 。将值 插入 field;
     *
     * @param fkColumn get方法
     * @param fkValue  值
     * @param entity   对象
     * @param <T>
     * @param <FK>
     */
    private <T, FK> void setFkValue(SFunction <T, ?> fkColumn, FK fkValue, T entity) {
        SerializedLambda lambda = LambdaUtils.resolve(fkColumn);
        // 从lambda信息取出method、field、class等
        String field = lambda.getImplMethodName().substring("get".length());
        String methodName = "set" + field;

        Method method = null;
        try {
            method = entity.getClass().getMethod(methodName, fkValue.getClass());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            method.invoke(entity, fkValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
