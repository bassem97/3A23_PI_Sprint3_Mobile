package com.esprit.PI_Sprint3_Mobile.GUI.eventType;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.entities.EventType;
import com.esprit.PI_Sprint3_Mobile.services.EventService;
import com.esprit.PI_Sprint3_Mobile.services.EventTypeService;

import java.io.IOException;
import java.util.ArrayList;

public class EventTypeListForm extends Form {

    private Resources theme;

    public EventTypeListForm() {
        super("Types D'Evènements", BoxLayout.y());
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
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(theme, null, null).show());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, "", 5), evt1 -> new EventTypeStats());

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new EventTypeAddForm().show());

        ArrayList<EventType> eventTypes = EventTypeService.getInstance().findAll();
        eventTypes.forEach(event -> this.add(item(event)));
        this.getToolbar().addSearchCommand(e ->{
            String text = (String)e.getSource();
            if (text != null && text.length() != 0){
                this.removeAll();
                eventTypes
                        .stream()
                        .filter(th -> th.getName().contains(text))
                        .forEach(th1 -> this.add(item(th1)));
            }else{
                this.removeAll();
                eventTypes.forEach(th2 -> this.add(item(th2)));
            }
        });
    }

    private Container item(EventType eventType) {
        Container global = new Container(BoxLayout.x());
        Label lbName = new Label(eventType.getName());
        lbName.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbName.getAllStyles().setFont(Font.createSystemFont(lbName.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbName.getUnselectedStyle().getFont().getSize()));

        Container labels = new Container(BoxLayout.y()).add(lbName);
        global.add(labels);

        lbName.addPointerReleasedListener(evt -> new EventTypeShowForm(eventType).show());
        global.setLeadComponent(lbName);

        return global;
    }
}

