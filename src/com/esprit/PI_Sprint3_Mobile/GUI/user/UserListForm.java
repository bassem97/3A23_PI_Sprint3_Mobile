package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class UserListForm extends Form {

    public Resources theme;
    ArrayList<User> users;


    public UserListForm (){

        super("",BoxLayout.y());
//        super("Users",BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        users = new UserService().findAll();
        addGUIs();
        addActions();
    }

    private void addActions() {
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        FontImage Home = FontImage.createMaterial(FontImage.MATERIAL_HOME_FILLED, "TitleCommand", 5);
        this.getToolbar().addCommandToLeftBar(null,Home,evt1 -> new ProfileForm(theme).show());

        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new LoginForm(theme).show());

        users.forEach(user ->this.add(item(user.getImage(),user.getUsername(),user.getEmail(),user)));

        this.getToolbar().addSearchCommand(e ->{
            String text = (String)e.getSource();
            if (text != null && text.length() != 0){
                this.removeAll();
                users
                        .stream()
                        .filter(user -> user.getNom().toLowerCase().contains(text.toLowerCase()) || user.getUsername().toLowerCase().contains(text.toLowerCase()) || user.getEmail().toLowerCase().contains(text.toLowerCase()))
                        .forEach(user ->{
                                this.add(item(user.getImage(),user.getUsername(),user.getEmail(),user));
                        } );
            }else{
                this.removeAll();
                users.forEach(user -> this.add(item(user.getImage(),user.getUsername(),user.getEmail(),user)));
            }

        });

    }

    private Container item(String image, String username, String email, User user) {
        Container global = new Container(BoxLayout.x());
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, user.getImage(), "");

        Image img;
        ImageViewer imageViewer = null;
        if(image == null || image.equals(""))
            img = theme.getImage("person.png");
        else if(image.contains("google")){
            String url = image;
            img = URLImage.createToStorage(placeholder,username,url);
            imageViewer = new ImageViewer(img);
            imageViewer.setPreferredSize(new Dimension(400,400));
        }else
            img = theme.getImage(username);




        Label lbUsername = new Label(username);
        Label lbEmail = new Label(email);
        lbUsername.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbUsername.getAllStyles().setFont(Font.createSystemFont(lbUsername.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbUsername.getUnselectedStyle().getFont().getSize()));
        Image delete = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "TitleCommand", 5).toImage();
        Container labels = new Container(BoxLayout.y()).addAll(lbUsername, lbEmail);
//        System.out.println(theme.getImage("person.png").getImageName());
        if(image.contains("google"))
            global.add(imageViewer);
        else
            global.add(background);
        global.addAll(labels);

        lbUsername.addPointerReleasedListener(evt -> {
            UserShowForm userShowForm = new UserShowForm(username,user);
            userShowForm.show();
        });
        global.setLeadComponent(lbUsername);

        return global;
    }
}
