package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;
import com.esprit.PI_Sprint3_Mobile.utils.FileChooser.FileChooser;

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
//    private PickerComponent birthDate = new PickerComponent();

    public EditUser(User user) {
        super(user.getUsername(), new FlowLayout(Component.CENTER,Component.CENTER));
        this.user = user;
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
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5);
        save = FontImage.createMaterial(FontImage.MATERIAL_SAVE, "TitleCommand", 5);
        this.getToolbar().addCommandToLeftBar(null,icon,evt1 -> new UserShowForm(user.getUsername(),user).show());
        this.getToolbar().addCommandToRightBar(null,save,evt1 ->{
            user.setNom(nom.getText());
            user.setPrenom(prenom.getText());
            System.out.println(user);
            if(UserService.getInstance().update(user))
                Dialog.show("Successful", user.getUsername()+" updated !" , "OK", null);
            else
                Dialog.show("Failure", user.getUsername()+" cannot updated !" , "OK", null);

        } );
        Container ct = new Container(BoxLayout.y());
        EncodedImage placeHolder = EncodedImage.createFromImage(theme.getImage("person.png"), false);
        if(user.getImage() == null || user.getImage().equals(""))
            img = theme.getImage("person.png");
        else if(user.getImage().contains("google")){
            String url = user.getImage();
            img = URLImage.createToStorage(placeHolder,user.getUsername(),url);
        }else
            img = theme.getImage(user.getUsername());

        uploadImg = FontImage.createMaterial(FontImage.MATERIAL_FILE_UPLOAD, "TitleCommand", 5).toImage();
        Button button = new Button("upload");

        button.addActionListener(evt -> {
            ActionListener callback = e->{
                if (e != null && e.getSource() != null) {
                    String filePath = (String)e.getSource();
                    System.out.println(filePath);
                    try {
//                        img = Image.createImage(filePath);
                        img = theme.getImage("person.png");
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

        Container upload = new Container(new FlowLayout(Component.CENTER,Component.CENTER));
        upload.add(uploadImg).add(button);
        upload.setLeadComponent(button);

        Container ct1 = new Container(BoxLayout.y());
        ct1.addAll(nom,prenom,email);
        ct.add(upload).add(img);
        this.addAll(ct,ct1);
    }
}
