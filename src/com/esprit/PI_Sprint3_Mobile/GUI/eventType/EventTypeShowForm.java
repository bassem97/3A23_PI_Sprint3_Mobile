package com.esprit.PI_Sprint3_Mobile.GUI.eventType;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.EventType;
import com.esprit.PI_Sprint3_Mobile.services.EventTypeService;

public class EventTypeShowForm extends Form {

    private EventType eventType;
    private TextField tfName;
    private Button btnUpdate;

    public EventTypeShowForm(EventType eventType) {
        super(eventType.getName(), BoxLayout.yCenter());
        this.eventType = eventType;
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm().show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "TitleCommand", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + eventType.getName() + " ?", "Oui", "Non" )) {
                EventTypeService.getInstance().delete(eventType.getId());
                new EventTypeListForm().show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new EventTypeListForm().show());

        tfName = new TextField(eventType.getName(), "Nom");
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfName), btnUpdate);

    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            eventType.setName(tfName.getText());
            if (EventTypeService.getInstance().update(eventType)) {
                Dialog.show("Information", eventType.getName() + " Modifié", "OK",null);
                new EventTypeListForm().show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
}
