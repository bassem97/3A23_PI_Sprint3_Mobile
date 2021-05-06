package com.esprit.PI_Sprint3_Mobile.GUI.event;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.services.EventService;

import java.io.IOException;

public class EventShowForm extends Form {

    private Resources theme;
    private Event event;
    private Label lbDate, lbName, lbDescription, lbPlaces;

    public EventShowForm(Event event) {
        super(event.getName(), BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.event = event;
        addGUIs();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm().show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "TitleCommand", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + event.getName() + " ?", "Oui", "Non" )) {
                EventService.getInstance().delete(event.getId());
                new EventListForm().show();
            }
        });


        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new EventListForm().show());

        lbDate = new Label(event.getDate().toLocalDate().toString());
        lbName = new Label(event.getName());
        lbName.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbName.getAllStyles().setFont(Font.createSystemFont(lbName.getUnselectedStyle().getFont().getFace(), Font.STYLE_BOLD, lbName.getUnselectedStyle().getFont().getSize()));
        lbDescription = new Label(event.getDescription());
        lbPlaces = new Label("Encore " + event.getNb_part_max() + " Places !");


        this.addAll(new Container(BoxLayout.yCenter()).add(new ImageViewer(theme.getImage("Cracks.jpg"))),
                new Container(BoxLayout.xCenter()).add(lbName),
                new Container(BoxLayout.xCenter()).add(lbDescription),
                new Container(BoxLayout.xCenter()).add(lbPlaces),
                new Container(BoxLayout.xCenter()).add(lbDate)
              );

    }
}
