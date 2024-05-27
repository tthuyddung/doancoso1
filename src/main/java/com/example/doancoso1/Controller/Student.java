package com.example.doancoso1.Controller;

import DataConnection.Database;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Model.Users;
import Model.AlertMessage;
import javafx.stage.Stage;


public class Student implements Initializable {
    private Button login_Btn;

    private CheckBox login_checkbox;

    private AnchorPane login_form;

    private PasswordField login_password;

    private TextField login_showpassword;

    private TextField login_studentID;

    private ComboBox<?> login_user;

    private AnchorPane main_form;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private AlertMessage alert = new AlertMessage();

    public void loginAccount(){
        if (login_studentID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Inccorrect Username/Password");
        }else{
            String sql = "SELECT * FROM student WHERE student_id = ? AND password = ? AND date_delete IS NULL";
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

                String checkStatus = "SELECT status FROM student WHERE student_id = '"
                        + login_studentID.getText() + "' AND password = '"
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
                    prepare.setString(1, login_studentID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if(result.next()){
                        alert.successMessage("Login Success");
                    }else{
                        alert.errorMessage("Incorrect Username/Password");
                    }
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
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Student.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Online Learning System");

                stage.setMinWidth(402);
                stage.setMaxHeight(598);

                stage.setScene(new Scene(root));
                stage.show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        login_user.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList();
    }
}
