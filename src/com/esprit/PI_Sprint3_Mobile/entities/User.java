package com.esprit.PI_Sprint3_Mobile.entities;



import java.time.LocalDateTime;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
    private LocalDateTime birth_date;
    private Boolean is_verified;
    private String roles;
    private String google_id;
    private LocalDateTime creation_date;
    private LocalDateTime last_login;
    private String image;
    private Club club;



    public User() {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.is_verified = false;
        this.password = "123456" ;
        this.creation_date = LocalDateTime.now();
        this.last_login = LocalDateTime.now();
        this.club = new Club();
    }

    public User(int id, String nom, String prenom, String email, String username, String password, LocalDateTime birth_date, Boolean is_verified, String roles, String google_id, LocalDateTime creation_date, LocalDateTime last_login, String image) {
//    public User(int id, String nom, String prenom, String email, String username, String password, String plain_password, Boolean is_verified, String roles, String google_id,  String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birth_date = birth_date;
        this.is_verified = false;
        this.roles = roles;
        this.google_id = google_id;
        this.creation_date = LocalDateTime.now();
        this.last_login = LocalDateTime.now();
        this.image = image;
        this.club = new Club();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public LocalDateTime getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDateTime birth_date) {
        this.birth_date = birth_date;
    }

    public Boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birth_date=" + birth_date +
                ", is_verified=" + is_verified +
                ", roles='" + roles + '\'' +
                ", google_id='" + google_id + '\'' +
                ", creation_date=" + creation_date +
                ", last_login=" + last_login +
                ", image='" + image + '\'' +
                '}';
    }
}
