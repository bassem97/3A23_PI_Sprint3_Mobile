package com.esprit.PI_Sprint3_Mobile.GUI.article;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Article;
import com.esprit.PI_Sprint3_Mobile.services.ArticleService;

import java.io.IOException;
import java.util.ArrayList;

public class ArticleListForm extends Form {

    private Resources theme;
    Article article;

    public ArticleListForm() {
        super("Articles", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(theme).show());

        //this.getToolbar().addCommandToOverflowMenu("Mes Commentaires",null, evt1 -> new CommList(article, article1).show());
        //this.getToolbar().addCommandToOverflowMenu("Mon Article",null,evt1 -> new ArticleShow().show());

        //this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(theme).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(theme, null, null).show();
        });
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, "", 5), evt1 -> new ArticleStats());

        //if (UserSession.getUser().getRoles().contains("ADMIN"))
            this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "TitleCommand", 5), evt1 -> new ArticleAddForm().show());

        ArrayList<Article> articles = ArticleService.getInstance().findAll();
        articles.forEach(article -> this.add(item(article)));
        TextField tfSearch = new TextField("", "Search");
        tfSearch.addPointerReleasedListener(event2 -> {
            if(tfSearch.getText().length() > 0) {
                ArrayList<Article> aux = new ArrayList<>(articles);
                this.removeAll();
                aux.stream().filter(article -> article.getTitre().contains(tfSearch.getText().toLowerCase())).forEach(article ->{
                    System.out.println(tfSearch.getText());
                    System.out.println(article);
                    this.add(item(article));
                });
            } else {
                this.removeAll();
                ArrayList<Article> aux = new ArrayList<>(articles);
                aux.forEach(article -> this.add(item(article)));
            }
        });
        this.getToolbar().setTitleComponent(tfSearch);
    }

    private Container item(Article article){
        try {
            Container global = new Container(BoxLayout.x());
            //EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
            //URLImage background = URLImage.createToStorage(placeholder, article.getImage(), "");
            Label lbName = new Label(article.getTitre());
            lbName.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
            lbName.getAllStyles().setFont(Font.createSystemFont(lbName.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbName.getUnselectedStyle().getFont().getSize()));
            Label lbDescription = new Label(article.getCorps());
            //Label lbDate = new Label(article.getDate().toLocalDate().toString());
            Container labels = new Container(BoxLayout.y()).addAll(lbName, lbDescription);
            //global.add(background);
            global.add(labels);

            lbName.addPointerReleasedListener(evt -> new ArticleShow(article).show());
            global.setLeadComponent(lbName);

            return global;

        } catch (Exception e) {
            e.printStackTrace();
            return new Container();
        }
    }

}
