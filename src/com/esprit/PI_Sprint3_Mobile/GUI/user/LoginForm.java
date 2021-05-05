package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;

import java.io.IOException;

public class LoginForm extends Form {
    private TextField tfEmail;
    private TextField tfPassword;
    private Button btnLogin;

    public Resources theme;

    public LoginForm(){
        super("Login", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            User user = UserService.getInstance().findByEmail(tfEmail.getText());
//            if(user == null)
////                Dialog.show("Confirmation", "Voulez-vous choisir " + pays + " ?", "OK", null );
//                Dialog.show("ERROR", "User does'nt exist", "OK", null);
//            else if()
            Home home = new Home();
            home.show();
        });
    }


}
