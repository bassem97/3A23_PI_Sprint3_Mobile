package com.esprit.PI_Sprint3_Mobile.GUI.article;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.Template.ProfileForm;

public class SlideBar2 extends Form {

    public Resources theme;

    public SlideBar2(Resources res) {
        super(new LayeredLayout());
        this.theme = res;
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");

        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));

        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();

        Image notes = res.getImage("background.jpg");
        Image duke = res.getImage("background.jpg");

        Label notesPlaceholder = new Label("","ProfilePic");
        Label notesLabel = new Label(notes, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Label bottomSpace = new Label();

        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                notesPlaceholder,
                new Label("Consulter nos articles", "WalkthruWhite"),
                new SpanLabel("Never miss To use EspritGazine, never forget about your " +
                        "daily team meeting and remember when their is an event.",  "WalkthruBody"),
                bottomSpace
        ));
        tab1.setUIID("WalkthruTab1");

        walkthruTabs.addTab("", tab1);

        Label bottomSpaceTab2 = new Label();

        Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                new Label(duke, "ProfilePic"),
                new Label("Articles concernant ESPRIT", "WalkthruWhite"),
                new SpanLabel("Ecrire et consulter dees articles contentant  " +
                        "Les evenements ,Les clubs ,les Forums et  Les formations " +
                        "Dans ESPRIT ",  "WalkthruBody"),
                bottomSpaceTab2
        ));

        tab2.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab2);

        add(walkthruTabs);

        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Button skip = new Button("Go To Articles");
        skip.setUIID("SkipButton");

        skip.addActionListener(e -> new ArticleListForm().show());
//        skip.addActionListener(e -> new Home().show());

        Container southLayout = BoxLayout.encloseY(
                radioContainer,
                skip
        );
        add(BorderLayout.south(
                southLayout
        ));

        Component.setSameWidth(bottomSpace, bottomSpaceTab2, southLayout);
        Component.setSameHeight(bottomSpace, bottomSpaceTab2, southLayout);

        // visual effects in the first show
//        addShowListener(e -> {
//            notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));
//        });
    }
}
