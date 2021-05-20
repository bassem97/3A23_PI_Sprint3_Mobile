package com.esprit.PI_Sprint3_Mobile.GUI.responsableCategorie;

import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.list.DefaultListCellRenderer;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.categorie.CategorieListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Categorie;
import com.esprit.PI_Sprint3_Mobile.entities.ResponsableCategorie;
import com.esprit.PI_Sprint3_Mobile.services.*;


public class ResponsableCategorieAddForm extends  Form{
    private TextField tfNom, tfPrenom, tfEmail;
    private ComboBox<Categorie> tfCategorie;
    private Button btnSave;

    public ResponsableCategorieAddForm() {
        super("Ajouter Responsable Categorie", BoxLayout.y());
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new ResponsableCategorieListForm().show());

        tfNom = new TextField();
        tfPrenom = new TextField();
        tfEmail = new TextField();
        tfCategorie = new ComboBox<>();

        CategorieService.getInstance().findAll().forEach(categorie -> tfCategorie.addItem(categorie));
        tfCategorie.setRenderer(new DefaultListCellRenderer(false));

        btnSave = new Button("Ajouter");




        tfNom.setHint("Nom");
        tfPrenom.setHint("Prenom");
        tfEmail.setHint("Email");
        tfCategorie.setHint("Categorie");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfNom),
                new Container(BoxLayout.xCenter()).add(tfPrenom),
                new Container(BoxLayout.xCenter()).add(tfEmail),
                new Container(new BoxLayout(BoxLayout.Y_AXIS)).add(tfCategorie),
                btnSave);

        Validator val = new Validator();
        val.addConstraint(tfNom, new LengthConstraint(10));
        val.addConstraint(tfPrenom, new LengthConstraint(10));

//        val.addConstraint(tfNbPartMax, new NumericConstraint(true));
    }


    private void addActions() {
        btnSave.addActionListener(actionEvent -> {
            ResponsableCategorie responsableCategorie = new ResponsableCategorie();
            responsableCategorie.setNom(tfNom.getText());
            responsableCategorie.setPrenom(tfPrenom.getText());
            responsableCategorie.setEmail(tfEmail.getText());
            responsableCategorie.setCategorie(tfCategorie.getSelectedItem());

            ToastBar.Status status = ToastBar.getInstance().createStatus();
            if (ResponsableCategorieService.getInstance().save(responsableCategorie)) {
                status.setMessage("Responsable Categorie Ajouté");
                new ResponsableCategorieListForm().show();
            } else {
                status.setMessage("Erreur");
            }
            status.show();
        });




    }
}
