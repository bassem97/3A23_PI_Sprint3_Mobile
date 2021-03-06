package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeService {
    private static ThemeService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Theme> themes;
    Theme theme;

    private ThemeService() {
        req = new ConnectionRequest();
    }

    public static ThemeService getInstance() {
        if (instance == null) {
            instance = new ThemeService();
        }
        return instance;
    }

    public ArrayList<Theme> findAll(){
        String url = Statics.BASE_URL + "api/theme/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                themes = parseThemes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return themes;
    }

    public Theme findById(int id){
        String url = Statics.BASE_URL + "api/theme/"+ id + "/find";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                theme = parseThemes(new String(req.getResponseData())).get(0);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return theme;
    }


    public boolean save(Theme theme) {
        String url = Statics.BASE_URL + "api/theme/new?libelle=" + theme.getLibelle();
        req.setUrl(url);
        try {
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                    ToastBar.showMessage("Theme ajout?? avec succ??s", FontImage.MATERIAL_INFO);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(Theme theme) {
        String url = Statics.BASE_URL + "api/theme/" + theme.getId() + "/update?libelle=" + theme.getLibelle();
        req.setUrl(url);
        req.setContentType("application/json");
        try {
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                    ToastBar.showMessage("Theme modifi?? avec succ??s", FontImage.MATERIAL_INFO);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "api/theme/" + id + "/delete";
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.setContentType("application/json");
        try {
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                    ToastBar.showMessage("Theme supprim?? avec succ??s", FontImage.MATERIAL_INFO);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        } catch (Exception e) {
            return false;
        }

    }





    public ArrayList<Theme> parseThemes(String jsonText){
        try {
            themes =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Theme t = new Theme();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
//                t.setLibelle("elli yji");
//                t.setLibelle(obj.get("libelle").toString().sou
                t.setLibelle(obj.get("libelle").toString());

                themes.add(t);

            }
            return themes;

        } catch (IOException ex) {
            return null;
        }



    }
}
