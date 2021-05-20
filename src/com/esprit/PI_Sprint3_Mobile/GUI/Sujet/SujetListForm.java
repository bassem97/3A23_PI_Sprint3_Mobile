package com.esprit.PI_Sprint3_Mobile.GUI.Sujet;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Post.PostListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.theme.ThemeListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.services.SujetService;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class SujetListForm extends Form {
private Theme theme;
    private Resources res;
    public SujetListForm(Theme themee) {
        super("Sujets", BoxLayout.y());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addGUIs(themee);
    }


    private void addGUIs(Theme themee) {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(res).show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new SujetAddForm(themee).show());

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new ThemeListForm().show());



        SujetService.getInstance()
                .findAll()
                .stream()
                .filter(sujet->sujet.getTheme().getId()==themee.getId())
                .forEach(sujet1 -> this.add(item(sujet1)));
    }


    private Container item(Sujet sujet){
        Container global = new Container(BoxLayout.x());
        Label lbText = new Label(sujet.getText());
        lbText.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        PrettyTime p = new PrettyTime();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Label lbDate = new Label(p.format(Date.valueOf(sujet.getDateTime().toLocalDate())));
        Container labels = new Container(BoxLayout.y()).addAll(lbText, lbDate);
        global.addAll(labels);


        if (sujet.getUser().getId() == UserSession.getUser().getId()) {
            lbText.addPointerPressedListener(evt -> {
                if (Dialog.show("Information", "Afficher Sujet / Posts", "Afficher Sujet", "Afficher Posts"))
                    new SujetShowForm(sujet).show();
                else
                    new PostListForm(sujet).show();
            });
        } else
            lbText.addPointerPressedListener(evt -> new PostListForm(sujet).show());
        global.setLeadComponent(lbText);

        return global;
    }
}
