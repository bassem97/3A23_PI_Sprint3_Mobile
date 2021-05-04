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
    private ConnectionRequest req;
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

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User user = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                user.setId((int) id);
                user.setNom(obj.get("nom").toString());
                user.setPrenom(obj.get("prenom").toString());
                user.setUsername(obj.get("username").toString());
                user.setEmail(obj.get("email").toString());
//                user.setGoogle_id(obj.get("google_id").toString());
                user.setPassword(obj.get("password").toString());
//                System.out.println(obj.get("image").toString());
                if(obj.get("image") == null)
                    user.setImage(null);
                else
                    user.setImage(obj.get("image").toString());
//                user.setCreation_date(obj.get("creation_date"));
//                user.setLast_login(obj.get("last_login"));


                users.add(user);
            }


        } catch (IOException ex) {

        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
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
}
