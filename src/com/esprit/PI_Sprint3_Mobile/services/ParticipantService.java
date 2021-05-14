package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.entities.Participant;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParticipantService {
    private static ParticipantService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Participant> participants;

    private ParticipantService() {
        req = new ConnectionRequest();
    }

    public static ParticipantService getInstance() {
        if (instance == null) {
            instance = new ParticipantService();
        }
        return instance;
    }

    public ArrayList<Participant> findAll(){
        String url = Statics.BASE_URL + "api/participant/list/user/" + UserSession.getUser().getUsername();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participants = parseParticipants(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participants;
    }

    public boolean save(Participant participant) {
        String url = Statics.BASE_URL + "api/participant/new/event/" + participant.getEvent().getId() + "/user/" + participant.getUser().getId();
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
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(Participant participant) {
        String url = Statics.BASE_URL + "api/participant/" + participant.getId() + "/update?avis=" + participant.getAvis() + "&daysreminder=" + participant.getDaysReminder();
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
            System.out.println(e.getMessage());
            return false;
        }
    }


    public boolean delete(int id) {
        String url = Statics.BASE_URL + "api/participant/" + id + "/delete";
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


    public ArrayList<Participant> parseParticipants(String jsonText){
        try {
            participants =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Participant t = new Participant();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setAvis((obj.get("avis").toString()));
                float daysReminder = Float.parseFloat(obj.get("daysReminder").toString());
                t.setDaysReminder((int) daysReminder);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(obj.get("dateParticipation").toString()
                        .replace("T", " ")
                        .split("\\+")[0], formatter);
                t.setDateParticipation(dateTime);

                float eventId = Float.parseFloat(obj.get("eventId").toString());
                t.setEvent(EventService.getInstance().findAll().stream().filter(event -> event.getId() == (int) eventId).findAny().get());

                float userId = Float.parseFloat(obj.get("userId").toString());
                t.setUser(UserService.getInstance().findById((int) userId));


                participants.add(t);
            }
            return participants;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}