package com.esprit.PI_Sprint3_Mobile.GUI.eventType;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.EventType;
import com.esprit.PI_Sprint3_Mobile.services.EventTypeService;

import java.io.IOException;

public class EventTypeAddForm extends Form {

    private TextField tfName;
    private Button btnSave;
    private Resources theme;

    public EventTypeAddForm() {
        super("Ajouter Type Evènement", BoxLayout.yCenter());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(theme, null, null).show());

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new EventTypeListForm().show());

        tfName = new TextField("", "Nom");
        tfName.getAllStyles().setFgColor(ColorUtil.BLACK);
        btnSave = new Button("Ajouter");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfName), btnSave);

    }

    private void addActions() {
        btnSave.addActionListener(actionEvent -> {
            EventType eventType = new EventType();
            eventType.setName(tfName.getText());
            if (EventTypeService.getInstance().save(eventType)) {
                Dialog.show("Information", eventType.getName() + " Ajouté", "OK",null);
                new EventTypeListForm().show();
            } else {
                Dialog.show("Erreur", "Erreur D'ajout", "OK",null);
            }
        });
    }

}
