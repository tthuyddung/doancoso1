package com.example.doancoso1.Controller;


import DataConnection.Database;
import Model.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Model.Data;


public class EditAppointmentForm implements Initializable {
    @FXML
    private AnchorPane appointment_tableview;

    @FXML
    private TextField editAppointment_id;

    @FXML
    private TextField editAppointment_name;

    @FXML
    private ComboBox<String> editAppointment_gender;

    @FXML
    private TextField editAppointment_contact;

    @FXML
    private TextField editAppointment_address;

    @FXML
    private ComboBox<String> editAppointment_status;

    @FXML
    private Button editApp_updateBtn;

    @FXML
    private Button editApp_cancelBtn;

    @FXML
    private TextField editAppointment_description;

    @FXML
    private TextField editAppointment_diagnoisis;

    @FXML
    private TextField editAppointment_treatment;

    @FXML
    private ComboBox<String> editAppointment_teacher;

    @FXML
    private ComboBox<String> editAppointment_specialized;


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private AlertMessage alert = new AlertMessage();

    public void displayAppoimentData(){
        String sql = "SELECT * FROM appointment WHERE appointment_id = '"
                + editAppointment_id .getText()+"'";
        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                editAppointment_name.setText(result.getString("name"));
                editAppointment_gender.getSelectionModel().select(result.getString("gender"));
                editAppointment_contact.setText(result.getString("mobile_number"));
                editAppointment_address.setText(result.getString("address"));
                editAppointment_description.setText(result.getString("description"));
                editAppointment_treatment.setText(result.getString("treatment"));
                editAppointment_teacher.getSelectionModel().select(result.getString("teacher"));
                editAppointment_specialized.getSelectionModel().select(result.getString("specialized"));
                editAppointment_status.getSelectionModel().select(result.getString("status"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateBtn(){
        connect = Database.connectDb();

        if(editAppointment_id.getText().isEmpty()
                || editAppointment_name.getText().isEmpty()
                || editAppointment_gender.getSelectionModel().getSelectedItem()==null
                || editAppointment_contact.getText().isEmpty()
                || editAppointment_address.getText().isEmpty()
                || editAppointment_description.getText().isEmpty()
                || editAppointment_treatment.getText().isEmpty()
                || editAppointment_teacher.getSelectionModel().getSelectedItem()==null
                || editAppointment_specialized.getSelectionModel().getSelectedItem()==null
                || editAppointment_status.getSelectionModel().getSelectedItem()==null) {
            alert.errorMessage("Please fill all blank fields ");
        }else{
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            if(Data.path == null || "".equals(Data.path)){
                String updateData = "UPDATE appointment SET name ='"
                        + editAppointment_name.getText() + "', gender='"
                        + editAppointment_gender.getSelectionModel().getSelectedItem()+ "', mobile_number='"
                        + editAppointment_contact.getText() + "', address='"
                        + editAppointment_address.getText() + "', description = '"
                        + editAppointment_description.getText() + "', treatment='"
                        + editAppointment_treatment.getText() + "', teacher='"
                        + editAppointment_teacher.getSelectionModel().getSelectedItem()+ "', specialized='"
                        + editAppointment_specialized.getSelectionModel().getSelectedItem() + "', status = '"
                        +editAppointment_status.getSelectionModel().getSelectedItem() + "', date_modify = '"
                        + String.valueOf(sqlDate) + "' "
                        + "WHERE appointment_id = '" + editAppointment_id.getText() + "'";
                try {
                    if (alert.confirmationMessage("Are you sure you want to update Appointment ID: " + editAppointment_id.getText() + "?")) {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        alert.successMessage("Updated Successfully!");
                    }else{
                        alert.errorMessage("Cancelled");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }


        }

        displayAppoimentData();
    }


    public void cancelBtn(){
        if (alert.confirmationMessage("Are you sure you want to cancel ?")) {
            javafx.application.Platform.exit();
        }
        displayAppoimentData();
    }
    public void displayFields(){
        editAppointment_id.setText(Data.temp_appID);
        editAppointment_name.setText(Data.temp_appName);
        editAppointment_gender.getSelectionModel().select(Data.temp_appGender);
        editAppointment_contact.setText(Data.temp_appMobileNumber);
        editAppointment_address.setText(Data.temp_appAddress);
        editAppointment_description.setText(Data.temp_appDescription);
        editAppointment_diagnoisis.setText(Data.temp_appDiagnosis);
        editAppointment_treatment.setText(Data.temp_appTreatment);
        editAppointment_teacher.getSelectionModel().select(Data.temp_appTeacher);
        editAppointment_specialized.getSelectionModel().select(Data.temp_appSpecialized);
        editAppointment_status.getSelectionModel().select(Data.temp_appStatus);
    }
    public void teacherList(){
        String sql = "SELECT * FROM teacher WHERE delete_date is NULL";

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("teacher_id"));
            }

            editAppointment_teacher.setItems(listData);
            specializedList();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //    public void specializedList(){
//        String sql = "SELECT * FROM teacher WHERE delete_date IS NULL AND teacher_id = '"
//        + editAppointment_teacher.getSelectionModel().getSelectedItem() +"'";
//
//        connect = Database.connectDb();
//
//        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//            ObservableList listData = FXCollections.observableArrayList();
//
//            if(result.next()){
//                listData.add(result.getString("specialized"));
//            }
//            editAppointment_specialized.setItems(listData);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    public void specializedList(){
        List<String> genderL = new ArrayList<>();

        for(String data : Data.specialization){
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editAppointment_specialized.setItems(listData);
    }

    public void genderList(){
        List<String> genderL = new ArrayList<>();

        for(String data : Data.gender){
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editAppointment_gender.setItems(listData);
    }
    public void statusList(){
        List<String> statusL = new ArrayList<>();

        for(String data : Data.status){
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editAppointment_status.setItems(listData);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayFields();
        specializedList();
        teacherList();
        genderList();
        statusList();




    }
}