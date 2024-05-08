package com.example.doancoso1;

import Database.Database;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Module.AlertMessage;
import javafx.stage.Stage;
import Module.Users;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import Module.Data;

public class Teacher implements Initializable {
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
    private TextField login_teacherID;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private AnchorPane main_form;

    @FXML
    private CheckBox register_checkbox;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullname;

    @FXML
    private Hyperlink register_login;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showpassword;

    @FXML
    private Button register_signup;

    @FXML
    private TextField register_teacherId;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    public void loginAccount(){
        if (login_teacherID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Inccorrect Username/Password");
        }else{
            String sql = "SELECT * FROM teacher WHERE teacher_id = ? AND password = ? AND delete_date IS NULL";
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

                String checkStatus = "SELECT status FROM teacher WHERE teacher_id = '"
                  + login_teacherID.getText() + "' AND password = '"
                  + login_password.getText() + "' AND status = 'Confirm'";

                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if(result.next()){
                    if(!login_showpassword.isVisible()){
                        if(!login_showpassword.getText().equals(login_password.getText())){
                            login_showpassword.setText(login_password.getText());
                        }
                    }else{
                        if(!login_showpassword.getText().equals(login_password.getText())){
                            login_password.setText(login_showpassword.getText());
                        }
                    }

                    alert.errorMessage("Need the confirmation of the Admin");
                }else{
                    prepare= connect.prepareStatement(sql);
                    prepare.setString(1, login_teacherID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if(result.next()){
                       Data.teacher_id = result.getString("teacher_id");
                       Data.teacher_name = result.getString("full_name");

                        alert.successMessage("Login Success");

                        Parent root = FXMLLoader.load(getClass().getResource("TeacherMainForm.fxml"));
                        Stage stage = new Stage();

                        stage.setTitle("Online Learning System | Teacher Main Form");
                        stage.setScene(new Scene(root));
                        stage.show();

                        login_Btn.getScene().getWindow().hide();
                    }else{
                        alert.errorMessage("Incorrect Username/Password");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
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
        if(register_fullname.getText().isEmpty()
                || register_email.getText().isEmpty()
                || register_teacherId.getText().isEmpty()
                || register_password.getText().isEmpty()){
            alert.errorMessage("Please fill all the fields");
        }else{
            String checkDoctor = "SELECT * FROM teacher WHERE teacher_id = '"
                    + register_teacherId.getText() + "'";

            connect = Database.connectDb();

            try{
                if(!register_showpassword.isVisible()){
                    if(!register_showpassword.getText().equals(register_password.getText())){
                        register_showpassword.setText(register_password.getText());
                    }else{
                        if(!register_showpassword.getText().equals(register_password.getText())){
                            register_password.setText(register_showpassword.getText());
                        }
                    }
                }
                prepare = connect.prepareStatement(checkDoctor);
                result = prepare.executeQuery();

                if(result.next()){
                    alert.errorMessage(register_teacherId.getText() + " is already taken");
                }else if(register_password.getText().length() < 8){
                    alert.errorMessage("Inavlid password, at least 8 character needed");
                }else{

                    String password = register_password.getText();
                    String hashedPassword = hashPassword(password);

                    String insertData = "INSERT INTO teacher (full_name, email, teacher_id, password, date, status) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(1, register_fullname.getText());
                    prepare.setString(2, register_email.getText());
                    prepare.setString(3, register_teacherId.getText());
                    prepare.setString(4, hashedPassword);
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.setString(6, "Confirm");

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully");

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

    public void registerTeacherID(){
        String doctorID = "DID-";
        int tempID = 0;
        String sql = "SELECT MAX(id) FROM teacher";

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                tempID = result.getInt("MAX(id)");

            }
            if(tempID == 0){
                tempID += 1;
                doctorID += tempID;
            }else{
                doctorID += (tempID+1);
            }
            register_teacherId.setText(doctorID);
            register_teacherId.setDisable(true);
        }catch(Exception e){
            e.printStackTrace();
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
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
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
                Parent root = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
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
                Parent root = FXMLLoader.load(getClass().getResource("Student.fxml"));
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
        if(event.getSource() == register_login){
            login_form.setVisible(true);
            register_form.setVisible(false);
        }else if(event.getSource() == login_register){
            login_form.setVisible(false);
            register_form.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerTeacherID();
        userList();
    }


}
