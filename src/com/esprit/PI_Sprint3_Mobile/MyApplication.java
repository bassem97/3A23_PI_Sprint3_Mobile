package com.esprit.PI_Sprint3_Mobile;


import static com.codename1.ui.CN.*;

import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Theme;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.EventService;
import com.esprit.PI_Sprint3_Mobile.services.SujetService;
import com.esprit.PI_Sprint3_Mobile.services.ThemeService;
import com.esprit.PI_Sprint3_Mobile.services.UserService;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        System.out.println(SujetService.getInstance().findById(34));
        if(current != null){
            current.show();
            return;
        }

//        System.out.println(UserService.getInstance().findByEmail("bassem.jadoui@esrit.tn"));
//        User user = UserService.getInstance().findById(122);
//        user.setNom("bassem");
//        System.out.println(UserService.getInstance().delete(75));
        // System.out.println(EventService.getInstance().findAll());
        new LoginForm().show();
//        User user = new User();
//        user.setNom("basssssem");
//        user.setPrenom("jaaaaadoui");
//        user.setUsername("basssssem.jaaaasdoui");
//        user.setEmail("fghjkfghjkl@dfghfgh.fghj");
//        user.setPassword("fghjkghjkghjj");
//        UserService.getInstance().save(user);
//        Form hi = new Form("Hi World", BoxLayout.y());
//        hi.add(new Label("Hi World"));
//        hi.show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
