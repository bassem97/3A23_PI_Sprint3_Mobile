package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;
import sun.misc.Resource;

import java.io.IOException;

public class UserListForm extends Form {

    public Resources theme;


    public UserListForm (){

        super("Users",BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        addActions();
    }

    private void addActions() {
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToLeftBar("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new LoginForm().show());

        new UserService().findAll().forEach(user -> {
            System.out.println(user);
            try {
                this.add(item(user.getImage(),user.getUsername(),user.getEmail(),user));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Container item(String image, String username, String email, User user) throws IOException {
        Container global = new Container(BoxLayout.x());
        EncodedImage placeHolder = EncodedImage.createFromImage(theme.getImage("person.png"), false);
        Image img;
        if(image == null || image.equals(""))
            img = theme.getImage("person.png");
        else if(image.contains("google")){
            String url = image;
            img = URLImage.createToStorage(placeHolder,username,url);
        }else
            img = theme.getImage(username);

        ImageViewer imageViewer = new ImageViewer(img);
        imageViewer.setWidth(30);
        imageViewer.setHeight(20);

        Label lbUsername = new Label(username);
        Label lbEmail = new Label(email);
        Container labels = new Container(BoxLayout.y()).addAll(lbUsername, lbEmail);
//        System.out.println(theme.getImage("person.png").getImageName());
        global.addAll(imageViewer,labels);

        lbUsername.addPointerPressedListener(evt -> {
            UserShowForm userShowForm = new UserShowForm(username,user);

            userShowForm.show();
        });
        global.setLeadComponent(lbUsername);

        return global;
    }
}
