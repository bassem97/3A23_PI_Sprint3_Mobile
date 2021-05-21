package com.esprit.PI_Sprint3_Mobile.GUI.commentaire;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Article;
import com.esprit.PI_Sprint3_Mobile.entities.Commentaire;
import com.esprit.PI_Sprint3_Mobile.services.CommentaireService;
//import org.ocpsoft.prettytime.PrettyTime;


import java.io.IOException;

public class CommList extends Form {
    private Article article;
    private Resources res;

    public CommList(){ }

    public CommList(Article article) {
        super("Commentaires", BoxLayout.y());

        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs(article);
    }


    private void addGUIs(Article article) {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new ProfileForm(res).show());
        //this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(res).show();
        });
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new CommAdd(article).show());

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new CommList(article).show());

        CommentaireService.getInstance()
                .findAll()
                .stream()
                .filter(com -> com.getArticle().getId() == article.getId())
                .forEach(com -> {
                    this.add(item(com));
                });
    }


    private Container item(Commentaire com) {
        Container global = new Container(BoxLayout.x());
        Label lbText = new Label(com.getCorps());
        lbText.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        //PrettyTime p = new PrettyTime();
        //String pattern = "yyyy-MM-dd HH:mm:ss";
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        //Label lbDate = new Label(p.format(Date.valueOf(post.getDateTime().toLocalDate())));
        //Label lbDate = new Label(com.getDate().toLocalDate().toString());
        Container labels = new Container(BoxLayout.y()).addAll(lbText);
        global.addAll(labels);

        // if (com.getUser().getId() == UserSession.getUser().getId()) {
            lbText.addPointerPressedListener(evt -> new CommShow(com).show());
            global.setLeadComponent(lbText);


        return global;
    }
}