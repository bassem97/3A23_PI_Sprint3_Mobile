package com.esprit.PI_Sprint3_Mobile.GUI.FeedbackForms;

import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.user.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.Form;
import com.esprit.PI_Sprint3_Mobile.entities.FormAnswer;
import com.esprit.PI_Sprint3_Mobile.entities.QuestionAnswer;
import com.esprit.PI_Sprint3_Mobile.services.FormService;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;

public class FormAnswersForm extends com.codename1.ui.Form {
    private Resources theme;
    private Collection<FormAnswer> formAnswers;
    private int currentIndex;
    private int numberOfAnswers;
    private Container answerContainer;
    private Container currentAnswerContainer;
    private Transition transition;
    private Label numberTrackerLabel;

    public FormAnswersForm(Form form) {
        super("Form Answers", BoxLayout.y());

        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addGUIs(form);
    }

    private void addGUIs(Form form) {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Forms", null, event -> new FormListForm().show());
        this.getToolbar().addCommandToOverflowMenu("Home", null, event -> new Home().show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, event -> new LoginForm().show());


        Label lbTitle = new Label(form.getTitle());
        Label lbDescription = new Label(form.getDescription());
        this.add(new Container(BoxLayout.y()).addAll(lbTitle, lbDescription));

        this.formAnswers = FormService.getInstance().getAnswers(form.getId());
        this.currentIndex = 0;
        this.numberOfAnswers = this.formAnswers.size();
        this.transition = CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 200);

        this.initAnswerContainer();

        Button next = new Button("next");
        next.getAllStyles().setMargin(0, 0, 0, 10);
        next.addActionListener((event) -> {
            if (this.currentIndex == this.numberOfAnswers - 1) {
                return;
            }
            this.transition = CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 200);
            this.currentIndex++;
            this.renderAnswerOfCurrentIndex();
        });

        Button previous = new Button("previous");
        previous.addActionListener((event) -> {
            if (this.currentIndex == 0) {
                return;
            }
            this.transition = CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 200);
            this.currentIndex--;
            this.renderAnswerOfCurrentIndex();
        });

        this.numberTrackerLabel = new Label("");
        this.add(new Container(BoxLayout.xCenter()).add(this.numberTrackerLabel));

        this.add(new Container(BoxLayout.xCenter()).addAll(previous, next));
        this.renderAnswerOfCurrentIndex();
    }

    private void initAnswerContainer() {
        this.answerContainer = new Container(BoxLayout.y());
        this.answerContainer.getAllStyles().setPadding(5, 5, 5, 5);
        this.answerContainer.getAllStyles().setMargin(10, 10, 10, 10);
        this.answerContainer.getAllStyles().setBorder(Border.createOutsetBorder(5));
        this.currentAnswerContainer = new Container(BoxLayout.y());
        this.answerContainer.add(this.currentAnswerContainer);
        this.add(this.answerContainer);
    }

    private void renderAnswerOfCurrentIndex() {
        Container container = new Container(BoxLayout.y());
        FormAnswer currentAnswer = (FormAnswer) this.formAnswers.toArray()[this.currentIndex];
        Label userEmail = new Label(currentAnswer.getUser().getEmail());
        Label answerDate = new Label(currentAnswer.getAnswerDate().toLocalDate().toString());
        container.add(new Container(BoxLayout.y()).addAll(userEmail, answerDate));

        currentAnswer.getQuestionAnswerCollection()
                .stream()
                .sorted(Comparator.comparingInt(QuestionAnswer::getNumber))
                .forEach((questionAnswer) -> {
                    Label questionLabel = new Label(String.format("%d) %s", questionAnswer.getNumber(), questionAnswer.getQuestion()));
                    Label answerLabel = new Label(questionAnswer.getAnswer());
                    container.add(new Container(BoxLayout.y()).addAll(questionLabel, answerLabel));
                });

        this.answerContainer.replace(this.currentAnswerContainer, container, this.transition);
        this.currentAnswerContainer = container;

        this.numberTrackerLabel.setText(String.format("%d/%d", this.currentIndex + 1, this.numberOfAnswers));
    }
}
