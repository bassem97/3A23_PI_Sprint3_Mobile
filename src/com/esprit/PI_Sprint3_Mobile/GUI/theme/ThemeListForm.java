package com.esprit.PI_Sprint3_Mobile.GUI.theme;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.Sujet.SujetListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.services.ThemeService;

public class ThemeListForm extends Form {
    public ThemeListForm() {
        super("Themes", BoxLayout.y());
        addGUIs();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "TitleCommand", 5), evt1 -> new ThemeAddForm().show());

        ThemeService.getInstance().findAll().forEach(theme -> {
            this.add(item(theme));
        });
    }

    private Container item(Theme theme){
        Container global = new Container(BoxLayout.x());
        Label lbLibelle = new Label(theme.getLibelle());
        Container labels = new Container(BoxLayout.y()).addAll(lbLibelle);
        global.addAll(labels);

        lbLibelle.addPointerPressedListener(evt -> {
            new SujetListForm(theme).show();
        });
        global.setLeadComponent(lbLibelle);

        return global;
    }
}
