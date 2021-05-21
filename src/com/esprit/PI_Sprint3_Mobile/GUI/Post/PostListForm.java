package com.esprit.PI_Sprint3_Mobile.GUI.Post;

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
import com.esprit.PI_Sprint3_Mobile.entities.Post;
import com.esprit.PI_Sprint3_Mobile.entities.Sujet;
import com.esprit.PI_Sprint3_Mobile.services.PostService;
import com.esprit.PI_Sprint3_Mobile.services.SujetService;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class PostListForm extends Form {
    private Sujet sujet;
    private Resources res;

    public PostListForm(Sujet sujett) {
        super("Posts", BoxLayout.y());
        try {
            res = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs(sujett);
    }


    private void addGUIs(Sujet sujet) {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(res).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(res, null, null).show();
        });

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new PostAddForm(sujet).show());

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new PostListForm(sujet).show());

      /*  PostService.getInstance()
                .findAll()
                .stream()
                .filter(post->post.getSujet().getId()==sujet.getId())
                .forEach(post -> {
                    this.add(item(post));
                });*/
        List<Post> posts = PostService.getInstance().findAll().stream()
                .filter(post->sujet.getId()==post.getSujet().getId()).collect(Collectors.toList());


        posts.forEach(post -> this.add(item(post)));
        this.getToolbar().addSearchCommand(e ->{
            String text = (String)e.getSource();
            if (text != null && text.length() != 0){
                this.removeAll();
                posts
                        .stream()
                        .filter(th -> th.getText().contains(text))
                        .forEach(th1 -> this.add(item(th1)));
            }else{
                this.removeAll();
                posts.forEach(th2 -> this.add(item(th2)));
            }
        });
    }


    private Container item(Post post){
        Container global = new Container(BoxLayout.x());
        Label lbText = new Label(post.getText());
        lbText.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        PrettyTime p = new PrettyTime();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Label lbDate = new Label(p.format(Date.valueOf(post.getDateTime().toLocalDate())));
        Container labels = new Container(BoxLayout.y()).addAll(lbText, lbDate);
        global.addAll(labels);

        if (post.getUser().getId() == UserSession.getUser().getId()) {
            lbText.addPointerPressedListener(evt -> new PostShowForm(post).show());
            global.setLeadComponent(lbText);
        }

        return global;
    }
}
