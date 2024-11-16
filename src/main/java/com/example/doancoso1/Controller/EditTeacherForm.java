package com.example.doancoso1.Controller;

import DataConnection.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Model.Data;
import Model.AlertMessage;
import javafx.stage.FileChooser;

public class EditTeacherForm implements Initializable {

    @FXML
    private TextField editTeacher_id;

    @FXML
    private TextField editTeacher_fullname;

    @FXML
    private TextField editTeacher_email;

    @FXML
    private TextField editTeacher_password;

    @FXML
    private ComboBox<String> editTeacher_gender;

    @FXML
    private TextField editTeacher_mobileNumber;

    @FXML
    private ImageView editTeacher_imageView;

    @FXML
    private Button editTeacher_importBtn;

    @FXML
    private TextField editTeacher_address;

    @FXML
    private ComboBox<String> editTeacher_status;

    @FXML
    private Button editTeacher_updateBtn;

    @FXML
    private Button editTeacher_cancelBtn;

    @FXML
    private ComboBox<String> editTeacher_specialized;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void importBtn(){
        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*jpg", "*png", "*jpeg"));

        File file = open.showOpenDialog(editTeacher_importBtn.getScene().getWindow());
        if(file != null){

            Data.path=file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 135, 151, false, true );
            editTeacher_imageView.setImage(image);


        }
    }

    public void displayTeacherData(){
        String sql = "SELECT * FROM teacher WHERE teacher_id = '"
                + editTeacher_id .getText()+"'";
        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                editTeacher_fullname.setText(result.getString("fullname"));
                editTeacher_email.setText(result.getString("email"));
                editTeacher_password.setText(result.getString("password"));
                editTeacher_specialized.getSelectionModel().select(result.getString("specialized"));
                editTeacher_gender.getSelectionModel().select(result.getString("gender"));
                editTeacher_mobileNumber.setText(result.getString("mobile_number"));
                editTeacher_address.setText(result.getString("address"));
                editTeacher_status.getSelectionModel().select(result.getString("status"));

                image = new Image("File: " + result.getString("image"), 135, 151, false, true );
                editTeacher_imageView.setImage(image);


            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateBtn(){
        connect = Database.connectDb();

        if(editTeacher_id.getText().isEmpty()
                || editTeacher_fullname.getText().isEmpty()
                || editTeacher_email.getText().isEmpty()
                || editTeacher_password.getText().isEmpty()
                || editTeacher_specialized.getSelectionModel().getSelectedItem()==null
                || editTeacher_gender.getSelectionModel().getSelectedItem()==null
                || editTeacher_mobileNumber.getText().isEmpty()
                || editTeacher_address.getText().isEmpty()
                || editTeacher_status.getSelectionModel().getSelectedItem()==null) {
            alert.errorMessage("Please fill all blank fields ");
        }else{
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            if(Data.path == null || "".equals(Data.path)){
                String updateData = "UPDATE teacher SET fullname ='"
                        + editTeacher_fullname.getText() + "', email='"
                        + editTeacher_email.getText() + "', password='"
                        + editTeacher_password.getText() + "', specialized='"
                        + editTeacher_specialized.getSelectionModel().getSelectedItem() + "', gender = '"
                        + editTeacher_gender.getSelectionModel().getSelectedItem() + "', mobile_number='"
                        + editTeacher_mobileNumber.getText() + "', address='"
                        + editTeacher_address.getText() + "', status='"
                        + editTeacher_status.getSelectionModel().getSelectedItem() + "', modify_date = '"
                        + String.valueOf(sqlDate) + "' "
                        + "WHERE teacher_id = '" + editTeacher_id.getText() + "'";
                try {
                    if (alert.confirmationMessage("Are you sure you want to update Teacher ID: " + editTeacher_id.getText() + "?")) {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        alert.successMessage("Updated Successfully!");
                    }else{
                        alert.errorMessage("Cancelled");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }else {
                try{
                    if (alert.confirmationMessage("Are you sure you want to update teacher ID: "
                            + editTeacher_id.getText() + "?")) {
                        String path = Data.path;
                        path = path.replace("\\","\\\\");
                        Path transfer = Paths.get(path);

                        Path copy = Paths.get("C:\\Users\\Dieu Linh\\IdeaProjects\\doancoso1\\src\\main\\resources\\com\\example\\doancoso1\\Image"
                                + editTeacher_id.getText() + ".jpg");

                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                        String insertImage = copy.toString();

                        insertImage = insertImage.replace("\\", "\\\\");

                        String updateData = "UPDATE teacher SET fullname ='"
                                + editTeacher_fullname.getText() + "', email='"
                                + editTeacher_email.getText() + "', password='"
                                + editTeacher_password.getText() + "', specialized='"
                                + editTeacher_specialized.getSelectionModel().getSelectedItem() + "', gender = '"
                                + editTeacher_gender.getSelectionModel().getSelectedItem() + "', mobile_number='"
                                + editTeacher_mobileNumber.getText() + "', image = '"
                                + insertImage + "', address ='"
                                + editTeacher_address.getText() + "', status='"
                                + editTeacher_status.getSelectionModel().getSelectedItem() + "' "
                                + "WHERE teacher_id = '" + editTeacher_id.getText() + "'";
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        alert.successMessage("Updated Successfully!");

                    } else {
                        alert.errorMessage("Cancelled");
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }



            }
        }
        displayTeacherData();
    }


    public void cancelBtn(){
        if (alert.confirmationMessage("Are you sure you want to cancel ?")) {
            javafx.application.Platform.exit();
        }
        displayTeacherData();
    }


    public void setField(){
        editTeacher_id.setText(String.valueOf(Data.temp_teacherID));
        editTeacher_fullname.setText(Data.temp_teacherName);
        editTeacher_email.setText(Data.temp_teacherEmail);
        editTeacher_password.setText(Data.temp_teacherPassword);
        editTeacher_specialized.getSelectionModel().select(Data.temp_teacherSpecialized);
        editTeacher_gender.getSelectionModel().select(Data.temp_teacherGender);
        editTeacher_mobileNumber.setText(String.valueOf(Data.temp_teacherMobileNumber));
        editTeacher_address.setText(Data.temp_teacherAddress);
        editTeacher_status.getSelectionModel().select(Data.temp_teacherStatus);

        image = new Image("File: "+ Data.temp_teacherImagePath, 135, 151, false, true);
        editTeacher_imageView.setImage(image);

    }

    public void specializationList(){
        List<String> specializationL = new ArrayList<>();
        for(String data : Data.specialization){
            specializationL.add(data);
        }
        ObservableList listData = FXCollections.observableList(specializationL);
        editTeacher_specialized.setItems(listData);
    }

    public void genderList(){
        List<String> genderL = new ArrayList<>();

        for(String data : Data.gender){
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editTeacher_gender.setItems(listData);
    }

    public void statusList(){
        List<String> statusL = new ArrayList<>();

        for(String data : Data.status){
            statusL.add(data);
        }
        ObservableList listData = FXCollections.observableList(statusL);
        editTeacher_status.setItems(listData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setField();
        specializationList();
        genderList();
        statusList();

    }
}