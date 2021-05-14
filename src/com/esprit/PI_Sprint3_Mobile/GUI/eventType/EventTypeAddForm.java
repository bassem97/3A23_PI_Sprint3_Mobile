package com.esprit.PI_Sprint3_Mobile.GUI.eventType;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.EventType;
import com.esprit.PI_Sprint3_Mobile.services.EventTypeService;

public class EventTypeAddForm extends Form {

    private TextField tfName;
    private Button btnSave;

    public EventTypeAddForm() {
        super("Ajouter Type Evènement", BoxLayout.yCenter());
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new EventTypeListForm().show());

        tfName = new TextField("", "Nom");
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
