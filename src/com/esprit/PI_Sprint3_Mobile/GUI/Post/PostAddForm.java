package com.esprit.PI_Sprint3_Mobile.GUI.Post;

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
import com.esprit.PI_Sprint3_Mobile.entities.Post;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.services.PostService;

import java.io.IOException;

public class PostAddForm extends Form {
        private Label lbDate;
        private TextField tfText;
        private Button btnSave;
        private Resources res;

        public PostAddForm(Sujet sujet) {
            super("Ajouter Post", BoxLayout.y());
            try {
                res = Resources.openLayered("/theme");
            } catch (IOException e) {
                e.printStackTrace();
            }
            addGUIs();
            addActions(sujet);
        }

        private void addGUIs() {
            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
            this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(res).show());
            this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
                UserSession.logOut();
                new LoginForm(res, null, null).show();
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

        private void addActions(Sujet sujet) {
            btnSave.addActionListener(actionEvent -> {
                Post post = new Post();
                post.setSujet(sujet);
                post.setText(tfText.getText());
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                if (PostService.getInstance().save(post)) {
                    Dialog.show("Information", "Post Ajouté", "OK", null);
                    new PostListForm(sujet).show();
                } else {
                    status.setMessage("Erreur");
                }
                status.show();
            });
        }
    }

