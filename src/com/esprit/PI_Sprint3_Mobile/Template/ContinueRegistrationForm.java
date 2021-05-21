package com.esprit.PI_Sprint3_Mobile.Template;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;
import com.esprit.PI_Sprint3_Mobile.utils.FileChooser.FileChooser;
import com.esprit.PI_Sprint3_Mobile.utils.Mailer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

public class ContinueRegistrationForm extends Form {

    Container welcome;
    Container labels;
    Button upload;
    Button continuee;
    TextField password;
    private Resources theme;
    String email;
    String imageName = "";
    private Container uploads;

    public ContinueRegistrationForm(String email) {
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        super(new FlowLayout(Component.CENTER,Component.CENTER));
        setUIID("LoginForm");
        this.email = email;
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUI();
        addActions();
    }

    private void addActions() {
        upload.addActionListener(evt -> {
            if (FileChooser.isAvailable()) {
                // FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(false, ".jpg, .jpeg, .png", (ActionEvent e2) -> {
                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);
                            add(logo);
                            if (file.lastIndexOf(".") > 0) {
                                StringBuilder hi = new StringBuilder(file);
                                if (file.startsWith("file://")) {
                                    hi.delete(0, 7);
                                }
                                Log.p(hi.toString());
                                String namePic = saveFileToDevice(file);
                                this.imageName = namePic;

                                revalidate();


                            }
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + imageName;

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }
                    }
                });
            }
        });


        continuee.addActionListener(evt ->{
            if (password.getText().equals("") || imageName.equals(""))
                Dialog.show("Information",  "please provide and choose password to continue", "OK",null);
            else{
                User user = new User();
                user.setEmail(email);
                user.setNom(email.substring(0,email.indexOf(".")));
                user.setPrenom(email.substring(email.indexOf(".")+1,email.indexOf("@")));
                user.setUsername(user.getNom()+"."+user.getPrenom());
                user.setImage(imageName);
                user.setPassword(new BCryptPasswordEncoder().encode(password.getText()));
                user.setCreation_date(LocalDateTime.now());

                if(UserService.getInstance().save(user)) {
                    try {
                        Mailer.sendMail(email,0,password.getText());
                        new LoginForm(theme,email,password.getText()).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }
    private String saveFileToDevice(String file) {
        int index = file.lastIndexOf("/");
        file = file.substring(index + 1);
        return file;
    }

    private void addGUI() {
        password = new TextField(null, "Password", 20, TextField.EMAILADDR) ;
        password.getAllStyles().setMargin(LEFT, 0);
        Label passwordIcon = new Label("", "TextField");
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        upload = new Button("UPLOAD IMAGE");
        continuee = new Button("Finish registration");
        continuee.setUIID("LoginButton");
        upload.setUIID("LoginButton");
        continuee.getAllStyles().setBgColor(Color.ORANGE.getRGB());

        labels = FlowLayout.encloseCenter(
                new Label("Continues Registration", "WelcomeBlue"));

//        uploads = new Container(BoxLayout.xCenter()).add(upload);
//        uploads = new Container();
//        uploads.setLayout(new BorderLayout());
//        uploads.addComponent(BorderLayout.EAST, upload);


        welcome = FlowLayout.encloseCenter(
                labels,
                upload,
                password,

                continuee
        );
        add(welcome);
    }
}
