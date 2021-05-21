package com.esprit.PI_Sprint3_Mobile.GUI.categorie;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Categorie;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.services.CategorieService;
import com.esprit.PI_Sprint3_Mobile.services.EventService;

import java.io.IOException;

public class CategorieListForm extends Form {

    private Resources theme;

    public CategorieListForm() {
        super("Categories ", BoxLayout.y());
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
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "TitleCommand", 5), evt1 -> new CategorieAddForm().show());

        CategorieService.getInstance().findAll().forEach(event -> this.add(item(event)));
    }

    private Container item(Categorie categorie){
//        ImageViewer imageViewer = new ImageViewer(theme.getImage("Cracks.jpg"));
//        imageViewer.setPreferredSize(new Dimension(300,300));
        Container global = new Container(BoxLayout.x());
        Label lbTitre = new Label(categorie.getTitre());
        lbTitre.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbTitre.getAllStyles().setFont(Font.createSystemFont(lbTitre.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbTitre.getUnselectedStyle().getFont().getSize()));
        Label lbDescription = new Label(categorie.getDescription());
        Container labels = new Container(BoxLayout.y()).addAll(lbTitre, lbDescription);
        global.addAll( labels);

        lbTitre.addPointerReleasedListener(evt -> new CategorieShowForm(categorie).show());
        global.setLeadComponent(lbTitre);

        return global;
    }
}