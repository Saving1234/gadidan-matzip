package com.gadidan.app.common;

import com.gadidan.app.dto.UserDto;

/// 유저 로그인 상태를 관리하는 세션입니다.
public class UserSession {
    private static UserDto userSession = null;

    /// 로그인 상태 여부를 반환하는 함수
    public static boolean isLogin() {
        if (userSession != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void setUserInfo(UserDto user) {
        userSession = user;
    }

    public static UserDto getUserInfo() {return userSession;}

    public static void removeUserInfo() {
        userSession = null;
    }
}