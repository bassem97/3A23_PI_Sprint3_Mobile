package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.Article;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {

    private static ArticleService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Article> articles;


    public ArticleService() {

        req = new ConnectionRequest();
    }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    public ArrayList<Article> findAll(){

        String url = Statics.BASE_URL + "api/article/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

    public boolean save(Article article) {
        String url = Statics.BASE_URL + "api/article/new"+article.getTitre();
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");

        try{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", article.getId());
            hashMap.put("Titre", article.getTitre());
            hashMap.put("corps", article.getCorps());
            hashMap.put("date", article.getDate());
            hashMap.put("image", article.getImage());
            hashMap.put("likes", article.getLikes());
            hashMap.put("vues", 0);
            //hashMap.put("commentaire", article.getCommentaire());

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

    public boolean update(Article article) {
        String url = Statics.BASE_URL + "api/article/" + article.getId() + "/update";
        req.setUrl(url);
        req.setHttpMethod("PUT");
        req.setContentType("application/json");

        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", article.getId());
            hashMap.put("Titre", article.getTitre());
            hashMap.put("corps", article.getCorps());
            //hashMap.put("date", article.getDate());
            //hashMap.put("image", article.getImage());
            //hashMap.put("likes", article.getLikes());
           // hashMap.put("vues", article.getVues());

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

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "api/article/" + id + "/delete";
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

    public ArrayList<Article> parseArticles(String jsonText){

        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object> articlesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)articlesListJson.get("root");


            for(Map<String,Object> obj : list){

                Article a = new Article();
                float id = Float.parseFloat(obj.get("id").toString());
                //int id = Integer.parseInt(obj.get("id").toString());
                a.setId((int) id);
                a.setTitre((obj.get("titre").toString()));
                a.setCorps(obj.get("corps").toString());
                /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(obj.get("date").toString()
                        .replace("T", " ")
                        .split("\\+")[0], formatter);
                a.setDate(dateTime);*/
                //a.setImage(obj.get("image").toString());
                //a.setLikes((int)Float.parseFloat(obj.get("likes").toString()));

                articles.add(a);
            }
        } catch (IOException ex) { }

        return articles;
    }
}