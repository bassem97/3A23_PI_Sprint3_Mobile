package com.esprit.PI_Sprint3_Mobile.GUI.Sujet;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.services.SujetService;

import java.io.IOException;

public class SujetShowForm extends Form {
    private Sujet sujet;
    private TextField tfText;
    private Button btnUpdate;
    private Resources res;

    public SujetShowForm(Sujet sujet) {
        super(sujet.getText(), BoxLayout.yCenter());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sujet = sujet;
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(res).show());

            this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
                if (Dialog.show("Confirmation", "Supprimer " + sujet.getText() + " ?", "Oui", "Non")) {
                    SujetService.getInstance().delete(sujet.getId());
                    new SujetListForm(sujet.getTheme()).show();
                }
            });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new SujetListForm(sujet.getTheme()).show());

        tfText = new TextField(sujet.getText(), "Text");
        tfText.getAllStyles().setFgColor(ColorUtil.BLACK);
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfText), btnUpdate);

    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            sujet.setText(tfText.getText());
            if (SujetService.getInstance().update(sujet)) {
                Dialog.show("Information", sujet.getText() + " Modifié", "OK",null);
                new SujetListForm(sujet.getTheme()).show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
}

