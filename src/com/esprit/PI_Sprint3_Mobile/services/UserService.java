package com.esprit.PI_Sprint3_Mobile.services;


import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    public static UserService instance = null;
    public boolean resultOK;
    public ConnectionRequest req;
    public ArrayList<User> users;

    public UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public ArrayList<User> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");
            for (Map<String, Object> obj : list) {
                User user = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                user.setId((int) id);
                user.setNom(obj.get("nom").toString());
                user.setPrenom(obj.get("prenom").toString());
                user.setUsername(obj.get("username").toString());
                user.setEmail(obj.get("email").toString());
                user.setPassword(obj.get("password").toString());
                if(obj.get("image") == null)
                    user.setImage(null);
                else
                    user.setImage(obj.get("image").toString());
                users.add(user);
            }
        } catch (IOException ex) {
        }
        return users;
    }

    public ArrayList<User> findAll() {
        String url = Statics.BASE_URL + "api/user/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public User findByEmail(String email){
        List<User> users = findAll();
         return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public User findById(int id){
        List<User> users = findAll();
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public boolean save(User user) {
        String url = Statics.BASE_URL + "api/user/signUp";
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("nom", user.getNom());
            hashMap.put("prenom", user.getPrenom());
            hashMap.put("email", user.getEmail());
            hashMap.put("username", user.getUsername());
            hashMap.put("password", user.getPassword());
            req.setRequestBody(Result.fromContent(hashMap).toString());
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(User user) {
//        String url = Statics.BASE_URL + "api/user/update/"+user.getId();
        String url = Statics.BASE_URL + "api/user/update/"+ user.getId() + "?nom=" + user.getNom() + "&prenom=" + user.getPrenom() + "&email="+ user.getEmail() + "&image="+user.getImage() ;
        req.setUrl(url);
//        req.setHttpMethod("PUT");
        req.setContentType("application/json");
//        System.out.println(user);
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id",  user.getId());
            hashMap.put("nom", user.getNom());
            hashMap.put("prenom", user.getPrenom());
            hashMap.put("email", user.getEmail());
            hashMap.put("image", user.getImage());
            // passsword w birthdate
//            req.setRequestBody(Result.fromContent(hashMap).toString());
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
            });
//            System.out.println(req.getRequestBody());
            NetworkManager.getInstance().addToQueueAndWait(req);
//            System.out.println("1");
            return resultOK;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "api/user/delete/"+id;
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.setContentType("application/json");
        try {
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            System.out.println("User deleted !");
            return resultOK;
        } catch (Exception e) {
            System.out.println("User could not be deleted !");
            return false;
        }

    }

}
