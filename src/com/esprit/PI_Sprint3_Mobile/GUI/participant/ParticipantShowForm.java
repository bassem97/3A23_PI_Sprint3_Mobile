package com.esprit.PI_Sprint3_Mobile.GUI.participant;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Participant;
import com.esprit.PI_Sprint3_Mobile.services.EventService;
import com.esprit.PI_Sprint3_Mobile.services.ParticipantService;

import java.io.IOException;
import java.time.LocalDate;

public class ParticipantShowForm extends Form {

    private Resources theme;
    private Participant participant;
    private TextField tfAvis;
    private Button btnUpdate;

    public ParticipantShowForm(Participant participant) {
        super(participant.getEvent().getName(), BoxLayout.y());
        this.participant = participant;
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        addActions();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new ProfileForm(theme).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm().show());

        if (participant.getEvent().getDate().toLocalDate().isAfter(LocalDate.now())) {
            this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "TitleCommand", 5), actionEvent -> {
                if (Dialog.show("Confirmation", "Supprimer votre participation à " + participant.getEvent().getName() + " ?", "Oui", "Non")) {
                    ParticipantService.getInstance().delete(participant.getId());
                    new LoginForm().show();
                }
            });
        }


        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new ParticipantListForm().show());

        tfAvis = new TextField(participant.getAvis(), "Avis");
        tfAvis.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        tfAvis.getAllStyles().setFont(Font.createSystemFont(tfAvis.getUnselectedStyle().getFont().getFace(), Font.STYLE_BOLD, tfAvis.getUnselectedStyle().getFont().getSize()));

        if (participant.getEvent().getDate().toLocalDate().isAfter(LocalDate.now())) {
            tfAvis.setEditable(false);
        }
        btnUpdate = new Button("Modifier");


        this.addAll(new Container(BoxLayout.yCenter()).add(new ImageViewer(theme.getImage("Cracks.jpg"))),
                new Container(BoxLayout.xCenter()).add(tfAvis),
                new Container(BoxLayout.yBottom()).add(btnUpdate)
        );

    }

    private void addActions() {
        btnUpdate.addActionListener(evt -> {
            if (tfAvis.isEditable()) {
                participant.setAvis(tfAvis.getText());
                if (ParticipantService.getInstance().update(participant)) {
                    Dialog.show("Information", "Modification avec Succes", "OK", null);
                    new ParticipantListForm().show();
                } else
                    Dialog.show("Information", "Erreur De Modification", "OK", null);
            }
        });
    }


}
