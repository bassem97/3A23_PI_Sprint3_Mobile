/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.esprit.PI_Sprint3_Mobile.Template;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;

/**
 * A swipe tutorial for the application
 *
 * @author Shai Almog
 */
public class WalkthruForm extends Form {
    public Resources theme;
    public WalkthruForm(Resources res) {
        super(new LayeredLayout());
//        System.out.println("HNEE "+UserSession.getUser());
        this.theme = res;
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        
        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        
        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        
        Image notes = res.getImage("notes.png");
        Image duke = res.getImage("logo.png");
        ImageViewer imageViewer = new ImageViewer(duke);
        imageViewer.setPreferredSize(new Dimension(800,500));

        
        Label notesPlaceholder = new Label("","ProfilePic");
        Label notesLabel = new Label(notes, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Label bottomSpace = new Label();
        
        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                notesPlaceholder,
                new Label("Bienvenus ?? EspritGazine", "WalkthruWhite"),
                new SpanLabel("Magazine Officielle d'Esprit",  "WalkthruBody"),
                bottomSpace
        ));
        tab1.setUIID("WalkthruTab1");
        
        walkthruTabs.addTab("", tab1);
        
        Label bottomSpaceTab2 = new Label();

        Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                // new Label(duke, "ProfilePic"),
                imageViewer,
                new Label("Esprit Gazine", "WalkthruWhite"),
                new SpanLabel("Clubs, Ev??nements, Articles, Forums, Questionnaires ... ",  "WalkthruBody"),
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
        
        Button skip = new Button("Passer");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new ProfileForm(res).show());
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
