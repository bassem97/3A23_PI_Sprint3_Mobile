package com.esprit.PI_Sprint3_Mobile.GUI.Post;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.Sujet.SujetAddForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.services.PostService;
import com.esprit.PI_Sprint3_Mobile.services.SujetService;

public class PostListForm extends Form {
    private Sujet sujet;
    public PostListForm(Sujet sujett) {
        super("Posts", BoxLayout.y());

        addGUIs(sujett);
    }


    private void addGUIs(Sujet sujet) {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "TitleCommand", 5), evt1 -> new PostAddForm(sujet).show());

        PostService.getInstance()
                .findAll()
                .stream()
                .filter(post->post.getSujet().getId()==sujet.getId())
                .forEach(post -> {
                    this.add(item(post.getText()));
                });
    }


    private Container item(String text){
        Container global = new Container(BoxLayout.x());
        Label lbText = new Label(text);
        Container labels = new Container(BoxLayout.y()).addAll(lbText);
        global.addAll(labels);

        /*lbName.addPointerPressedListener(evt -> {
            UserShowForm userShowForm = new UserShowForm();
            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 3);
            userShowForm.getToolbar().addCommandToLeftBar("Return to list ",icon,evt1 -> this.show());
            userShowForm.show();
        });*/
        global.setLeadComponent(lbText);

        return global;
    }
}
