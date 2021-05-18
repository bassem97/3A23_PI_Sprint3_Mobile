package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {

    private static EventService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Event> events;

    private EventService() {
        req = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public ArrayList<Event> findAll(){
        String url = Statics.BASE_URL + "api/event/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public boolean save(Event event) {
        String url = Statics.BASE_URL + "api/event/new/eventtype/" + event.getEventType().getId();
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", event.getName());
            hashMap.put("nb_part_max", String.valueOf(event.getNb_part_max()));
            hashMap.put("description", event.getDescription());
            hashMap.put("date", event.getDate());
            hashMap.put("club_id", event.getClub());
            hashMap.put("image", event.getImage());
            hashMap.put("eventType", event.getEventType());
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
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(Event event) {
        // String url = Statics.BASE_URL + "api/event/" + event.getId() + "/update";
        String url = Statics.BASE_URL + "api/event/" + event.getId() + "/update?name=" + event.getName() + "&description=" + event.getDescription() + "&nb_part_max=" + event.getNb_part_max();
        req.setUrl(url);
        //req.setHttpMethod("PUT");
        req.setContentType("application/json");
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", event.getId());
            hashMap.put("name", event.getName());
            hashMap.put("nb_part_max", String.valueOf(event.getNb_part_max()));
            hashMap.put("description", event.getDescription());
            hashMap.put("date", event.getDate());
            hashMap.put("club_id", event.getClub());
            hashMap.put("image", event.getImage());
            hashMap.put("event_type_id", event.getEventType());
            // req.setRequestBody(Result.fromContent(hashMap).toString());
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
        String url = Statics.BASE_URL + "api/event/" + id + "/delete";
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





    public ArrayList<Event> parseEvents(String jsonText){
        try {
            events =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Event t = new Event();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setName((obj.get("name").toString()));
                t.setDescription(obj.get("description").toString());
                t.setImage(obj.get("image").toString());
                t.setNb_part_max((int)Float.parseFloat(obj.get("nbPartMax").toString()));
                t.setEventType(EventTypeService.getInstance().parseEventTypes(new String(req.getResponseData())).get(0));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(obj.get("date").toString()
                        .replace("T", " ")
                        .split("\\+")[0], formatter);
                t.setDate(dateTime);

                events.add(t);
            }
            return events;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
