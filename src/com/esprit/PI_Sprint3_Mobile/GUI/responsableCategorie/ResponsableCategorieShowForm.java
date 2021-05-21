package com.esprit.PI_Sprint3_Mobile.GUI.responsableCategorie;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Categorie;
import com.esprit.PI_Sprint3_Mobile.entities.ResponsableCategorie;
import com.esprit.PI_Sprint3_Mobile.services.CategorieService;
import com.esprit.PI_Sprint3_Mobile.services.ResponsableCategorieService;
import com.esprit.PI_Sprint3_Mobile.services.ThemeService;

import java.io.IOException;

public class ResponsableCategorieShowForm extends Form {
    private Resources res;
    private ResponsableCategorie responsableCategorie;
    private TextField lbNom, lbPrenom, lbEmail;
    private ComboBox<Categorie> lbCategorie;
    Button btnUpdate;

    public ResponsableCategorieShowForm(ResponsableCategorie responsableCategorie) {
        super(responsableCategorie.getNom(), BoxLayout.yCenter());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.responsableCategorie = responsableCategorie;
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
//        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(res, null, null).show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + responsableCategorie.getNom() + " ?", "Oui", "Non" )) {
                ThemeService.getInstance().delete(responsableCategorie.getId());
                new ResponsableCategorieListForm().show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new ResponsableCategorieListForm().show());

        lbNom = new TextField(responsableCategorie.getNom(), "Nom");
        lbNom.getAllStyles().setFgColor(ColorUtil.BLACK);
        lbPrenom = new TextField(responsableCategorie.getPrenom(), "Prenom");
        lbPrenom.getAllStyles().setFgColor(ColorUtil.BLACK);
        lbEmail = new TextField(responsableCategorie.getEmail(), "Email");
        lbEmail.getAllStyles().setFgColor(ColorUtil.BLACK);
        lbCategorie = new ComboBox<>();
        CategorieService.getInstance().findAll().forEach(ct -> lbCategorie.addItem(ct));
        lbCategorie.setSelectedItem(responsableCategorie.getCategorie());
        lbCategorie.getAllStyles().setFgColor(ColorUtil.BLACK);

        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.yCenter()).addAll(lbNom,lbPrenom,lbEmail,lbCategorie), btnUpdate);


    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            responsableCategorie.setNom(lbNom.getText());
            responsableCategorie.setPrenom(lbPrenom.getText());
            responsableCategorie.setEmail(lbEmail.getText());
            responsableCategorie.setCategorie(lbCategorie.getSelectedItem());

            if (ResponsableCategorieService.getInstance().update(responsableCategorie)) {
                Dialog.show("Information", responsableCategorie.getNom() + " Modifié", "OK",null);
                new ResponsableCategorieListForm().show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
}