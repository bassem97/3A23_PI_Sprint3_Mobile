package com.esprit.PI_Sprint3_Mobile.GUI.event;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.entities.Participant;
import com.esprit.PI_Sprint3_Mobile.services.EventService;
import com.esprit.PI_Sprint3_Mobile.services.ParticipantService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/*import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
*/
import java.io.IOException;
import java.time.LocalDate;

public class EventShowForm extends Form {

    private Resources theme;
    private Event event;
    private Label lbDate, lbName, lbDescription, lbPlaces;
    private Button btnParticipate;

    public EventShowForm(Event event) {
        super(event.getName(), BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.event = event;
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(theme, null, null).show());
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + event.getName() + " ?", "Oui", "Non" )) {
                EventService.getInstance().delete(event.getId());
                new EventListForm().show();
            }
        });


        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new EventListForm().show());

        lbDate = new Label(event.getDate().toLocalDate().toString());
        lbName = new Label(event.getName());
        lbName.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbName.getAllStyles().setFont(Font.createSystemFont(lbName.getUnselectedStyle().getFont().getFace(), Font.STYLE_BOLD, lbName.getUnselectedStyle().getFont().getSize()));
        lbDescription = new Label(event.getDescription());
        int places;
        try {
            places = (int) (event.getNb_part_max() - ParticipantService.getInstance().findAll().stream().filter(participant -> participant.getEvent().getId() == event.getId()).count());
        }
        catch (Exception e) {
            places = event.getNb_part_max();
        }
        lbPlaces = new Label("Encore " + places + " Places Disponibles !");
        btnParticipate = new Button("Participer");

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, event.getImage(), "");

        this.add(background);
        this.addAll(
                new Container(BoxLayout.xCenter()).add(lbName),
                new Container(BoxLayout.xCenter()).add(lbDescription),
                new Container(BoxLayout.xCenter()).add(lbPlaces),
                new Container(BoxLayout.xCenter()).add(lbDate),
                new Container(BoxLayout.yBottom()).add(btnParticipate)
        );

    }

    private void addActions() {
        btnParticipate.addActionListener(evt -> {
            if (event.getDate().toLocalDate().isBefore(LocalDate.now()))
                Dialog.show("Inormation", "Event dejà passé", "OK", null);
            else if (ParticipantService.getInstance().findAll().stream().filter(participant -> participant.getEvent().getId() == event.getId()).count() >= event.getNb_part_max())
                Dialog.show("Inormation", "Pas de place disponible", "OK", null);
            else if (ParticipantService.getInstance().findAll().stream().noneMatch(participant -> (participant.getEvent().getId() == event.getId()) && (participant.getUser().getId() == UserSession.getUser().getId()))) {
                ParticipantService.getInstance().save(new Participant(event, UserSession.getUser()));
                Dialog.show("Inormation", "Vous participez à l'event " + event.getName(), "OK", null);
                String accountSID = "AC709e88cb1f31108712fcc78bb1bc772f";
                String authToken = "ece948d681c8e57c763e8df95d7b8aaf";
                String fromPhone = "+19384440612";
                Twilio.init(accountSID, authToken);

                Message message = Message
                        .creator(new PhoneNumber("+21625882775"), // to
                                new PhoneNumber(fromPhone), // from
                                "Vous participez à l'event " + event.getName() + " qui aura lieu le " + event.getDate().toLocalDate())
                        .create();

                System.out.println(message.getSid());
            }
            else if (ParticipantService.getInstance().findAll().stream().anyMatch(participant -> (participant.getEvent().getId() == event.getId()) && (participant.getUser().getId() == UserSession.getUser().getId())))
                Dialog.show("Inormation", "Vous participez déjà à cet event", "OK", null);

        });
    }
}
