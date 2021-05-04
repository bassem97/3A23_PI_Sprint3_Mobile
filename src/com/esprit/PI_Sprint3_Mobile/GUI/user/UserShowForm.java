package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.entities.User;

import java.io.IOException;

public class UserShowForm extends Form {

    private User user;
    public Resources theme;

    private  Label Nom = new Label();
    private  Label Prenom = new Label();
    private  Label email = new Label();
    private  Label creationDate = new Label();


    public UserShowForm(String title,User user) {
        super(title,new FlowLayout(Component.CENTER,Component.CENTER));
        this.user = user;
        Nom.setText("Nom : "+user.getNom());
        Prenom.setText("Prenom : "+user.getPrenom());
        email.setText("Email : "+user.getEmail());
        creationDate.setText("Joined at  : "+user.getCreation_date().toString());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUI();
        addActions();
    }

    private void addActions() {
    }

    private void addGUI() {
        Container ct = new Container(BoxLayout.y());
        Image img;
        EncodedImage placeHolder = EncodedImage.createFromImage(theme.getImage("person.png"), false);
        if(user.getImage() == null || user.getImage().equals(""))
            img = theme.getImage("person.png");
        else if(user.getImage().contains("google")){
            System.out.println("HNEEEEE");
            String url = user.getImage();
            img = URLImage.createToStorage(placeHolder,user.getUsername(),url);
        }else
            img = theme.getImage(user.getUsername());

        Container ct1 = new Container(BoxLayout.x());
        ct1.addAll(Nom,Prenom);



        ct.add(img);
        this.addAll(ct,ct1,email,creationDate);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
