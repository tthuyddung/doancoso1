package com.example.doancoso1.Controller;

import DataConnection.Database;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Model.Data;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import Model.AlertMessage;
import Model.TeacherData;
import Model.StudentData;
import Model.AppointmentData;
import javafx.scene.image.Image;





public class AdminMainForm implements Initializable {

    @FXML
    private TableColumn<AppointmentData, String> appointment_ID;

    @FXML
    private TableColumn<AppointmentData, String> appointment_action;

    @FXML
    private Button appointment_btn;

    @FXML
    private Button Logout_btn;

    @FXML
    private TableColumn<AppointmentData, String> appointment_contact;

    @FXML
    private TableColumn<AppointmentData, String> appointment_date;

    @FXML
    private TableColumn<AppointmentData, String> appointment_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointment_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointment_description;

    @FXML
    private AnchorPane appointment_form;

    @FXML

    private TableColumn<AppointmentData, String> appointment_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointment_name;

    @FXML
    private TableColumn<AppointmentData, String> appointment_status;

    @FXML
    private TableView<AppointmentData> appointment_tableview;

    @FXML
    private Label current_form;

    @FXML
    private Label dashboard_AS;

    @FXML
    private Label dashboard_AT;

    @FXML
    private Label dashboard_TA;

    @FXML
    private Label dashboard_TS;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AreaChart<?, ?> dashboard_char_SD;

    @FXML
    private BarChart<?, ?> dashboard_char_TD;

    @FXML
    private TableColumn<TeacherData, String> dashboard_col_name;

    @FXML
    private TableColumn<TeacherData, String> dashboard_col_specialized;

    @FXML
    private TableColumn<TeacherData, String> dashboard_col_status;

    @FXML
    private TableColumn<TeacherData, String> dashboard_col_teacherID;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<TeacherData> dashboard_tableview;

    @FXML
    private Label date_time;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button payment_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private Button student_btn;

    @FXML
    private TableColumn<StudentData, String> student_col_ID;

    @FXML
    private TableColumn<StudentData, String> student_col_action;

    @FXML
    private TableColumn<StudentData, String> student_col_contact;

    @FXML
    private TableColumn<StudentData, String> student_col_date;

    @FXML
    private TableColumn<StudentData, String> student_col_dateDelete;

    @FXML
    private TableColumn<StudentData, String> student_col_dateModify;

    @FXML
    private TableColumn<StudentData, String> student_col_description;

    @FXML
    private TableColumn<StudentData, String> student_col_gender;

    @FXML
    private TableColumn<StudentData, String> student_col_name;

    @FXML
    private TableColumn<StudentData, String> student_col_status;

    @FXML
    private AnchorPane student_form;

    @FXML
    private TableView<StudentData> student_tableview;

    @FXML
    private Button teacher_btn;

    @FXML
    private TableView<TeacherData> teacher_tableview;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_ID;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_name;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_gender;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_contact;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_email;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_specialized;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_address;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_status;

    @FXML
    private TableColumn<TeacherData, String> teacher_col_action;

    @FXML
    private AnchorPane teacher_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;
    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_adminID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_adminID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private TableView<StudentData> payment_tableView;

    @FXML
    private TableColumn<StudentData, String> payment_col_studentID;

    @FXML
    private TableColumn<StudentData, String> payment_col_name;

    @FXML
    private TableColumn<StudentData, String> payment_col_gender;

    @FXML
    private TableColumn<StudentData, String> payment_col_diagnosis;

    @FXML
    private TableColumn<StudentData, String> payment_col_teacher;

    @FXML
    private TableColumn<StudentData, String> payment_col_date;

    @FXML
    private Circle payment_circle;

    @FXML
    private Label payment_studentID;

    @FXML
    private Label payment_name;

    @FXML
    private Label payment_date;

    @FXML
    private Label payment_teacher;

    @FXML
    private Button payment_checkoutBtn;



    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private AlertMessage alert = new AlertMessage();
    private Image image;



    // tổng gvien hoạt động
    public void dashboard_AT() {
        String sql = "SELECT COUNT(id) FROM teacher WHERE status = 'Active' AND delete_date IS NULL";
        connect = Database.connectDb();
        int tempAD = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempAD = result.getInt("COUNT(id)");
            }

