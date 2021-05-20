package com.esprit.PI_Sprint3_Mobile.GUI.theme;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.services.ThemeService;

import java.io.IOException;

public class ThemeAddForm extends Form {
    private Label lbLibelle;
    private TextField tfLibelle;
    private Button btnSave;
    private Resources res;

    public ThemeAddForm() {
        super("Ajouter Theme", BoxLayout.y());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        addActions();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(res).show();
        });        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_UNDO, "", 5), evt1 -> new ThemeListForm().show());

        tfLibelle = new TextField();
        tfLibelle.getAllStyles().setFgColor(ColorUtil.BLACK);
        btnSave = new Button("Ajouter");


        tfLibelle.setHint("Libelle");
        this.addAll(new Container(BoxLayout.xCenter()).add(tfLibelle),
                btnSave);

        Validator val = new Validator();
        val.addConstraint(tfLibelle, new LengthConstraint(5));
    }

    private void addActions() {
        btnSave.addActionListener(actionEvent -> {
            Theme theme = new Theme();
            theme.setLibelle(tfLibelle.getText());
            theme.setVues(0);
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            if (ThemeService.getInstance().save(theme)) {
                status.setMessage("Theme Ajouté");
                new ThemeListForm().show();
            } else {
                status.setMessage("Erreur");
            }
            status.show();
        });
    }
}
