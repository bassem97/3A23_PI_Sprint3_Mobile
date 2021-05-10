package com.esprit.PI_Sprint3_Mobile.GUI;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.theme.ThemeListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserListForm;

public class Home extends Form {
    public Home(){
        super("Home", BoxLayout.y());
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
        this.getToolbar().addCommandToSideMenu("Forum",null,evt1 -> new ThemeListForm().show());
        this.getToolbar().addCommandToSideMenu("settings",null,evt1 -> evt1.toString());
    }
}