            dashboard_AT.setText(String.valueOf(tempAD));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // tổng hsinh
    public void dashboardTS() {
        String sql = "SELECT COUNT(id) FROM student WHERE date_delete IS NULL";
        connect = Database.connectDb();
        int tempTP = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempTP =result.getInt("COUNT(id)");
            }

            dashboard_TS.setText(String.valueOf(tempTP));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // tổng hsinh hoạt động
    public void dashboard_AS() {
        String sql = "SELECT COUNT(id) FROM student WHERE date_delete IS NULL AND status = 'Active'";
        connect = Database.connectDb();
        int tempAP = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()){
                tempAP = result.getInt("COUNT(id)");
            }

            dashboard_AS.setText(String.valueOf(tempAP));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // tổng cuộc hẹn
    public void dashboard_TA() {
        String sql = "SELECT COUNT(id) FROM appointment WHERE date_delete IS NULL";
        connect = Database.connectDb();
        int tempTA = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempTA =result.getInt("COUNT(id)");
            }

            dashboard_TA.setText(String.valueOf(tempTA));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void dashboardStudentDataChart() {
        dashboard_char_SD.getData().clear();
        String selectData = "SELECT date, COUNT(id) FROM student WHERE date_delete IS NULL GROUP BY TIMESTAMP(datE) ASC LIMIT 8";
        connect = Database.connectDb();
        XYChart.Series chart = new XYChart.Series();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while(result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            dashboard_char_SD.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void dashboardTeacherDataChart() {
        dashboard_char_TD.getData().clear();
        String selectData = "SELECT date, COUNT(id) FROM teacher WHERE delete_date IS NULL GROUP BY TIMESTAMP(date) ASC LIMIT 6";
        connect = Database.connectDb();
        XYChart.Series chart = new XYChart.Series();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while(result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            dashboard_char_TD.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //dashboard
    //truy xuất dữ liệu giáo viên từ cơ sở dữ liệu và trả về dưới dạng mảng
    public ObservableList<TeacherData> dashboardGetTeacherData() {
        ObservableList<TeacherData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM teacher WHERE delete_date IS NULL ";

        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            TeacherData tData;

            while(result.next()) {
                tData = new TeacherData(
                        result.getString("teacher_id"),
                        result.getString("fullname"),
                        result.getString("specialized"),
                        result.getString("status"));

                listData.add(tData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<TeacherData> dashboardListData;
    public void dashboardGetTeacherDisplayData() {
        dashboardListData =dashboardGetTeacherData();
        dashboard_col_teacherID.setCellValueFactory(new PropertyValueFactory("teacherID"));
        dashboard_col_name.setCellValueFactory(new PropertyValueFactory("fullname"));
        dashboard_col_specialized.setCellValueFactory(new PropertyValueFactory("specialized"));
        dashboard_col_status.setCellValueFactory(new PropertyValueFactory("status"));
        dashboard_tableview.setItems(dashboardListData);
    }



    // thêm dữ liệu teacher vào bảng
    public ObservableList<TeacherData> teacherGetData() {
        ObservableList<TeacherData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM teacher ";

        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            TeacherData tData;

            while(result.next()) {
                tData = new TeacherData(
                        result.getInt("id"),
                        result.getString("teacher_id"),
                        result.getString("password"),
                        result.getString("fullname"),
                        result.getString("email"),
                        result.getString("gender"),
                        result.getLong("mobile_number"),
                        result.getString("specialized"),
                        result.getString("address"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getDate("modify_date"),
                        result.getDate("delete_date"),
                        result.getString("status"));

                listData.add(tData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<TeacherData> teacherListData;

    public void teacherDisplayData(){
        teacherListData = teacherGetData();

        teacher_col_ID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        teacher_col_name.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        teacher_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        teacher_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        teacher_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        teacher_col_specialized.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        teacher_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        teacher_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        teacher_tableview.setItems(teacherListData);

    }

    //xử lí edit và delete teacher
    public void teacherActionButton(){
        connect = Database.connectDb();
        teacherListData = teacherGetData();

        Callback<TableColumn<TeacherData, String>, TableCell<TeacherData, String>> cellFactory = (TableColumn<TeacherData, String> param) -> {
            final TableCell<TeacherData, String> cell = new TableCell<TeacherData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 7px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 7px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                TeacherData tData = teacher_tableview.getSelectionModel().getSelectedItem();
                                int num = teacher_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_teacherID = tData.getTeacherID();
                                Data.temp_teacherName = tData.getFullname();
                                Data.temp_teacherGender = tData.getGender();
                                Data.temp_teacherEmail = tData.getEmail();
                                Data.temp_teacherPassword = tData.getPassword();
                                Data.temp_teacherSpecialized = tData.getSpecialized();
                                Data.temp_teacherMobileNumber = String.valueOf(tData.getMobileNumber());
                                Data.temp_teacherAddress = tData.getAddress();
                                Data.temp_teacherStatus = tData.getStatus();



                                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/EditTeacherForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            TeacherData pData = teacher_tableview.getSelectionModel().getSelectedItem();
                            int num = teacher_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE teacher SET delete_date = ? WHERE teacher_id = '"
                                    + pData.getTeacherID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete teacher ID: " + pData.getTeacherID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    teacherGetData();

                                    alert.successMessage("Deleted Successfully!");


                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            teacherDisplayData();
            return cell;
        };

        teacher_col_action.setCellFactory(cellFactory);
        teacher_tableview.setItems(teacherListData);
    }

    //thêm dữ liệu student vào bảng
    public ObservableList<StudentData> studentGetData(){
        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM  student WHERE date_delete IS NULL";

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            StudentData sData;

            while(result.next()){
                sData = new StudentData(result.getInt("id"), result.getInt("student_id")
                        , result.getString("password"), result.getString("full_name")
                        , result.getLong("mobile_number"), result.getString("gender")
                        ,result.getString("address"), result.getString("image")
                        , result.getString("subject"), result.getString("evaluate")
                        , result.getString("lop"), result.getString("teacher")
                        , result.getString("specialized"), result.getDate("date")
                        , result.getDate("date_modify"), result.getDate("date_delete")
                        , result.getString("status"));

                listData.add(sData);

            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listData;
    }
    public ObservableList <StudentData> studentListData;
    public void studentDisplayData(){
        studentListData = studentGetData();

        student_col_ID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        student_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        student_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        student_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        student_col_description.setCellValueFactory(new PropertyValueFactory<>("subject"));
        student_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        student_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        student_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        student_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        student_tableview.setItems(studentListData);
    }

    //xử lí edit và delete student
    public void studentActionButton(){
        connect = Database.connectDb();
        studentListData = studentGetData();

        Callback<TableColumn<StudentData, String>, TableCell<StudentData, String>> cellFactory = (TableColumn<StudentData, String> param) -> {
            final TableCell<StudentData, String> cell = new TableCell<StudentData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 7px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 7px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                StudentData sData = student_tableview.getSelectionModel().getSelectedItem();
                                int num = student_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_studentID = sData.getStudentID();
                                Data.temp_address = sData.getAddress();
                                Data.temp_name = sData.getFullName();
                                Data.temp_gender = sData.getGender();
                                Data.temp_number = sData.getMobileNumber();
                                Data.temp_status = sData.getStatus();



                                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/EditStudentForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            StudentData pData = student_tableview.getSelectionModel().getSelectedItem();
                            int num = student_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE student SET date_delete = ? WHERE student_id = '"
                                    + pData.getStudentID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete student ID: " + pData.getStudentID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    teacherGetData();

                                    alert.successMessage("Deleted Successfully!");


                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            teacherDisplayData();
            return cell;
        };

        student_col_action.setCellFactory(cellFactory);
        student_tableview.setItems(studentListData);
    }

    public ObservableList<AppointmentData> appointmentgetData(){
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE date_delete";

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            if(result.next()){
                aData = new AppointmentData(result.getInt("id"), result.getInt("appointment_id")
                        , result.getString("name"), result.getString("gender")
                        , result.getString("teacher"), result.getString("specialized")
                        , result.getLong("mobile_number"), result.getString("subject")
                        , result.getString("evaluate"), result.getString("lop")
                        , result.getString("address"), result.getDate("date")
                        , result.getDate("date_modify"), result.getDate("date_delete")
                        , result.getString("status"), result.getDate("schedule"));
                listData.add(aData);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentData> appointmentListData;
    public void appointmentDisplayData(){
        appointmentListData = appointmentgetData();

        appointment_ID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointment_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointment_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointment_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointment_description.setCellValueFactory(new PropertyValueFactory<>("subject"));
        appointment_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointment_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointment_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointment_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointment_tableview.setItems(appointmentListData);

    }

    public void appointmentActionButton(){
        connect = Database.connectDb();
        appointmentListData = appointmentgetData();

        Callback<TableColumn<AppointmentData, String>, TableCell<AppointmentData, String>> cellFactory = (TableColumn<AppointmentData, String> param) -> {
            final TableCell<AppointmentData, String> cell = new TableCell<AppointmentData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 7px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 7px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                AppointmentData aData = appointment_tableview.getSelectionModel().getSelectedItem();
                                int num = appointment_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_appID = String.valueOf(aData.getAppointmentID());
                                Data.temp_appName = aData.getName();
                                Data.temp_appGender = aData.getGender();
                                Data.temp_appMobileNumber =String.valueOf(aData.getMobileNumber());
                                Data.temp_appAddress = aData.getAddress();
                                Data.temp_appDescription = aData.getSubject();
                                Data.temp_appDiagnosis = aData.getEvaluate();
                                Data.temp_appTreatment = aData.getLop();
                                Data.temp_appTeacher = aData.getTeacherID();
                                Data.temp_appSpecialized = aData.getSpecialized();
                                Data.temp_appStatus = aData.getStatus();



                                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/EditAppointmentForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            AppointmentData aData = appointment_tableview.getSelectionModel().getSelectedItem();
                            int num = appointment_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE appointment SET date_delete = ? WHERE appointment_id = '"
                                    + aData.getAppointmentID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete appointment ID: " + aData.getAppointmentID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    appointmentgetData();

                                    alert.successMessage("Deleted Successfully!");


                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            teacherDisplayData();
            return cell;
        };

        appointment_action.setCellFactory(cellFactory);
        appointment_tableview.setItems(appointmentListData);
    }

    public  ObservableList<StudentData> paymentGetData(){
        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student WHERE date_delete IS NULL";
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            StudentData pData;
            while (result.next()) {
                pData = new StudentData(result.getInt("id"),
                        result.getInt("student_id"), result.getString("full_name"),
                        result.getString("gender"), result.getString("subject"),
                        result.getString("evaluate"), result.getString("lop"),
                        result.getString("teacher"), result.getString("image"), result.getDate("date"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<StudentData> paymentListData;

    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        payment_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        payment_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        payment_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("evaluate"));
        payment_col_teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        payment_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        payment_tableView.setItems(paymentListData);

    }

    public void paymentSelectItems() {

        StudentData pData = payment_tableView.getSelectionModel().getSelectedItem();
        int num = payment_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        if (pData.getImage() != null) {
            String path = "File:" + pData.getImage();
            image = new Image(path, 166, 103, false, true);
            payment_circle.setFill(new ImagePattern(image));

            Data.temp_path = pData.getImage();
        }

        Data.temp_studentID = pData.getStudentID();
        Data.temp_name = pData.getFullName();
        Data.temp_date = String.valueOf(pData.getDate());

        payment_studentID.setText(String.valueOf(pData.getStudentID()));
        payment_name.setText(pData.getFullName());
        payment_teacher.setText(pData.getTeacher());
        payment_date.setText(String.valueOf(pData.getDate()));

    }

    public void paymentCheckOutBtn() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/CheckOutStudent.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Online Learning System | Check-Out");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileUpdateBtn(){
        if(profile_adminID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem()==null){
            alert.errorMessage("Please fill all blank fields");
        }else{
            if (Data.path == null || "".equals(Data.path)){
                String updateData = "UPDATE admin SET username = ?, email = ?, gender = ? "
                        + "WHERE admin_id = " + Data.admin_id;

                try{
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();
                    profileDisplayInfo();

                    alert.successMessage("Updated Successfully");
                }catch(Exception e){
                    e.printStackTrace();
                }
            } else{
                String updateData = "UPDATE admin SET username =?, email = ?, image=?, gender = ? "
                        + "WHERE admin_id= " + Data.admin_id;

                try{

                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());

                    String path = Data.path;
                    path = path.replace("\\","\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("C:\\Users\\Dieu Linh\\IdeaProjects\\doancoso1\\src\\main\\resources\\com\\example\\doancoso1\\Image"
                            + Data.admin_id +".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    prepare.setString(3, copy.toAbsolutePath().toString());
                    prepare.setString(4, profile_gender.getSelectionModel().getSelectedItem());


                    prepare.executeUpdate();
                    profileDisplayInfo();

                    profileDisplayImage();
                    alert.successMessage("Updated Successfull!");

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void profileDisplayImage(){
        String selectData = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        connect = Database.connectDb();

        String tempPath1 = "";
        String tempPath2 = "";
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                tempPath1 = "File:" + result.getString("image");
                tempPath2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(tempPath1, 1170, 27, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(tempPath2, 128, 114, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void profileInsertImage(){
        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Images", "*jpg", "*jpeg", "*png"));
        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file!=null){
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 114, false, true);
            profile_circle.setFill(new ImagePattern(image));
        }
    }

    public void profileDisplayInfo(){
        String sql = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                profile_adminID.setText(result.getString("admin_id"));
                profile_name.setText(result.getString("username"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));

                profile_label_adminID.setText(result.getString("admin_id"));
                profile_label_name.setText(result.getString("username"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));



            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void profileGenderList(){
        List<String> listS = new ArrayList<>();

        for (String data : Data.gender){
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_gender.setItems(listData);

    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            teacher_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            dashboard_AT();
            dashboardTS();
            dashboard_AS();
            dashboard_TA();
            dashboardGetTeacherDisplayData();
            current_form.setText("Dashboard Form");
        }else if(event.getSource() == teacher_btn){
            dashboard_form.setVisible(false);
            teacher_form.setVisible(true);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            teacherDisplayData();
            teacherActionButton();
            current_form.setText("Teacher's Form");
        }else if(event.getSource() == student_btn){
            dashboard_form.setVisible(false);
            teacher_form.setVisible(false);
            student_form.setVisible(true);
            appointment_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            studentDisplayData();
            studentActionButton();
            current_form.setText("Student's Form");
        }else if(event.getSource() == appointment_btn){
            dashboard_form.setVisible(false);
            teacher_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(true);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            appointmentDisplayData();
            current_form.setText("Appointment's Form");


        }else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            teacher_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
            payment_form.setVisible(true);
            profile_form.setVisible(false);

            paymentDisplayData();
            current_form.setText("Payment Form");

        }else if(event.getSource() == profile_btn){
            dashboard_form.setVisible(false);
            teacher_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(true);

            profileGenderList();
            profileDisplayInfo();
            profileDisplayImage();
            current_form.setText("Profile Form");

        }
    }
    public void displayUsername(){
        String sql = "SELECT * FROM admin WHERE username = '"
                + Data.admin_username + "'";

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                nav_adminID.setText(result.getString("admin_id"));
                String tempUsername = result.getString("username");
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1);
                nav_username.setText(tempUsername);
                top_username.setText(tempUsername);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void logoutBtn(){
        try{
            if(alert.confirmationMessage("Are you sure you want  to logout?")) {

                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Login.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                Logout_btn.getScene().getWindow().hide();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void runTime() {
        new Thread() {
            public void run(){
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while(true){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Platform.runLater(() ->{
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayUsername();

        dashboard_AT();
        dashboardTS();
        dashboard_AS();
        dashboard_TA();
//        dashboardStudentDataChart();
//        dashboardTeacherDataChart();
        dashboardGetTeacherDisplayData();
        // in dữ liệu bảng teacher
        teacherDisplayData();
        teacherActionButton();

        // in dữ liệu bảng student
        studentDisplayData();
        studentActionButton();

        //in dữ liệu bảng appointment
        appointmentDisplayData();
        appointmentActionButton();


        //payment
        paymentDisplayData();

        // profile
        profileGenderList();
        profileDisplayInfo();
        profileDisplayImage();

        paymentDisplayData();

    }
}