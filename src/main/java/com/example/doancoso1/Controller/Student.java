package com.example.doancoso1.Controller;

import DataConnection.Database;
import java.sql.Connection;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Model.Users;
import Model.AlertMessage;
import javafx.stage.Stage;
import Model.Data;


public class Student implements Initializable {
    @FXML
    private Button login_Btn;

    @FXML
    private CheckBox login_checkbox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showpassword;

    @FXML
    private TextField login_studentID;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private AnchorPane main_form;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private AlertMessage alert = new AlertMessage();

    @FXML
    void loginAccount(ActionEvent event) {
        if (!this.login_studentID.getText().isEmpty() && !login_password.getText().isEmpty()) {
            String sql = "SELECT * FROM student WHERE student_id = ? AND password = ? AND date_delete IS NULL";
            this.connect = Database.connectDb();

            try {
                if (!login_showpassword.isVisible()) {
                    if (!login_showpassword.getText().equals(login_password.getText())) {
                        login_showpassword.setText(login_password.getText());
                    }
                } else if (!login_showpassword.getText().equals(login_password.getText())) {
                    login_password.setText(login_showpassword.getText());
                }

                String checkStatus = "SELECT status FROM student WHERE student_id = '" + login_studentID.getText() + "' AND password = '" + login_password.getText() + "' AND status = 'Confirm'";
                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();
                if (result.next()) {
                    if (!login_showpassword.isVisible()) {
                        if (!login_showpassword.getText().equals(login_password.getText())) {
                            login_showpassword.setText(login_password.getText());
                        }
                    } else if (!login_showpassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showpassword.getText());
                    }

                    alert.errorMessage("Need the confimation of the Admin!");
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_studentID.getText());
                    prepare.setString(2, login_password.getText());
                    result = prepare.executeQuery();
                    if (result.next()) {

                        Data.student_id = Integer.parseInt(login_studentID.getText());
                        alert.successMessage("Login Successfully!");
                        Parent root = (Parent)FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/StudentMainForm.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        login_Btn.getScene().getWindow().hide();
                    } else {
                        alert.errorMessage("Incorrect Student ID/Password");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.errorMessage("Incorrect Studenr ID/Password");
        }

    }
    @FXML
    void loginShowPassword(ActionEvent event) {
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
    @FXML
    void switchPage(ActionEvent event) {
        Parent root;
        Stage stage;
        if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {
            try {
                root = (Parent)FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Login.fxml"));
                stage = new Stage();
                stage.setTitle("Online Learning System");
                stage.setMinWidth(340.0);
                stage.setMinHeight(580.0);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (login_user.getSelectionModel().getSelectedItem() == "Teacher Portal") {
            try {
                root = (Parent)FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/TeacherMainForm.fxml"));
                stage = new Stage();
                stage.setTitle("Online Learning System");
                stage.setMinWidth(340.0);
                stage.setMinHeight(580.0);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (login_user.getSelectionModel().getSelectedItem() == "Student Portal") {
            try {
                root = (Parent)FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/StudentMainForm.fxml"));
                stage = new Stage();
                stage.setTitle("Online Learning System");
                stage.setMinWidth(340.0);
                stage.setMinHeight(580.0);
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