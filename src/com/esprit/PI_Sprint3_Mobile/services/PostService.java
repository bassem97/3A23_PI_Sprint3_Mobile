package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.entities.Post;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostService {
    private static PostService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Post> posts;

    private PostService() {
        req = new ConnectionRequest();
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    public ArrayList<Post> findAll(){
        String url = Statics.BASE_URL + "api/post";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }

    public boolean save(Post post) {
        String url = Statics.BASE_URL + "api/post/new/sujet/" + post.getSujet().getId() + "/user/" + UserSession.getUser().getId() + "/t?text=" + post.getText();
        req.setUrl(url);
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
            return resultOK;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Post post) {
        String url = Statics.BASE_URL + "api/post/" + post.getId() + "/update?text=" + post.getText();
        req.setUrl(url);
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
            return resultOK;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "api/post/" + id + "/delete";
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
            return resultOK;
        } catch (Exception e) {
            return false;
        }

    }


    public ArrayList<Post> parsePosts(String jsonText){
        try {
            posts=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Post p = new Post();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setText((obj.get("text").toString()));
                p.setImage((obj.get("image").toString()));
                float rating = Float.parseFloat(obj.get("rating").toString());
                p.setRating((int)rating);
                float sujetId = Float.parseFloat(obj.get("sujetId").toString());
                p.setSujet(SujetService.getInstance().findById((int) sujetId));
                float userId = Float.parseFloat(obj.get("userId").toString());
                p.setUser(UserService.getInstance().findById((int) userId));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(obj.get("date").toString()
                        .replace("T", " ")
                        .split("\\+")[0], formatter);
                p.setDateTime(dateTime);

                posts.add(p);
            }

        } catch (IOException ex) {

        }

        return posts;
    }

}
