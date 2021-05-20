package com.esprit.PI_Sprint3_Mobile.Template;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import java.io.IOException;

public class ContinueRegistrationForm extends Form {

    Container welcome;
    TextField email;
    Button googleButton;
    private Resources theme;

    public ContinueRegistrationForm() {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        welcome = FlowLayout.encloseCenter(
//                new Label("Sign Up to ", "WelcomeWhite"),
                new Label("Continues Registration", "WelcomeBlue")
        );
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUI();
        addActions();
    }

    private void addActions() {
    }

    private void addGUI() {
    }
}
