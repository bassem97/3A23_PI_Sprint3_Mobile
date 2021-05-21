package com.esprit.PI_Sprint3_Mobile.GUI.commentaire;

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
import com.esprit.PI_Sprint3_Mobile.entities.Article;
import com.esprit.PI_Sprint3_Mobile.entities.Commentaire;
import com.esprit.PI_Sprint3_Mobile.services.CommentaireService;

import java.io.IOException;


public class CommAdd extends Form {
    private Label lbDate;
    private TextField tfText;
    private Button btnSave;
    private Resources res;

    public CommAdd (Article article) {
        super("Ajouter Commentaire", BoxLayout.y());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        addActions(article);
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(res).show());
        //this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(res).show();
        });
/*
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_UNDO, "TitleCommand", 5), evt1 -> new SujetListForm().show());
*/

        tfText = new TextField();
        btnSave = new Button("Ajouter");


        tfText.setHint("Text");
        tfText.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.addAll(new Container(BoxLayout.xCenter()).add(tfText),
                btnSave);

        Validator val = new Validator();
        val.addConstraint(tfText, new LengthConstraint(5));
    }

    private void addActions(Article  article) {
        btnSave.addActionListener(actionEvent -> {
            Commentaire com = new  Commentaire();
            com.setArticle(article);
            com.setCorps(tfText.getText());
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            if (CommentaireService.getInstance().save(com)) {
                Dialog.show("Information", "Post Ajouté", "OK", null);
                new CommList(article).show();
            } else {
                status.setMessage("Erreur");
            }
            status.show();
        });
    }
}