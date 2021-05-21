package com.esprit.PI_Sprint3_Mobile.GUI.FeedbackForms;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Form;
import com.esprit.PI_Sprint3_Mobile.services.FormService;

import java.io.IOException;

public class FormListForm extends com.codename1.ui.Form {
    private Resources theme;

    public FormListForm() {
        super("Forms", BoxLayout.y());

        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addGUIs();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home", null, event -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, event -> new LoginForm(theme, null, null).show());

        FormService.getInstance().findAll().forEach(form -> this.add(item(form)));
    }

    private Container item(Form form) {
        Container card = new Container(BoxLayout.y());
        card.getAllStyles().setPadding(5, 5, 5, 5);
        card.getAllStyles().setMargin(10, 10, 25, 25);
        card.getAllStyles().setBorder(Border.createOutsetBorder(5));

        Label lbTitle = new Label(form.getTitle());
        Label lbDescription = new Label(form.getDescription());

        Label lbIsOpen = new Label(form.getIsOpen() ? "Open" : "Closed");
        lbIsOpen.getAllStyles().setFgColor(
                form.getIsOpen() ?
                        ColorUtil.rgb(0, 190, 0) :
                        ColorUtil.rgb(190, 0, 0)
        );

        Button toggleState = new Button(form.getIsOpen() ? "Close form" : "Open form");
        toggleState.addActionListener((event) -> {
            if (form.getIsOpen()) {
                boolean formClosed = FormService.getInstance().closeForm(form.getId());
                if (formClosed) {
                    lbIsOpen.setText("Closed");
                    lbIsOpen.getAllStyles().setFgColor(ColorUtil.rgb(190, 0, 0));
                    toggleState.setText("Open form");
                    form.setIsOpen(false);
                }
                return;
            }
            boolean formOpened = FormService.getInstance().openForm(form.getId());
            if (formOpened) {
                lbIsOpen.setText("Open");
                lbIsOpen.getAllStyles().setFgColor(ColorUtil.rgb(0, 190, 0));
                toggleState.setText("Close form");
                form.setIsOpen(true);
            }
        });

        Button viewAnswers = new Button("View answers");
        viewAnswers.getAllStyles().setFgColor(ColorUtil.rgb(82, 133, 197));
        viewAnswers.getAllStyles().setBgColor(ColorUtil.rgb(255, 255, 255));
        viewAnswers.getAllStyles().setMargin(0, 0, 10, 0);
        viewAnswers.addActionListener((event) -> {
            new FormAnswersForm(form).show();
        });

        card.addAll(
                lbTitle,
                lbDescription,
                lbIsOpen,
                new Container(BoxLayout.xCenter()).addAll(toggleState, viewAnswers)
        );

        return card;
    }

}
