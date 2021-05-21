package com.esprit.PI_Sprint3_Mobile.GUI.article;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.PI_Sprint3_Mobile.GUI.Home;
import com.esprit.PI_Sprint3_Mobile.GUI.event.EventListForm;
import com.esprit.PI_Sprint3_Mobile.GUI.user.UserSession;
import com.esprit.PI_Sprint3_Mobile.Template.LoginForm;
import com.esprit.PI_Sprint3_Mobile.entities.*;
import com.esprit.PI_Sprint3_Mobile.services.*;
import com.esprit.PI_Sprint3_Mobile.utils.FileChooser.FileChooser;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.ZoneId;

public class ArticleAddForm extends Form {

    Form f;
    private Label lbDate;
    private TextField tfName, tfDescription, tfNbLike;
    private Picker datePicker;
    private Button btnSave, btnImg;
    private Image img ;
    Resources theme;
    private String imageName;

    public ArticleAddForm() {
        super("Ajouter Article", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        addActions();
    }

    private void addGUIs() {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);
        this.getToolbar().addCommandToOverflowMenu("Home",null,evt1 -> new Home().show());
        //this.getToolbar().addCommandToOverflowMenu("Details",null,evt1 -> new ArticleShow().show());

        //this.getToolbar().addCommandToOverflowMenu(null,icon,evt1 -> new LoginForm(theme).show());
        this.getToolbar().addCommandToOverflowMenu(null, icon, evt1 -> {
            UserSession.logOut();
            new LoginForm(theme, null, null).show();
        });
        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5), evt1 -> new ArticleListForm().show());

        lbDate = new Label("Date");
        tfName = new TextField();
        tfDescription = new TextField();
        tfNbLike = new TextField();
        datePicker = new Picker();
        tfName.getAllStyles().setFgColor(ColorUtil.BLACK);
        tfDescription.getAllStyles().setFgColor(ColorUtil.BLACK);
        tfNbLike.getAllStyles().setFgColor(ColorUtil.BLACK);
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.getAllStyles().setFgColor(ColorUtil.BLACK);

        btnSave = new Button("Ajouter");
        btnImg = new Button("Image");


        Container upload = new Container(new FlowLayout(Component.CENTER,Component.CENTER));
        upload.add(btnImg).add(img);
        upload.setLeadComponent(btnImg);


        tfName.setHint("Nom");
        tfDescription.setHint("Description");
        tfNbLike.setHint("Nombre de Like");
        this.addAll(new Container(BoxLayout.xCenter()).add(tfName),
                new Container(BoxLayout.xCenter()).add(tfDescription),
                new Container(BoxLayout.xCenter()).add(tfNbLike),
                new Container(BoxLayout.xCenter()).addAll(lbDate, datePicker),
                upload,
                btnSave);

        Validator val = new Validator();
        val.addConstraint(tfName, new LengthConstraint(5));
        val.addConstraint(tfDescription, new LengthConstraint(5));
        val.addConstraint(tfNbLike, new NumericConstraint(true));

        btnImg.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                // FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(false, ".jpg, .jpeg, .png", (ActionEvent e2) -> {
                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);
                            add(logo);
                            if (file.lastIndexOf(".") > 0) {
                                StringBuilder hi = new StringBuilder(file);
                                if (file.startsWith("file://")) {
                                    hi.delete(0, 7);
                                }
                                Log.p(hi.toString());
                                try {
                                    String namePic = saveFileToDevice(file);
                                    this.imageName = namePic;
                                    System.out.println(namePic);
                                } catch (IOException ex) {
                                }

                                revalidate();


                            }
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + imageName;

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }
                    }
                });
            }
        });

    }

    private void addActions() {
        btnSave.addActionListener(actionEvent -> {
           Article article = new Article();
            article.setTitre(tfName.getText());
            article.setCorps(tfDescription.getText());
            article.setLikes(Integer.parseInt(tfNbLike.getText()));
            article.setDate(Instant.ofEpochMilli(datePicker.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
            article.setImage(imageName);
            if (ArticleService.getInstance().save(article)) {
                //Dialog.show("Information", article.getTitre()+ " Ajouté", "OK",null);
                ToastBar.showMessage("Ajout établi avec succès", FontImage.MATERIAL_INFO);
                new ArticleListForm().show();
            } else
                Dialog.show("Erreur", "Erreur D'ajout", "Ok",null);
        });

        btnImg.addActionListener(evt -> {
            ActionListener callback = e->{
                if (e != null && e.getSource() != null) {
                    String filePath = (String)e.getSource();
                    System.out.println(filePath);
                    try {
                        img = Image.createImage(filePath);
                        OutputStream os = FileSystemStorage.getInstance().openOutputStream(filePath);
                        ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 1);
                        os.close();

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            };

            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(".gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
            } else {
                Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
            }
        });
        /*btnAff.addActionListener((e)->{
            ArticleShow a =new ArticleShow();
            a.getF().show();
        });*/
    }


    protected String saveFileToDevice(String hi) throws IOException {
        int index = hi.lastIndexOf("/");
        hi = hi.substring(index + 1);
        return hi;

    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}