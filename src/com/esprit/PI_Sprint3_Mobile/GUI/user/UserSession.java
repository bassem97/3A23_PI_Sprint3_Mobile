package com.esprit.PI_Sprint3_Mobile.GUI.user;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.esprit.PI_Sprint3_Mobile.entities.User;

import java.util.List;


public final class UserSession {
    private static UserSession instance;

    private static User user;
    private static List<String> privileges;


    public UserSession(User user, List<String> privileges) {
        UserSession.user = user;
        UserSession.privileges = privileges;
    }

    public UserSession(User user) {
        UserSession.user = user;
    }

    public UserSession() {
    }





    public static UserSession setInstace(User user) {
        System.out.println("isntance "+instance);
        if(instance == null) {
            System.out.println("DKHAAAAAAAAAAAL");
            instance = new UserSession(user);
        }
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public List<String> getPrivileges() {
        return privileges;
    }





    public static   void logOut() {
        user = null;// or null
        instance = null;

    }



    @Override
    public String toString() {
        return "UserSession{" + "user =" + user +" privileges : "+privileges+ '}';
    }










}
