package com.esprit.PI_Sprint3_Mobile.GUI;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserListForm;
import sun.misc.Resource;

import java.io.IOException;

public class Home extends Form {

    public Resources theme;

    public Home(){
        super("Home", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUI();
        addAction();

    }

    private void addAction() {
    }

    private void addGUI() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new LoginForm().show());
        this.getToolbar().addCommandToSideMenu("HOME",null,evt -> new Home().show());
        this.getToolbar().addCommandToSideMenu("Users",null,evt1 -> new UserListForm().show());
        this.getToolbar().addCommandToSideMenu("Events",null,evt1 -> new EventListForm().show());
        this.getToolbar().addCommandToSideMenu("Articles",null,evt1 -> evt1.toString());
        this.getToolbar().addCommandToSideMenu("settings",null,evt1 -> evt1.toString());
    }
}
