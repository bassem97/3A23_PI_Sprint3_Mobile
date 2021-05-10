package com.esprit.PI_Sprint3_Mobile.GUI.Sujet;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.Post.PostListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.services.SujetService;

public class SujetListForm extends Form {
private Theme theme;
    public SujetListForm(Theme themee) {
        super("Sujets", BoxLayout.y());

        addGUIs(themee);
    }


    private void addGUIs(Theme themee) {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "TitleCommand", 5), evt1 -> new SujetAddForm().show());

        SujetService.getInstance()
                .findAll()
                .stream()
                .filter(sujet->sujet.getTheme().getId()==themee.getId())
                .forEach(sujet -> {
            this.add(item(sujet));
        });
    }


    private Container item(Sujet sujet){
        Container global = new Container(BoxLayout.x());
        Label lbText = new Label(sujet.getText());
        Container labels = new Container(BoxLayout.y()).addAll(lbText);
        global.addAll(labels);

        lbText.addPointerPressedListener(evt -> {
            new PostListForm(sujet).show();
        });
        global.setLeadComponent(lbText);

        return global;
    }
}
