package com.example.doancoso1.Controller;

import DataConnection.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import Model.Data;

import Model.AlertMessage;
import Model.Users;
import javafx.stage.Stage;

public class Login implements Initializable {

    @FXML
    private Button login_Btn;

    @FXML
    private CheckBox login_checkbox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_register;

    @FXML
    private TextField login_showpassword;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private CheckBox register_checkbox;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_login;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showpassword;

    @FXML
    private Button register_signup;

    @FXML
    private TextField register_username;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    public void loginAccount(){
        if(login_username.getText().isEmpty()
        || login_password.getText().isEmpty()){
            alert.errorMessage("Incorrect Username/Password");
        }else{
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

            connect = Database.connectDb();

            try{
                if(!login_showpassword.isVisible()){
                    if(!login_showpassword.getText().equals(login_password.getText())){
                        login_showpassword.setText(login_password.getText());
                    }
                }else{
                    if(!login_showpassword.getText().equals(login_password.getText())){
                        login_password.setText(login_showpassword.getText());
                    }
                }
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                if(result.next()){

                    Data.admin_username = login_username.getText();
                    Data.admin_id= Integer.parseInt(result.getString("admin_id"));

                    alert.successMessage("Login Successful");

                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/AdminMainForm.fxml"));
                    Stage stage = new Stage();

                    stage.setTitle("Online Learning Sysytem");
                    stage.setScene(new Scene(root));
                    stage.show();

                    login_Btn.getScene().getWindow().hide();
                }else{
                    alert.errorMessage("Incorrect Username/Password");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void loginShowPassword(){
        if(login_checkbox.isSelected()){
            login_showpassword.setText(login_password.getText());
            login_showpassword.setVisible(true);
            login_password.setVisible(false);
        }else{
            login_password.setText(login_showpassword.getText());
            login_showpassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    public void registerAccount(){
        if(register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()){
            alert.errorMessage("Please fill all blank fields");
        }else{
            String checkUsername = "SELECT * FROM admin WHERE username = '"
                    + register_username.getText() + "'";

            connect = Database.connectDb();

            try{
                if(!register_checkbox.isVisible()){
                    if(!register_showpassword.getText().equals(register_password.getText())){
                        register_showpassword.setText(register_password.getText());
                    }else{
                        if(!register_showpassword.getText().equals(register_password.getText())){
                            register_password.setText(register_showpassword.getText());
                        }
                    }
                }
                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if(result.next()) {
                    alert.errorMessage(register_username.getText() + " is already exist");
                }else if(register_password.getText().length() < 8){
                    alert.errorMessage("Inavlid password, at least 8 characters needed");
                }else{

                    String password = register_password.getText();
                    String hashedPassword = hashPassword(password);

                    String insertData = "INSERT INTO admin (email, username, password, date) VALUES (?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, hashedPassword);
                    prepare.setString(4, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully");

                    registerClear();

                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void registerClear(){
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showpassword.clear();
    }
    public void registerShowPassword(){
        if(register_checkbox.isSelected()){
            register_showpassword.setText(register_password.getText());
            register_showpassword.setVisible(true);
            register_password.setVisible(false);
        }else{
            register_password.setText(register_showpassword.getText());
            register_showpassword.setVisible(false);
            register_password.setVisible(true);
        }
    }
    public void userList(){
        List<String> listU = new ArrayList<>();

        for(String data : Users.user){
            listU.add(data);
        }
        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage(){
        if(login_user.getSelectionModel().getSelectedItem() == "Admin Portal"){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Login.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Online Learning System");

                stage.setMinWidth(402);
                stage.setMaxHeight(598);

                stage.setScene(new Scene(root));
                stage.show();


            }catch(Exception e) {
                e.printStackTrace();
            }
        }else if(login_user.getSelectionModel().getSelectedItem() == "Teacher Portal") {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Teacher.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Online Learning System");

                stage.setMinWidth(402);
                stage.setMaxHeight(598);

                stage.setScene(new Scene(root));
                stage.show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(login_user.getSelectionModel().getSelectedItem() == "Student Portal"){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Student.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Online Learning System");

                stage.setMinWidth(402);
                stage.setMaxHeight(598);

                stage.setScene(new Scene(root));
                stage.show();


            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        login_user.getScene().getWindow().hide();
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == login_register){
            login_form.setVisible(false);
            register_form.setVisible(true);
        }else if(event.getSource() == register_login){
            login_form.setVisible(true);
            register_form.setVisible(false);
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        userList();
    }
}
