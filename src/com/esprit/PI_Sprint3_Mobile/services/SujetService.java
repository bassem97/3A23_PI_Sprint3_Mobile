package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SujetService {  private static SujetService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Sujet> sujets;
    Sujet sujet;

    private SujetService() {
        req = new ConnectionRequest();
    }

    public static SujetService getInstance() {
        if (instance == null) {
            instance = new SujetService();
        }
        return instance;
    }

    public ArrayList<Sujet> findAll(){
        String url = Statics.BASE_URL + "api/sujet";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sujets = parseSujets(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return sujets;
    }

    public Sujet findById(int id){
        String url = Statics.BASE_URL + "api/sujet/"+ id + "/find";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sujet = parseSujets(new String(req.getResponseData())).get(0);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return sujet;
    }

    public boolean save(Sujet sujet) {
        String url = Statics.BASE_URL + "api/sujet/new/theme/" + sujet.getTheme().getId() + "/user/" + UserSession.getUser().getId() +"/t?text=" + sujet.getText();
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

    public boolean update(Sujet sujet) {
        String url = Statics.BASE_URL + "api/sujet/" + sujet.getId() + "/update?text=" + sujet.getText();
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
        String url = Statics.BASE_URL + "api/sujet/" + id + "/delete";
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





    public ArrayList<Sujet> parseSujets(String jsonText){
        try {
            sujets =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Sujet s = new Sujet();
                float id = Float.parseFloat(obj.get("id").toString());
                s.setId((int)id);
                s.setText((obj.get("text").toString()));
                s.setImage((obj.get("image").toString()));
                float themeId = Float.parseFloat(obj.get("themeId").toString());
                s.setTheme(ThemeService.getInstance().findById((int) themeId));
                float userId = Float.parseFloat(obj.get("userId").toString());
                s.setUser(UserService.getInstance().findById((int) userId));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(obj.get("date").toString()
                        .replace("T", " ")
                        .split("\\+")[0], formatter);
                s.setDateTime(dateTime);
                sujets.add(s);
            }

        } catch (IOException ex) {

        }

        return sujets;
    }

}
