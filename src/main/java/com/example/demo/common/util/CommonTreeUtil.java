package com.example.demo.common.util;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用的list转json tree的工具类
 */
public class CommonTreeUtil {

    /**
     * ，list 转 tree 方法,效率比转 JSONObject方式高
     * @param list 传入的list
     * @param parentKeyValue 根节点的父Id值  一般为 0 或者 null
     * @param key  节点标识字段
     * @param parentKey  父节点标识字段
     * @param childrenKey 子节点字段
     * @param <T> 对象泛型
     * @return 转换成树返回
     */
    public static <T> List <T> listToTree(List <T> list,Object parentKeyValue, SFunction <T, ?> key, SFunction <T, ?> parentKey, SFunction <T, ?> childrenKey) {

        List <T> tree = new ArrayList <>();
        listToTree(list, tree, parentKeyValue, key, parentKey, childrenKey);
        return tree;
    }

    /**
     * 效list 转 tree 方法,效率比转 JSONObject方式高 。默认根节点的父节点值为 0L
     * @param list 传入的list
     * @param key  节点标识字段
     * @param parentKey  父节点标识字段
     * @param childrenKey 子节点字段
     * @param <T> 对象泛型
     * @return 转换成树返回
     */
    public static <T> List <T> listToTree(List <T> list, SFunction <T, ?> key, SFunction <T, ?> parentKey, SFunction <T, ?> childrenKey) {

        List <T> tree = new ArrayList <>();
        Long defaultParentValue = 0L;
        listToTree(list, tree, defaultParentValue, key, parentKey, childrenKey);
        return tree;
    }



    private static <T> void listToTree(List <T> list, List <T> tree, Object parentKeyValue, SFunction <T, ?> key, SFunction <T, ?> parentKey, SFunction <T, ?> childrenKey) {
        for (T item : list) {
            Object pValue = getValue(parentKey, item);
            if ( (parentKeyValue == null && pValue == null) || (parentKeyValue!=null && parentKeyValue.equals(pValue)) ) {
                Object keyValue = getValue(key, item);
                List<T> children  = new ArrayList <>();
                listToTree(list, children, keyValue, key, parentKey, childrenKey);
                if(!children.isEmpty()) {
                    setListValue(childrenKey, children, item);
                }

                tree.add(item);
            }
        }

    }



//    public static void main(String[] args) {
//
//        MenuVO vo = new MenuVO();
//        vo.setId(1L);
//        vo.setParentId(0L);
//        vo.setName("root");
//
//
//        MenuVO v1 = new MenuVO();
//        v1.setId(2L);
//        v1.setParentId(1L);
//        v1.setName("2222");
//
//        MenuVO v2 = new MenuVO();
//        v2.setId(3L);
//        v2.setParentId(1L);
//        v2.setName("3333");
//
//
//        MenuVO v21 = new MenuVO();
//        v21.setId(4L);
//        v21.setParentId(3L);
//        v21.setName("3333-1");
//
//        MenuVO v22 = new MenuVO();
//        v22.setId(5L);
//        v22.setParentId(3L);
//        v22.setName("3333-2");
//
//        MenuVO v222 = new MenuVO();
//        v222.setId(6L);
//        v222.setParentId(5L);
//        v222.setName("3333-2-1");
//
//        List<MenuVO> list = new ArrayList <>();
//        list.add(vo);
//        list.add(v1);
//        list.add(v2);
//        list.add(v21);
//        list.add(v22);
//        list.add(v222);
//
//        Long t1 = System.currentTimeMillis();
//        List<MenuVO> tree = listToTree(list,MenuVO::getId, MenuVO::getParentId,MenuVO::getChildren);
//        Long t2 = System.currentTimeMillis();
//        // System.out.println(t2 - t1);
//
//        Long t3 = System.currentTimeMillis();
//        JSONArray result = CommonTreeUtil.listToTree(JSONArray.parseArray(JSON.toJSONString(list)), "id", "parentId", "children");
//        result.toJavaList(MenuVO.class);
//        Long t4 = System.currentTimeMillis();
//        // System.out.println(t4 - t3);
//
//
//        // System.out.println(JSONObject.toJSON(tree));
//        // System.out.println(result);
//    }

//
//    /**
//     * - listToTree
//     * - <p>方法说明<p>
//     * - 将JSONArray数组转为树状结构
//     * - @param arr 需要转化的数据
//     * - @param id 数据唯一的标识键值
//     * - @param pid 父id唯一标识键值
//     * - @param child 子节点键值
//     * - @return JSONArray
//     */
//    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
//        JSONArray r = new JSONArray();
//        JSONObject hash = new JSONObject();
//        //将数组转为Object的形式，key为数组中的id
//        for (int i = 0; i < arr.size(); i++) {
//            JSONObject json = (JSONObject) arr.get(i);
//            hash.put(json.getString(id), json);
//        }
//        //遍历结果集
//        for (int j = 0; j < arr.size(); j++) {
//            //单条记录
//            JSONObject aVal = (JSONObject) arr.get(j);
//            //在hash中取出key为单条记录中pid的值
//            JSONObject hashVP = null;
//            if (!NullUtil.isEmpty(aVal.get(pid))) {
//                hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
//            }
//            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
//            if (hashVP != null) {
//                //检查是否有child属性
//                if (hashVP.get(child) != null) {
//                    JSONArray ch = (JSONArray) hashVP.get(child);
//                    ch.add(aVal);
//                    hashVP.put(child, ch);
//                } else {
//                    JSONArray ch = new JSONArray();
//                    ch.add(aVal);
//                    hashVP.put(child, ch);
//                }
//            } else {
//                r.add(aVal);
//            }
//        }
//        return r;
//    }

    /**
     * 通过反射 根据 SFunction 获取 entity 的值
     * @param fkColumn
     * @param entity
     * @param <T>
     * @return
     */
    private static <T> Object getValue(SFunction <T, ?> fkColumn, T entity) {
        SerializedLambda lambda = LambdaUtils.resolve(fkColumn);
        // 从lambda信息取出method、field、class等
        String field = lambda.getImplMethodName().substring("get".length());
        String methodName = "get" + field;

        Method method = null;
        try {
            method = entity.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            Object obj = method.invoke(entity);
            return obj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射 根据 SFunction 插入 entity 的值 目前只支持list
     * @param fkColumn
     * @param entity
     * @param <T>
     * @return
     */
    private static <T> void setListValue(SFunction <T, ?> fkColumn, List<T> fkValue, T entity) {
        SerializedLambda lambda = LambdaUtils.resolve(fkColumn);
        // 从lambda信息取出method、field、class等
        String field = lambda.getImplMethodName().substring("get".length());
        String methodName = "set" + field;

        Method method = null;
        try {
            //TODO 参数定义为接口 有问题
            method = entity.getClass().getMethod(methodName, List.class);
//            if(fkValue instanceof  List) {
//
//            } else {
//                method = entity.getClass().getMethod(methodName, fkValue.getClass());
//            }


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