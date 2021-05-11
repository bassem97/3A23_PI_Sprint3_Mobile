package com.esprit.PI_Sprint3_Mobile.GUI.Sujet;

import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.services.EventService;
import com.esprit.PI_Sprint3_Mobile.services.SujetService;

import java.time.Instant;
import java.time.ZoneId;

public class SujetAddForm extends Form {
    private Label lbDate;
    private TextField tfText;
    private Button btnSave;

    public SujetAddForm() {
        super("Ajouter Sujet", BoxLayout.y());
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

/*
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_UNDO, "TitleCommand", 5), evt1 -> new SujetListForm().show());
*/


        tfText = new TextField();
        btnSave = new Button("Ajouter");


        tfText.setHint("Text");
        this.addAll(new Container(BoxLayout.xCenter()).add(tfText),
                btnSave);

        Validator val = new Validator();
        val.addConstraint(tfText, new LengthConstraint(5));
    }

    private void addActions() {
        btnSave.addActionListener(actionEvent -> {
            Sujet sujet = new Sujet();
            sujet.setText(tfText.getText());
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            if (SujetService.getInstance().save(sujet)) {
                status.setMessage("Sujet Ajouté");
                new EventListForm().show();
            } else {
                status.setMessage("Erreur");
            }
            status.show();
        });
    }
}
