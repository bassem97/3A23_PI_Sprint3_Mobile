package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;
import com.esprit.PI_Sprint3_Mobile.utils.FileChooser.FileChooser;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

public class EditUser extends Form {

    public Resources theme;
    private User user;

    private Image img ;
    private TextField nom = new TextField();
    private  TextField prenom = new TextField();
    private  TextField email = new TextField();
    private  FontImage save;
    private Image uploadImg;
    private ImageViewer imageViewer;
    private String imageName;
//    private PickerComponent birthDate = new PickerComponent();

    public EditUser(User user) {
        super(user.getUsername(), new FlowLayout(Component.CENTER,Component.CENTER));
        nom.getAllStyles().setBgColor(Color.BLACK.getRGB());
        nom.getAllStyles().setFgColor(Color.BLACK.getRGB());
        nom.getStyle().setBgColor(Color.BLACK.getRGB());
        nom.getStyle().setFgColor(Color.BLACK.getRGB());

        prenom.getAllStyles().setBgColor(Color.BLACK.getRGB());
        prenom.getAllStyles().setFgColor(Color.BLACK.getRGB());
        email.getAllStyles().setBgColor(Color.BLACK.getRGB());
        email.getAllStyles().setFgColor(Color.BLACK.getRGB());
        this.user = user;
//        this.user = new User();
        this.nom.setText(user.getNom());
        this.prenom.setText(user.getPrenom());
        this.email.setText(user.getEmail());
        this.email.setEditable(false);

        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        addActions();
    }

    private void addActions() {
        img.addActionListener(evt -> {
            System.out.println("immmmmmg");
        });
        uploadImg.addActionListener(evt -> {
            System.out.println("uploaad");
        });
    }

    private void addGUIs() {
        Container ct = new Container(BoxLayout.y());
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5);
        save = FontImage.createMaterial(FontImage.MATERIAL_SAVE, "TitleCommand", 5);
        this.getToolbar().addCommandToLeftBar(null,icon,evt1 -> new UserShowForm(user.getUsername(),user).show());
        this.getToolbar().addCommandToRightBar(null,save,evt1 ->{
            if(nom.getText().length() == 0 || prenom.getText().length() == 0 ){
                ToastBar.showErrorMessage("les champs sont obligatoire !");
            }else{
                User user2 = new User();
                user2.setId(user.getId());
                user2.setNom(nom.getText());
                user2.setPrenom(prenom.getText());
                user2.setEmail(email.getText());
                if(imageName.length() == 0)
                    user2.setImage(user.getImage());
                else
                    user2.setImage(imageName);
                System.out.println(user2);
                if(UserService.getInstance().update(user2))
                    Dialog.show("Successful", user.getUsername()+" updated !" , "OK", null);
                else
                    Dialog.show("Failure", user.getUsername()+" cannot updated !" , "OK", null);
            }


        } );
        EncodedImage placeHolder = EncodedImage.createFromImage(theme.getImage("person.png"), false);


        if(user.getImage() == null || user.getImage().equals(""))
            img = theme.getImage("person.png");
        else if(user.getImage().contains("google")){
            String url = user.getImage();
            img = URLImage.createToStorage(placeHolder,user.getUsername(),url);
        }else
            img = theme.getImage(user.getUsername());

        imageViewer = new ImageViewer(img);
        imageViewer.setPreferredSize(new Dimension(1400,800));

        imageViewer.addLongPressListener(evt -> {
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

        uploadImg = FontImage.createMaterial(FontImage.MATERIAL_FILE_UPLOAD, "TitleCommand", 5).toImage();






        ct.addAll(nom,prenom,email);
        this.addAll(imageViewer,ct);
    }

    protected String saveFileToDevice(String hi) throws IOException {
        int index = hi.lastIndexOf("/");
        hi = hi.substring(index + 1);
        return hi;

    }
}
