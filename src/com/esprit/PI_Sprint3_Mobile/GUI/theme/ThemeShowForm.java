package com.esprit.PI_Sprint3_Mobile.GUI.theme;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.services.ThemeService;

import java.io.IOException;

public class ThemeShowForm extends Form {
    private Theme theme;
    private TextField tfLibelle;
    private Button btnUpdate;
    private Resources res;

    public ThemeShowForm(Theme theme) {
        super(theme.getLibelle(), BoxLayout.yCenter());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.theme = theme;
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm().show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + theme.getLibelle() + " ?", "Oui", "Non" )) {
                ThemeService.getInstance().delete(theme.getId());
                new ThemeListForm().show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new ThemeListForm().show());

        tfLibelle = new TextField(theme.getLibelle(), "Libelle");
        tfLibelle.getAllStyles().setFgColor(ColorUtil.BLACK);
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfLibelle), btnUpdate);

    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            theme.setLibelle(tfLibelle.getText());
            if (ThemeService.getInstance().update(theme)) {
                Dialog.show("Information", theme.getLibelle() + " Modifié", "OK",null);
                new ThemeListForm().show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
}
