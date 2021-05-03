package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.services.UserService;

public class UserListForm extends Form {

    private Resources theme;

    public UserListForm (){
        super("Users",BoxLayout.y());
        this.theme = theme;
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
            this.add(item(user.getImage(),user.getUsername(),user.getEmail()));
        });
    }

    private Container item(String image, String username, String email){
        Container global = new Container(BoxLayout.x());
//        ImageViewer imgFlag = new ImageViewer(theme.getImage(flag));
//        ImageViewer imgFlag = new ImageViewer(theme.getImage("person.png"));
//        System.out.println(imgFlag.getName());
        Label lbUsername = new Label(username);
        Label lbEmail = new Label(email);
        Container labels = new Container(BoxLayout.y()).addAll(lbUsername, lbEmail);
        global.addAll(labels);

        lbUsername.addPointerPressedListener(evt -> {
            UserShowForm userShowForm = new UserShowForm();
            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 3);
            userShowForm.getToolbar().addCommandToLeftBar("Return to list ",icon,evt1 -> this.show());
            userShowForm.show();
        });
        global.setLeadComponent(lbUsername);

        return global;
    }
}
