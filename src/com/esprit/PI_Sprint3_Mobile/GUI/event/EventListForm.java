package com.esprit.PI_Sprint3_Mobile.GUI.event;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserShowForm;
import com.esprit.PI_Sprint3_Mobile.services.EventService;

import java.io.IOException;

public class EventListForm extends Form {

    private Resources theme;

    public EventListForm() {
        super("Evènements", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "TitleCommand", 5), evt1 -> new EventAddForm().show());

        EventService.getInstance().findAll().forEach(event -> {
            this.add(item(event.getName(),event.getDescription(),event.getDate().toLocalDate().toString()));
        });
    }

    private Container item(String name, String description, String date){
        ImageViewer imageViewer = new ImageViewer(theme.getImage("Cracks.jpg"));
        imageViewer.setPreferredSize(new Dimension(300,300));
        Container global = new Container(BoxLayout.x());
        Label lbName = new Label(name);
        Label lbDescription = new Label(description);
        Label lbDate = new Label(date);
        Container labels = new Container(BoxLayout.y()).addAll(lbName, lbDescription, lbDate);
        global.addAll(imageViewer, labels);

        /*lbName.addPointerPressedListener(evt -> {
            UserShowForm userShowForm = new UserShowForm();
            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 3);
            userShowForm.getToolbar().addCommandToLeftBar("Return to list ",icon,evt1 -> this.show());
            userShowForm.show();
        });*/
        global.setLeadComponent(lbName);

        return global;
    }
}
