package com.perscab.servlets;

import com.perscab.model.Account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyUtils {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_TO_STORE_USER_NAME_IN_COOKIE";
    public static final int COOKIE_MAX_AGE_1_DAY = 24 * 60 * 60;

    public static void storeAuthorizedAccount(HttpSession session, Account authorizedAccount) {
        session.setAttribute("authorizedAccount", authorizedAccount);
    }

    public static Account getAuthorizedAccount(HttpSession session) {
        Account authorizedAccount = (Account) session.getAttribute("authorizedAccount");
        return authorizedAccount;
    }

    public static void storeUserCookie(HttpServletResponse response, Account account) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, account.getLogin());
        cookieUserName.setMaxAge(COOKIE_MAX_AGE_1_DAY);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
