package com.esprit.PI_Sprint3_Mobile.GUI.event;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.eventType.EventTypeListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.participant.ParticipantListForm;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.services.EventService;

import java.io.IOException;
import java.util.ArrayList;

public class EventListForm extends Form {

    private Resources theme;

    public EventListForm() {
        super("Evènements", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
    }


    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Types Events",null,evt1 -> new EventTypeListForm().show());
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new ProfileForm(theme).show());
        this.getToolbar().addCommandToOverflowMenu("Mes Events",null,evt1 -> new ParticipantListForm().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(theme, null, null).show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new EventAddForm().show());

        ArrayList<Event> events = EventService.getInstance().findAll();
        events.forEach(event -> this.add(item(event)));
        this.getToolbar().addSearchCommand(e ->{
            String text = (String)e.getSource();
            if (text != null && text.length() != 0){
                this.removeAll();
                events
                        .stream()
                        .filter(th -> th.getName().contains(text))
                        .forEach(th1 -> this.add(item(th1)));
            }else{
                this.removeAll();
                events.forEach(th2 -> this.add(item(th2)));
            }
        });

    }

    private Container item(Event event){
        try {
            Container global = new Container(BoxLayout.x());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
            URLImage background = URLImage.createToStorage(placeholder, event.getImage(), "");
            Label lbName = new Label(event.getName());
            lbName.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
            lbName.getAllStyles().setFont(Font.createSystemFont(lbName.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbName.getUnselectedStyle().getFont().getSize()));
            Label lbDescription = new Label(event.getDescription());
            Label lbDate = new Label(event.getDate().toLocalDate().toString());
            Container labels = new Container(BoxLayout.y()).addAll(lbName, lbDescription, lbDate);
            global.add(background);
            global.add(labels);

            lbName.addPointerReleasedListener(evt -> new EventShowForm(event).show());
            global.setLeadComponent(lbName);

            return global;

        } catch (Exception e) {
            e.printStackTrace();
            return new Container();
        }
    }
}
