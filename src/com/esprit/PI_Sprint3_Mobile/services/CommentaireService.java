package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.Commentaire;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentaireService {

    private static CommentaireService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Commentaire> commentaires;


    public CommentaireService() {

        req = new ConnectionRequest();
    }

    public static CommentaireService getInstance() {
        if (instance == null) {
            instance = new CommentaireService();
        }
        return instance;
    }

    public ArrayList<Commentaire> findAll(){
        String url = Statics.BASE_URL + "api/commentaire/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseCommentaires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaires;
    }

    public boolean save(Commentaire commentaire) {
        String url = Statics.BASE_URL + "api/commentaire/new/"+commentaire.getArticle().getId();
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        try{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ref", commentaire.getRef());
            hashMap.put("corps",commentaire.getCorps());
            hashMap.put("date", commentaire.getDate());
            //hashMap.put("rating", commentaire.getRating());
            //hashMap.put("article",commentaire.getArticle());

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

    public boolean update(Commentaire commentaire) {
        String url = Statics.BASE_URL + "api/commentaire/" + commentaire.getRef() + "/update";
        req.setUrl(url);
        req.setHttpMethod("PUT");
        req.setContentType("application/json");
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ref", commentaire.getRef());
            hashMap.put("corps", commentaire.getCorps());
            //hashMap.put("date", commentaire.getDate());
            //hashMap.put("rating", commentaire.getRating());
            //hashMap.put("article",commentaire.getArticle().getId());

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

    public boolean delete(int ref) {
        String url = Statics.BASE_URL + "api/commentaire/" + ref + "/delete";
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

    public ArrayList<Commentaire> parseCommentaires(String jsonText){
        try {
            commentaires =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Commentaire c = new Commentaire();
                float ref = Float.parseFloat(obj.get("ref").toString());
                c.setRef((int)ref);
                c.setCorps(obj.get("corps").toString());

                commentaires.add(c);
            }

        } catch (IOException ex) {

        }

        return commentaires;
    }
}

