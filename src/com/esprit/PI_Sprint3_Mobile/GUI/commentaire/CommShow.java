package com.esprit.PI_Sprint3_Mobile.GUI.commentaire;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Commentaire;
import com.esprit.PI_Sprint3_Mobile.services.CommentaireService;

import java.io.IOException;

public class CommShow extends Form {

    private Commentaire post;
    private TextField tfText;
    private Button btnUpdate;
    private Resources res;

    public CommShow(Commentaire post) {
        super(post.getCorps(), BoxLayout.yCenter());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.post = post;
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new ProfileForm(res).show());
        //this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(res).show();
        });
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if (Dialog.show("Confirmation", "Supprimer " + post.getCorps() + " ?", "Oui", "Non")) {
                CommentaireService.getInstance().delete(post.getRef());
                new CommList(post.getArticle()).show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new CommList(post.getArticle()).show());

        tfText = new TextField(post.getCorps(), "Text");
        tfText.getAllStyles().setFgColor(ColorUtil.BLACK);
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfText), btnUpdate);

    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            post.setCorps(tfText.getText());
            if (CommentaireService.getInstance().update(post)) {
                Dialog.show("Information", post.getCorps() + " Modifié", "OK", null);
                new CommList(post.getArticle()).show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK", null);
            }
        });
    }
}