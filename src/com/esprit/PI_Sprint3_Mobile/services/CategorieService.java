package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.Categorie;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategorieService {

    private static CategorieService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Categorie> categories;
    Categorie categorie;

    private CategorieService() {
        req = new ConnectionRequest();
    }

    public static CategorieService getInstance() {
        if (instance == null) {
            instance = new CategorieService();
        }
        return instance;
    }

    public ArrayList<Categorie> findAll(){
        String url = Statics.BASE_URL + "api/categorie";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

    public Categorie findById(int id){
        String url = Statics.BASE_URL + "api/categorie/"+ categorie.getId() + "/find";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie = parseEvents(new String(req.getResponseData())).get(0);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie;
    }


    public boolean save(Categorie categorie) {
        String url = Statics.BASE_URL + "api/categorie/new";
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("titre", String.valueOf(categorie.getTitre()));
            hashMap.put("description", categorie.getDescription());
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

    public boolean update(Categorie categorie) {
        String url = Statics.BASE_URL + "api/categorie/" + categorie.getId() + "/update?Titre=" + categorie.getTitre() + "&Description=" + categorie.getDescription();
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
        String url = Statics.BASE_URL + "api/categorie/" + id + "/delete";
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



    public ArrayList<Categorie> parseEvents(String jsonText){
        try {
            categories =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                c.setTitre((obj.get("titre").toString()));
                c.setDescription(obj.get("description").toString());

                categories.add(c);
            }

            return categories;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;

        }


    }
}