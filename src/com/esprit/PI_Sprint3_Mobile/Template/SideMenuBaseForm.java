/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.esprit.PI_Sprint3_Mobile.Template;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.FeedbackForms.FormListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.categorie.CategorieListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.article.ArticleListForm;
//import com.esprit.PI_Sprint3_Mobile.GUI.article.SlideBar;
import com.esprit.PI_Sprint3_Mobile.GUI.article.SlideBar2;
import com.esprit.PI_Sprint3_Mobile.GUI.article.SplashForm;
import com.esprit.PI_Sprint3_Mobile.GUI.commentaire.CommList;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.responsableCategorie.ResponsableCategorieListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.theme.ThemeListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.entities.Article;

import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Article article = null;
        EncodedImage placeHolder = EncodedImage.createFromImage(res.getImage("person.png"), false);
        Image profilePic = null;
        if (UserSession.getUser().getImage().equals("") || UserSession.getUser().getImage() == null )
            profilePic = res.getImage("person.png");
        else if(UserSession.getUser().getImage().contains("google"))
            profilePic = URLImage.createToStorage(placeHolder,UserSession.getUser().getImage(),UserSession.getUser().getImage());
        else {
            try {
                profilePic = Image.createImage(FileSystemStorage.getInstance().getAppHomePath() + UserSession.getUser().getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(UserSession.getUser().getNom()+" "+UserSession.getUser().getPrenom(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Home", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Users", FontImage.MATERIAL_VERIFIED_USER,  e -> new UserListForm().show());
//        getToolbar().addMaterialCommandToSideMenu("  Activity", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Events", FontImage.MATERIAL_EMOJI_EVENTS,  e -> new EventListForm().show());
        getToolbar().addMaterialCommandToSideMenu("  Forum", FontImage.MATERIAL_FORUM,  e -> new ThemeListForm().show());
        getToolbar().addMaterialCommandToSideMenu("  Feedback", FontImage.MATERIAL_FEEDBACK,  e -> new FormListForm().show());
        getToolbar().addMaterialCommandToSideMenu("  Categories", FontImage.MATERIAL_CATEGORY,  e -> new CategorieListForm().show());
        getToolbar().addMaterialCommandToSideMenu("  Responsables", FontImage.MATERIAL_VERIFIED_USER,  e -> new ResponsableCategorieListForm().show());
        //getToolbar().addMaterialCommandToSideMenu("  Articles", FontImage.MATERIAL_EMOJI_EVENTS,  e -> new ArticleListForm().show());
        //getToolbar().addMaterialCommandToSideMenu("  Comments", FontImage.MATERIAL_EMOJI_EVENTS, e -> new CommList().show());
        getToolbar().addMaterialCommandToSideMenu("  Articles", FontImage.MATERIAL_EMOJI_SYMBOLS,  e -> new SlideBar2(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Forum", FontImage.MATERIAL_FORUM,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e ->{
            UserSession.logOut();
            new LoginForm(res, null, null).show();
        });
    }
    
    protected abstract void showOtherForm(Resources res);
}
