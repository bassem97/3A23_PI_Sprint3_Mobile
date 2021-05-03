package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;

public class LoginForm extends Form {
    private TextField tfEmail;
    private TextField tfPassword;
    private Button btnLogin;

    public LoginForm(){
        super("Login", BoxLayout.y());
        addGUI();
        addAction();
    }

    public void addGUI(){
        this.getToolbar().addCommandToLeftBar("",null,evt1 -> new LoginForm().show());

        tfEmail = new TextField(null,"Email");
        tfPassword = new TextField(null,"Password");
        btnLogin = new Button("Login");
        this.addAll(tfEmail,tfPassword,btnLogin);
    }
    public void addAction(){
        btnLogin.addActionListener(event -> {
            Home home = new Home();
            home.show();
        });
    }
}
