package com.esprit.PI_Sprint3_Mobile.GUI.event;

import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.services.EventService;

import java.time.Instant;
import java.time.ZoneId;

public class EventAddForm extends Form {

    private Label lbDate;
    private TextField tfName, tfDescription, tfNbPartMax;
    private Picker datePicker;
    private Button btnSave;

    public EventAddForm() {
        super("Ajouter Evènement", BoxLayout.y());
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_UNDO, "TitleCommand", 5), evt1 -> new EventListForm().show());

        lbDate = new Label("Date");
        tfName = new TextField();
        tfDescription = new TextField();
        tfNbPartMax = new TextField();
        datePicker = new Picker();
        btnSave = new Button("Ajouter");


        tfName.setHint("Nom");
        tfDescription.setHint("Description");
        tfNbPartMax.setHint("Nombre de Participants");
        this.addAll(new Container(BoxLayout.xCenter()).add(tfName),
                new Container(BoxLayout.xCenter()).add(tfDescription),
                new Container(BoxLayout.xCenter()).add(tfNbPartMax),
                new Container(BoxLayout.xCenter()).addAll(lbDate, datePicker),
                btnSave);

        Validator val = new Validator();
        val.addConstraint(tfName, new LengthConstraint(5));
        val.addConstraint(tfNbPartMax, new NumericConstraint(true));
    }

    private void addActions() {
        btnSave.addActionListener(actionEvent -> {
            Event event = new Event();
            event.setName(tfName.getText());
            event.setDescription(tfDescription.getText());
            event.setNb_part_max(Integer.parseInt(tfNbPartMax.getText()));
            event.setDate(Instant.ofEpochMilli(datePicker.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            if (EventService.getInstance().save(event)) {
                status.setMessage("Evènement Ajouté");
                new EventListForm().show();
            } else {
                status.setMessage("Erreur");
            }
            status.show();
        });
    }
}