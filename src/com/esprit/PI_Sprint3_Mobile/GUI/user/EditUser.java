package com.esprit.PI_Sprint3_Mobile.GUI.user;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.entities.User;
import com.esprit.PI_Sprint3_Mobile.services.UserService;
import com.esprit.PI_Sprint3_Mobile.utils.FileChooser.FileChooser;

import java.awt.*;
import java.io.File;
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
    private File imageFile;
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
            User user2 = new User();
            user2.setId(user.getId());
            user2.setNom(nom.getText());
            user2.setPrenom(prenom.getText());
            user2.setEmail(email.getText());
//            user2.setImage(img.getImageName());
            System.out.println(user2);
            if(UserService.getInstance().update(user2))
                Dialog.show("Successful", user.getUsername()+" updated !" , "OK", null);
            else
                Dialog.show("Failure", user.getUsername()+" cannot updated !" , "OK", null);

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

        uploadImg = FontImage.createMaterial(FontImage.MATERIAL_FILE_UPLOAD, "TitleCommand", 5).toImage();
        Button button = new Button("upload");

        button.addActionListener(evt -> {
            ActionListener callback = e->{
                if (e != null && e.getSource() != null) {
                    String filePath = (String)e.getSource();
                    try {
                        img = Image.createImage(filePath);
                        imageViewer = new ImageViewer(img);

                        filePath = filePath.replace("file://","");
                        System.out.println(filePath);
                        System.out.println(FileSystemStorage.getInstance().getRoots()[0]);
                        File imageFile = new File(filePath);
                        File newFile = new File("D:/BASSEM/Videos/Captures/test.jpg");


                        javax.imageio.ImageIO.write(javax.imageio.ImageIO.read(imageFile),"testWrite",newFile);
//                        img = theme.getImage("person.png");
                        OutputStream os = FileSystemStorage.getInstance().openOutputStream(filePath);
                        ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 5);
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

//        Container ct1 = new Container(BoxLayout.y());
//        ct1.addAll(nom,prenom,email);
        ct.addAll(nom,prenom,email);

        this.addAll(upload,imageViewer,ct);
    }
}
