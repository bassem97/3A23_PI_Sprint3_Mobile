package com.esprit.PI_Sprint3_Mobile.GUI.article;

import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Article;
import com.esprit.PI_Sprint3_Mobile.entities.Commentaire;
import com.esprit.PI_Sprint3_Mobile.services.ArticleService;
import com.esprit.PI_Sprint3_Mobile.services.CommentaireService;

import java.io.IOException;
import java.util.ArrayList;

public class ArticleShow extends Form {

    Form f;
    private Article article;
    //private TextField tfName,tfContenu,creationDate;
    private  Label tfName= new Label();
    private  Label tfContenu = new Label();
   // private  Label creationDate = new Label();
    private Button btnUpdate;
    public Resources theme;

    public ArticleShow(Article article) {
        super(article.getTitre(),BoxLayout.yCenter());

        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.article = article;
        addGUIs();
        addActions();
    }

    public ArticleShow() {

    }

    private void addGUIs() {

        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("back",null,evt1 -> new ProfileForm(theme).show());
        this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new Home().show());
        //this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> new LoginForm(theme).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(theme).show();
        });
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "TitleCommand", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + article.getTitre() + " ?", "Oui", "Non" )) {
                ArticleService.getInstance().delete(article.getId());
                ToastBar.showMessage("Article supprimé avec succès", FontImage.MATERIAL_INFO);
                new ArticleListForm().show();
            }
        });
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_EDIT, "TitleCommand", 5),actionEvent -> {
            if (Dialog.show("Information", article.getTitre() + " Modifié", "OK",null)){
                ArticleService.getInstance().update(article);
                ToastBar.showMessage("Article modifié avec succès", FontImage.MATERIAL_INFO);
                new ArticleListForm().show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new ArticleListForm().show());
         this.article=article;

        /*tfName = new TextField(article.getTitre(), "Nom");
        tfCotenu= new TextField(article.getCorps(), "Contenu");*/
        tfName.setText("Titre: "+article.getTitre());
        tfContenu.setText("Contenu : "+article.getCorps());
       // creationDate.setText("Joined at  : "+article.getDate().toLocalDate().toString());

        //Pour le Commentaire

        TextField Comment = new TextField("","Inserer Votre Commentaire ici");
        Button valider = new Button("Valider");

        // ajout Un commentaire
        valider.addActionListener((e) -> {
            CommentaireService ser = new CommentaireService();
            Commentaire t =new Commentaire();
            t.setRef(0);
            t.setCorps(Comment.getText());

            t.setArticle(article);
            ser.save(t);
        });
        Label Affichesuite = new Label("Afficher les Commentaires ..");
        Container tous = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        //btnUpdate = new Button("Modifier");


        Affichesuite.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //  CommentaireService ser = new CommentaireService();
                //  ArrayList<Commentaire> Listcommentaire = ser.getList2();

                ArrayList<Commentaire> p;
                CommentaireService ser = new CommentaireService();
                p= ser.findAll();
                //Label separator4 = new Label("______________");
                //int i=0;
                for(int i = 0 ; i < p.size(); i++){
                    Commentaire commentaire=p.get(i);

                    Label comments = new Label(commentaire.getCorps());

                }
            }
        });

        this.addAll(
                new Container(BoxLayout.xCenter()).add(tfName),
                new Container(BoxLayout.xCenter()).add(tfContenu),
                //new Container(BoxLayout.yBottom()).add(btnUpdate),
                new Container(BoxLayout.xCenter()).add(Comment),
                new Container(BoxLayout.xCenter()).add(Affichesuite),
                new Container(BoxLayout.xCenter()).add(valider)
        );

    }

    private void addActions() {
       /* btnUpdate.addActionListener(actionEvent -> {
            article.setTitre(tfName.getText());
            if (ArticleService.getInstance().update(article)) {
                Dialog.show("Information", article.getTitre() + " Modifié", "OK",null);
                new ArticleListForm().show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });*/
    }
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
