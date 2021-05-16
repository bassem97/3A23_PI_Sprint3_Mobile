/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.esprit.PI_Sprint3_Mobile.Template;

import com.codename1.components.ImageViewer;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.awt.*;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome To ", "WelcomeWhite"),
                new Label("ESPRITGAZINE", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");
        
//        Image profilePic = theme.getImage("user-picture.jpg");
        Image profilePic = theme.getImage("logo.png");
        Image mask = theme.getImage("round-mask.png");
//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        ImageViewer imageViewer = new ImageViewer(profilePic);
        imageViewer.setPreferredSize(new Dimension(300,250));

        Label profilePicLabel = new Label(profilePic, "ProfilePic");
//        profilePicLabel.setMask(mask.createMask());
        
        TextField login = new TextField("bassem.jadoui@esprit.tn", "Login", 20, TextField.EMAILADDR) ;
        TextField password = new TextField("123456", "Password", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Toolbar.setGlobalToolbar(false);
            User user = UserService.getInstance().findAll()
                    .stream()
                    .filter(user1 -> user1.getEmail().equals(login.getText()))
                    .findAny()
                    .orElse(null);
//            System.out.println(user);
            if(user == null)
                Dialog.show("Email error", "User not found ! ", "OK", null );
            else if(!passwordEncoder.matches(password.getText(), user.getPassword()))
                Dialog.show("Password error", "password doesn't  matches ! ", "OK", null );
            else
            {
                System.out.println("LOGIN FORM 88"+ UserSession.setInstace(user));
//                System.out.println("LOGIN FORM 88"+ UserSession.setInstace(user));
//                System.out.println("LOGIN FORM 88"+ UserSession.setInstace(user));
                WalkthruForm walkthruForm = new WalkthruForm(theme);
                walkthruForm.show();
//            new ProfileForm( walkthruForm.theme, UserSession.getUser()).show();
                Toolbar.setGlobalToolbar(true);
            }


        });

        Button googleButton = new Button("SIGN IN/UP WITH GOOGLE");
        googleButton.setUIID("LoginButton");
        googleButton.getAllStyles().setBgColor(Color.ORANGE.getRGB());
        googleButton.addActionListener(evt ->{
            Login gc = GoogleConnect.getInstance();
            gc.setClientId("313181558389-bqpmul5dcm01sdd4dfg1p9d2m18b1ua1.apps.googleusercontent.com");
            gc.setRedirectURI("https://www.codenameone.com/oauth2callback");
            gc.setClientSecret("dgXiO6oTB4QsMf2wLMIu0Jar");
//            gc.setOauth2URL();
//            gc.setOauth2URL("https://accounts.google.com/o/oauth2/v3/token");


//             Sets a LoginCallback listener
            gc.setCallback(new LoginCallback() {
                public void loginSuccessful() {
                    System.out.println("LOGIN SUCCESS !!");
                }

                public void loginFailed(String errorMessage) {
//                    System.out.println(gc.getAccessToken().getToken());
                    System.out.println("LOGIN FAILED !!");
                    System.out.println(errorMessage);
                }
            });

            // trigger the login if not already logged in
            if(!gc.isUserLoggedIn()){
                System.out.println("DO LOGIN");
                gc.doLogin();
            } else {
                // get the token and now you can query the Google API
                String token = gc.getAccessToken().getToken();
                System.out.println(token);
                // NOTE: On Android, this token will be null unless you provide valid
                // client ID and secrets.
            }

        });

        
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
//                imageViewer,
//                welcome,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                googleButton
//                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
//        by.getStyle().setBgColor(Color.WHITE.getRGB());
    }
}
