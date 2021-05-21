package com.esprit.PI_Sprint3_Mobile.GUI.categorie;

import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Categorie;
import com.esprit.PI_Sprint3_Mobile.services.CategorieService;

public class CategorieAddForm extends Form {

    private TextField tfTitre, tfDescription;
    private Button btnSave;

    public CategorieAddForm() {
        super("Ajouter Categorie", BoxLayout.y());
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());


        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new CategorieListForm().show());

        tfTitre = new TextField();
        tfDescription = new TextField();

        btnSave = new Button("Ajouter");


        tfTitre.setHint("Titre");
        tfDescription.setHint("Description");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfTitre),
                new Container(BoxLayout.xCenter()).add(tfDescription),
                btnSave);

        Validator val = new Validator();
        val.addConstraint(tfTitre, new LengthConstraint(10));
        val.addConstraint(tfTitre, new NumericConstraint(false));
        val.addConstraint(tfDescription, new LengthConstraint(25));

    }

    private void addActions() {
        btnSave.addActionListener(actionEvent -> {
            Categorie categorie = new Categorie();
            categorie.setTitre(tfTitre.getText());
            categorie.setDescription(tfDescription.getText());
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            if (CategorieService.getInstance().save(categorie)) {
                status.setMessage("Categorie Ajouté");
                new CategorieListForm().show();
            } else {
                status.setMessage("Erreur");
            }
            status.show();
        });




    }
}