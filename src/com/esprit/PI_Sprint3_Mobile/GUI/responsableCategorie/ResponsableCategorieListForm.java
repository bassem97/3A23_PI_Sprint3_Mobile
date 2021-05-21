package com.esprit.PI_Sprint3_Mobile.GUI.responsableCategorie;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.ResponsableCategorie;
import com.esprit.PI_Sprint3_Mobile.services.ResponsableCategorieService;

import java.io.IOException;

public class ResponsableCategorieListForm extends Form {

    private Resources theme;

    public ResponsableCategorieListForm() {
        super("Responsable Categorie", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(theme, null, null).show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, "TitleCommand", 5), evt1 -> new Charts());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "TitleCommand", 5), evt1 -> new ResponsableCategorieAddForm().show());

        ResponsableCategorieService.getInstance().findAll().forEach(rcategorie -> this.add(item(rcategorie)));
    }

    private Container item(ResponsableCategorie responsableCategorie){
        Container global = new Container(BoxLayout.x());
        Label lbNom = new Label(responsableCategorie.getNom());
        lbNom.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbNom.getAllStyles().setFont(Font.createSystemFont(lbNom.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbNom.getUnselectedStyle().getFont().getSize()));
        Label lbPrenom = new Label(responsableCategorie.getPrenom());
        lbPrenom.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbPrenom.getAllStyles().setFont(Font.createSystemFont(lbPrenom.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbPrenom.getUnselectedStyle().getFont().getSize()));
        Label lbEmail = new Label(responsableCategorie.getEmail());
        Label lbCategorie = new Label(responsableCategorie.getCategorie().toString());

        Container labels = new Container(BoxLayout.y()).addAll(lbNom,lbPrenom,lbEmail,lbCategorie);
        global.addAll( labels);


        lbNom.addPointerReleasedListener(evt -> new ResponsableCategorieShowForm(responsableCategorie).show());
        global.setLeadComponent(lbNom);

        return global;
    }
}