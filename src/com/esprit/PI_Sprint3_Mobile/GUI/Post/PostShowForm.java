package com.esprit.PI_Sprint3_Mobile.GUI.Post;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Post;
import com.esprit.PI_Sprint3_Mobile.services.PostService;

import java.io.IOException;

public class PostShowForm extends Form {
    private Post post;
    private TextField tfText;
    private Button btnUpdate;
    private Resources res;

    public PostShowForm(Post post) {
        super(post.getText(), BoxLayout.yCenter());
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
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(res).show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + post.getText() + " ?", "Oui", "Non" )) {
                PostService.getInstance().delete(post.getId());
                new PostListForm(post.getSujet()).show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new PostListForm(post.getSujet()).show());

        tfText = new TextField(post.getText(), "Text");
        tfText.getAllStyles().setFgColor(ColorUtil.BLACK);
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfText), btnUpdate);

    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            post.setText(tfText.getText());
            if (PostService.getInstance().update(post)) {
                Dialog.show("Information", post.getText() + " Modifié", "OK",null);
                new PostListForm(post.getSujet()).show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
}