package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.entities.EventType;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventTypeService {

    private static EventTypeService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<EventType> eventTypes;

    private EventTypeService() {
        req = new ConnectionRequest();
    }

    public static EventTypeService getInstance() {
        if (instance == null) {
            instance = new EventTypeService();
        }
        return instance;
    }

    public ArrayList<EventType> findAll(){
        String url = Statics.BASE_URL + "api/eventtype/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                eventTypes = parseEventTypes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return eventTypes;
    }

    public ArrayList<EventType> parseEventTypes(String jsonText){
        try {
            eventTypes =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                EventType t = new EventType();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setName((obj.get("name").toString()));

                eventTypes.add(t);
            }
            return eventTypes;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
