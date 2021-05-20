package com.esprit.PI_Sprint3_Mobile.Template;

import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import java.awt.*;

public class SignUpForm extends Form {
    Container welcome;
    TextField email;
    Button googleButton;


    public SignUpForm() {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        welcome = FlowLayout.encloseCenter(
                new Label("Sign Up to ", "WelcomeWhite"),
                new Label("ESPRITGAZINE", "WelcomeBlue")
        );
        addGUI();
        addActions();
    }

    private void addActions() {

        googleButton.addActionListener(evt -> {
            String pattern = "/^[a-zA-Z]+\\.[a-zA-Z]+@((e|E)sprit.(t|T)n)$/";
        });

    }

    private void addGUI() {
        Label loginIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);


        email = new TextField( "","person@esprit.tn", 20, TextField.EMAILADDR) ;
        googleButton = new Button("SIGN UP");
        googleButton.setUIID("LoginButton");
        googleButton.getAllStyles().setBgColor(Color.ORANGE.getRGB());


        Container by = BoxLayout.encloseY(
                welcome,
                BorderLayout.center(email).
                        add(BorderLayout.WEST, loginIcon),
                googleButton
                );
        add(BorderLayout.CENTER, by);
    }
}
