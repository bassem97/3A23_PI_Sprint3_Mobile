package com.esprit.PI_Sprint3_Mobile.Template;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.utils.Mailer;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class SignUpForm extends Form {
    Container welcome;
    TextField tfEmail;
    Button googleButton;
    private Resources theme;


    public SignUpForm() {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        welcome = FlowLayout.encloseCenter(
                new Label("Sign Up to ", "WelcomeWhite"),
                new Label("ESPRITGAZINE", "WelcomeBlue")
        );
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUI();
        addActions();
    }

    private void addActions() {

        googleButton.addActionListener(evt -> {
            String pattern = "[a-zA-Z]+\\.[a-zA-Z]+@(([eE])sprit.([tT])n)$";
            if(!tfEmail.getText().matches(pattern))
                Dialog.show("Email Error", "Email must be belongs to ESPRIT organization !", "OK",null);
//            else if(UserService.getInstance().findAll().stream().anyMatch(user -> user.getEmail().equals(email.getText()))){
//                if(Dialog.show("Email Exist", "You already signed in try to LOG in !", "OK","Cancel"))
//                    new LoginForm(theme).show();
            else{
                try {
                    int randomNum = ThreadLocalRandom.current().nextInt(100000, 999998 + 1);
                    Mailer.sendMail(tfEmail.getText(),randomNum,null);
                    new CodeVerificationForm(randomNum, tfEmail.getText()).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        });

    }

    private void addGUI() {
        Label loginIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);


        tfEmail = new TextField( "","person@esprit.tn", 20, TextField.EMAILADDR) ;
        googleButton = new Button("SIGN UP");
        googleButton.setUIID("LoginButton");
        googleButton.getAllStyles().setBgColor(Color.ORANGE.getRGB());


        Container by = BoxLayout.encloseY(
                welcome,
                BorderLayout.center(tfEmail).
                        add(BorderLayout.WEST, loginIcon),
                googleButton
                );
        add(BorderLayout.CENTER, by);
    }

    private class CodeVerificationForm extends Form{
        Container welcome;
        TextField verification_code;
        Button continue_button;
        String random_num;
        Button resend;
        String email;
        public CodeVerificationForm(int randomNum, String email) {
            super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            setUIID("LoginForm");
            this.email = email;
            this.random_num = String.valueOf(randomNum);
            welcome = FlowLayout.encloseCenter(
//                    new Label("Verification code is sent to you email !", "WelcomeWhite"),
                    new Label("Please check you email !", "WelcomeWhite")
            );
            try {
                theme = Resources.openLayered("/theme");
            } catch (IOException e) {
                e.printStackTrace();
            }
            addGUI2();
            addActions2();
        }

        private void addActions2() {
            continue_button.addActionListener(evt -> {
                if (verification_code.getText().equals(""))
                    Dialog.show("ERROR",  " type the verification code to continue", "OK",null);
                else if(!verification_code.getText().equals(random_num))
                    Dialog.show("ERROR",  "Wrong verification code ! check it again", "OK",null);
                else
                    new ContinueRegistrationForm(email).show();


            });

            resend.addActionListener(evt ->{
                int randomNum = ThreadLocalRandom.current().nextInt(100000, 999998 + 1);
                try {
                    Mailer.sendMail(tfEmail.getText(),randomNum,null);
                    this.random_num = String.valueOf(randomNum);
                    Dialog.show("Code Sent !",  "Check again you email !", "OK",null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        private void addGUI2() {
            verification_code = new TextField( "","verification number", 20, TextField.NUMERIC) ;
            continue_button = new Button("Continue Registration");
            continue_button.setUIID("LoginButton");
            continue_button.getAllStyles().setBgColor(Color.ORANGE.getRGB());
            Label numberIcon = new Label("", "TextField");
            numberIcon.getAllStyles().setMargin(RIGHT, 0);
            FontImage.setMaterialIcon(numberIcon, FontImage.MATERIAL_CONFIRMATION_NUMBER, 3);

            resend = new Button("Re-send verification code");
            resend.setUIID("CreateNewAccountButton");



            Container by2 = BoxLayout.encloseY(
                    welcome,
                    BorderLayout.center(verification_code).
                            add(BorderLayout.WEST, numberIcon),
                    continue_button,
                    resend
            );
            add(BorderLayout.CENTER, by2);
        }
      
    }
}
