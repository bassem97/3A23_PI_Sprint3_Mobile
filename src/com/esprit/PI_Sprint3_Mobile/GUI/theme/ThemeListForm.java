package com.esprit.PI_Sprint3_Mobile.GUI.theme;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Sujet.SujetListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.services.ThemeService;

import java.io.IOException;
import java.util.ArrayList;

public class ThemeListForm extends Form {

    private Resources res;

    public ThemeListForm() {
        super("Themes", BoxLayout.y());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, "", 5), evt1 -> new ThemeStats());

        if (/*UserSession.getUser().getRoles().contains("ROLE_ADMIN")*/ true)
            this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new ThemeAddForm().show());

        /*ThemeService.getInstance().findAll().forEach(theme -> {
            this.add(item(theme));
        });*/
        ArrayList<Theme> themes = ThemeService.getInstance().findAll();
        themes.forEach(theme -> this.add(item(theme)));
        this.getToolbar().addSearchCommand(e ->{
            String text = (String)e.getSource();
            if (text != null && text.length() != 0){
                this.removeAll();
                themes
                        .stream()
                        .filter(th -> th.getLibelle().contains(text))
                        .forEach(th1 -> this.add(item(th1)));
            }else{
                this.removeAll();
                themes.forEach(th2 -> this.add(item(th2)));
            }
        });
    }

    private Container item(Theme theme){
        Container global = new Container(BoxLayout.x());
        Label lbLibelle = new Label(theme.getLibelle());
        Container labels = new Container(BoxLayout.y()).addAll(lbLibelle);
        global.addAll(labels);

        if (/*UserSession.getUser().getRoles().contains("ROLE_ADMIN")*/ true) {
            lbLibelle.addPointerPressedListener(evt -> {
                if (Dialog.show("Information", "Afficher Theme / Sujets", "Afficher Theme", "Afficher Sujets"))
                    new ThemeShowForm(theme).show();
                else
                    new SujetListForm(theme).show();
            });
        } else {
            lbLibelle.addPointerPressedListener(evt -> new SujetListForm(theme).show());
        }
        global.setLeadComponent(lbLibelle);

        return global;
    }
}
