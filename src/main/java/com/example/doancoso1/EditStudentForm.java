package com.example.doancoso1;

import Database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Module.Data;
import Module.AlertMessage;

public class EditStudentForm implements Initializable {
    @FXML
    private TextField edit_ID;

    @FXML
    private TextArea edit_address;

    @FXML
    private TextField edit_contact;

    @FXML
    private AnchorPane edit_form;

    @FXML
    private ComboBox<String> edit_gender;

    @FXML
    private TextField edit_name;

    @FXML
    private ComboBox<String> edit_status;

    @FXML
    private Button edit_update;

    private AlertMessage alert = new AlertMessage();
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void updateBtn(){
        if(edit_ID.getText().isEmpty() || edit_name.getText().isEmpty()
                || edit_gender.getSelectionModel().getSelectedItem() == null
                || edit_contact.getText().isEmpty()
                || edit_address.getText().isEmpty()
        || edit_status.getSelectionModel().getSelectedItem() == null){
            alert.errorMessage("Please fill all the fields");
        }else{
            String updateData = "UPDATE student SET full_name = ?, gender = ?, mobile_number = ?, address = ?" +
                      ", status = ?, date_modify = ? "
                    + "WHERE student_id = '" + edit_ID.getText() + "'";

            connect = Database.connectDb();

            try{
                if(alert.confirmationMessage("Are you want to UPDATE student ID: " + edit_ID.getText() + "?")){
                    prepare = connect.prepareStatement(updateData);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(1, edit_name.getText());
                    prepare.setString(2, edit_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(3, edit_contact.getText());
                    prepare.setString(4, edit_address.getText());
                    prepare.setString(5, edit_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();
                    alert.successMessage("Updated Successfully");
                }else{
                    alert.errorMessage("Cancelled");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setField(){
        edit_ID.setText(String.valueOf(Data.temp_studentID));
        edit_name.setText(Data.temp_name);
        edit_gender.getSelectionModel().select(Data.temp_gender);
        edit_contact.setText(String.valueOf(Data.temp_number));
        edit_address.setText(Data.temp_address);
        edit_status.getSelectionModel().select(Data.temp_status);
    }
    public void genderList(){
        List<String> genderL = new ArrayList<>();
        for(String data : Data.gender){
            genderL.add(data);
        }
        ObservableList listData = FXCollections.observableList(genderL);
        edit_gender.setItems(listData);
    }
    public void statusList(){
        List<String> statusL = new ArrayList<>();
        for(String data : Data.status){
            statusL.add(data);
        }
        ObservableList listData = FXCollections.observableList(statusL);
        edit_status.setItems(listData);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setField();
        genderList();
        statusList();
    }
}
