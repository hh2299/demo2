package com.example.demo.common.util;


import com.example.demo.common.vo.LoginUserVO;
import org.springframework.util.StringUtils;

/**
 * @Author: quin
 * @Date: 2021/6/24
 * @Description: 用户信息
 */
public abstract class UserUtils {

    public static String userIdKey = "uid";

    public static String roleIdKey = "rid";

    private static ThreadLocal<LoginUserVO> userThreadLocal = new ThreadLocal();


    public static void setCurrentUser(LoginUserVO vo) {
        userThreadLocal.set(vo);
    }

    public static void setCurrentUser(String userId,String roleId) {
        LoginUserVO vo = new LoginUserVO();
        vo.setUserId(userId);
        vo.setRoleId(roleId);
        userThreadLocal.set(vo);
    }


    public static LoginUserVO getCurrentUser() {
        return userThreadLocal.get();
    }


    public static String getCurrentUserId() {
        LoginUserVO user = getCurrentUser();
        if(user != null) {
            return user.getUserId();
        }
        return null;
    }

    public static String getCurrentRoleId() {
        LoginUserVO user = getCurrentUser();
        if(user != null) {
            return user.getRoleId();
        }
        return null;
    }



    public static Long getCurrentUserIdOfLong() {
        String userId = getCurrentUserId();
        if (!StringUtils.isEmpty(userId)) {
            return Long.valueOf(userId);
        }
        return null;
    }

    public static Long getCurrentRoleOfLong() {
        String roleId = getCurrentRoleId();
        if (!StringUtils.isEmpty(roleId)) {
            return Long.valueOf(roleId);
        }
        return null;
    }
//
//    public static void setCurrentUserId(String userId) {
//        userThreadLocal.set(userId);
//    }
//
//    public static void setCurrentUserId(Long userId) {
//        if (userId != null) {
//            userThreadLocal.set(userId.toString());
//        }
//    }

    public static void removeCurrentUser() {
        userThreadLocal.remove();
    }
}
