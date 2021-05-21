package com.esprit.PI_Sprint3_Mobile.GUI.categorie;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.*;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Categorie;
import com.esprit.PI_Sprint3_Mobile.services.*;

import java.io.IOException;

public class CategorieShowForm extends Form {

    private Resources res;
    private Categorie categorie;
    private TextField  lbTitre, lbDescription;
    Button btnUpdate;

    public CategorieShowForm(Categorie categorie) {
        super(categorie.getTitre(), BoxLayout.yCenter());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.categorie = categorie;
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
//        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(res, null, null).show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + categorie.getTitre() + " ?", "Oui", "Non" )) {
                CategorieService.getInstance().delete(categorie.getId());
                new CategorieListForm().show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new CategorieListForm().show());

        lbTitre = new TextField(categorie.getTitre(), "Titre");
        lbTitre.getAllStyles().setFgColor(ColorUtil.BLACK);
        lbDescription = new TextField(categorie.getDescription(), "Description");
        lbTitre.getAllStyles().setFgColor(ColorUtil.BLACK);
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.yCenter()).addAll(lbTitre,lbDescription), btnUpdate);


    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            categorie.setTitre(lbTitre.getText());
            categorie.setDescription(lbDescription.getText());
            if (CategorieService.getInstance().update(categorie)) {
                Dialog.show("Information", categorie.getTitre() + " Modifié", "OK",null);
                new CategorieListForm().show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
}