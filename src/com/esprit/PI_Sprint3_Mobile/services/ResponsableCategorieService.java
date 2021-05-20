package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.Categorie;
import com.esprit.PI_Sprint3_Mobile.entities.ResponsableCategorie;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponsableCategorieService {

    private static ResponsableCategorieService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<ResponsableCategorie> responsableCategories;
    ResponsableCategorie responsableCategorie;

    private ResponsableCategorieService() {
        req = new ConnectionRequest();
    }

    public static ResponsableCategorieService getInstance() {
        if (instance == null) {
            instance = new ResponsableCategorieService();
        }
        return instance;
    }

    public ArrayList<ResponsableCategorie> findAll() {
        String url = Statics.BASE_URL + "api/responsable/categorie";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responsableCategories = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return responsableCategories;
    }
    public ResponsableCategorie findById(int id){
        String url = Statics.BASE_URL + "api/responsable/categorie/"+ id + "/find";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responsableCategorie= parseEvents (new String(req.getResponseData())).get(0);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return responsableCategorie;
    }


    public boolean save(ResponsableCategorie responsableCategorie) {
        String url = Statics.BASE_URL + "api/responsable/categorie/" + responsableCategorie.getCategorie().getId() + "/new?Nom=" + responsableCategorie.getNom() + "&Prenom="+responsableCategorie.getPrenom()+ "&Email="+responsableCategorie.getEmail() ;
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

    public boolean update(ResponsableCategorie responsableCategorie) {
        String url = Statics.BASE_URL + "api/responsable/categorie/" + responsableCategorie.getId() + "/update?Nom=" + responsableCategorie.getNom() + "&Prenom="+responsableCategorie.getPrenom()+ "&Email="+responsableCategorie.getEmail()  + "&Categorie="+responsableCategorie.getCategorie().getId() ;
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
        String url = Statics.BASE_URL + "api/responsable/categorie/" + responsableCategorie.getId() + "/delete";
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





    public ArrayList<ResponsableCategorie> parseEvents(String jsonText){
        try {
            responsableCategories =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                ResponsableCategorie r = new ResponsableCategorie();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
                r.setNom((obj.get("nom").toString()));
                r.setPrenom(obj.get("prenom").toString());
                r.setEmail(obj.get("email").toString());
                float cat_id = Float.parseFloat(obj.get("categorie_id").toString());
                r.setCategorie(CategorieService.getInstance().findAll().stream().filter(cat -> cat.getId() == cat_id ).findAny().orElse(null)) ;
                responsableCategories.add(r);
            }

        } catch (IOException ex) {

        }

        return responsableCategories;
    }
}
