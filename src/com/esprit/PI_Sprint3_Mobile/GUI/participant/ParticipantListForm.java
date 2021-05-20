package com.esprit.PI_Sprint3_Mobile.GUI.participant;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Participant;
import com.esprit.PI_Sprint3_Mobile.services.ParticipantService;

import java.io.IOException;
import java.util.stream.Collectors;

public class ParticipantListForm extends Form {

    private Resources theme;

    public ParticipantListForm() {
        super("Mes Evènements", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(theme).show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new EventListForm().show());


        ParticipantService.getInstance().findAll().stream().filter(participant -> participant.getUser().getId() == UserSession.getUser().getId())
                .collect(Collectors.toList()).forEach(participant ->this.add(item(participant)));

    }

    private Container item(Participant participant){
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, participant.getEvent().getImage(), "");
        Container global = new Container(BoxLayout.x());
        Label lbName = new Label(participant.getEvent().getName());
        lbName.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbName.getAllStyles().setFont(Font.createSystemFont(lbName.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbName.getUnselectedStyle().getFont().getSize()));
        Label lbDescription = new Label(participant.getEvent().getDescription());
        Label lbDate = new Label(participant.getEvent().getDate().toLocalDate().toString());
        Label lbAvis = new Label();
        if (!participant.getAvis().equals(""))
            lbAvis = new Label("Avis: " + participant.getAvis());
        Container labels = new Container(BoxLayout.y()).addAll(lbName, lbDescription, lbDate, lbAvis);
        global.add(background);
        global.add(labels);

        lbName.addPointerReleasedListener(evt -> new ParticipantShowForm(participant).show());
        global.setLeadComponent(lbName);

        return global;
    }
}
