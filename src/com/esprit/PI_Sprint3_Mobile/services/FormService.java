package com.esprit.PI_Sprint3_Mobile.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.entities.Form;
import com.esprit.PI_Sprint3_Mobile.entities.FormAnswer;
import com.esprit.PI_Sprint3_Mobile.entities.QuestionAnswer;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.utils.Statics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FormService {
    private static FormService instance = null;
    private ConnectionRequest req;
    private String formApiPath;
    private Collection<Form> forms;
    private Collection<FormAnswer> answers;

    private FormService() {
        req = new ConnectionRequest();
        formApiPath = Statics.BASE_URL + "api/form";
    }

    public static FormService getInstance() {
        if (instance == null) {
            instance = new FormService();
        }
        return instance;
    }

    public Collection<Form> findAll() {
        String url = this.formApiPath + "/" + UserSession.getUser().getId();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                forms = parseForms(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return forms;
    }

    public Collection<FormAnswer> getAnswers(int formId) {
        String url = this.formApiPath + "/" + UserSession.getUser().getId() + "/" + formId;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                answers = parseAnswers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return answers;
    }

    public boolean openForm(int formId) {
        String url = this.formApiPath + "/" + UserSession.getUser().getId() + "/" + formId + "/open";
        req.setUrl(url);
        req.setHttpMethod("POST");
        final int[] statusCode = new int[1];
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                statusCode[0] = evt.getResponseCode();
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return statusCode[0] == 200;
    }

    public boolean closeForm(int formId) {
        String url = this.formApiPath + "/" + UserSession.getUser().getId() + "/" + formId + "/close";
        req.setUrl(url);
        req.setHttpMethod("POST");
        final int[] statusCode = new int[1];
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                statusCode[0] = evt.getResponseCode();
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return statusCode[0] == 200;
    }

    public Collection<Form> parseForms(String jsonText) {
        try {
            Collection<Form> forms = new ArrayList<>();
            JSONParser jsonParser = new JSONParser();

            Map<String, Object> tasksListJson = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> object : list) {
                Form form = new Form();
                form.setId((int) Float.parseFloat(object.get("id").toString()));
                form.setTitle((object.get("title").toString()));
                form.setDescription(object.get("description").toString());
                form.setIsOpen(Boolean.parseBoolean(object.get("isOpen").toString()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(object.get("creationDate").toString()
                        .replace("T", " ")
                        .split("\\+")[0], formatter);

                form.setCreationDate(dateTime);

                form.setIsOpen(Boolean.parseBoolean(object.get("isOpen").toString()));

                forms.add(form);
            }
            return forms;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Collection<FormAnswer> parseAnswers(String jsonText) {
        try {
            Collection<FormAnswer> answers = new ArrayList<>();
            JSONParser jsonParser = new JSONParser();

            Map<String, Object> tasksListJson = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> object : list) {
                FormAnswer answer = new FormAnswer();
                answer.setId((int) Float.parseFloat(object.get("id").toString()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(object.get("answerDate").toString()
                        .replace("T", " ")
                        .split("\\+")[0], formatter);
                answer.setAnswerDate(dateTime);

                Collection<QuestionAnswer> formAnswers = new ArrayList<>();
                for (Map<String, Object> questioAnswerMap : (Collection<Map<String, Object>>) object.get("questionAnswers")) {
                    QuestionAnswer questionAnswer = new QuestionAnswer();
                    questionAnswer.setId((int) Float.parseFloat(questioAnswerMap.get("id").toString()));
                    questionAnswer.setQuestion(questioAnswerMap.get("question").toString());
                    questionAnswer.setAnswer(questioAnswerMap.get("answer").toString());
                    questionAnswer.setNumber((int) Float.parseFloat(questioAnswerMap.get("number").toString()));
                    formAnswers.add(questionAnswer);
                }
                answer.setQuestionAnswerCollection(formAnswers);
                User user = new User();
                user.setId((int) Float.parseFloat(((Map<String, Object>) object.get("user")).get("id").toString()));
                user.setEmail(((Map<String, Object>) object.get("user")).get("email").toString());
                answer.setUser(user);

                answers.add(answer);
            }

            return answers;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
