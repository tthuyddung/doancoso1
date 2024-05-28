package com.example.doancoso1.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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
import Model.AlertMessage;
import Model.AppointmentData;
import DataConnection.Database;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TeacherMainForm implements Initializable {


    @FXML
    private TextField appointment_ID;

    @FXML
    private TextArea appointment_address;

    @FXML
    private Button appointment_btn;

    @FXML
    private Button Logout_btn;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_ID;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_contact;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_date;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_description;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_name;

    @FXML
    private TableColumn<AppointmentData, String> appointment_col_status;

    @FXML
    private Button appointment_deleteBtn;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private AnchorPane appointment_form;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private TextField appointment_mobile;

    @FXML
    private TextField appointment_name;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private TableView<AppointmentData> appointment_tableview;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private Button appointment_updateBtn;

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
    private TableColumn<AppointmentData, String> dashboard_col_appDate;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_description;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_name;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_status;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<AppointmentData> dashboard_tableview;

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
    private TextArea profile_address;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle profile_circle;

    @FXML
    private TextField profile_email;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_lable_dateCre;

    @FXML
    private Label profile_lable_email;

    @FXML
    private Label profile_lable_name;

    @FXML
    private Label profile_lable_teacherID;

    @FXML
    private TextField profile_mobile;

    @FXML
    private TextField profile_name;

    @FXML
    private ComboBox<String> profile_specializion;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private TextField profile_teacherID;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private TextField student_ID;

    @FXML
    private Button student_SI_addBtn;

    @FXML
    private Label student_SI_address;

    @FXML
    private Label student_SI_gender;

    @FXML
    private Label student_SI_mobile;

    @FXML
    private Label student_SI_name;

    @FXML
    private Button student_SI_recordBtn;

    @FXML
    private Label student_ST_DateCre;

    @FXML
    private Label student_ST_ID;

    @FXML
    private Label student_ST_password;

    @FXML
    private TextArea student_address;

    @FXML
    private Button student_btn;

    @FXML
    private Button student_confirmBtn;

    @FXML
    private AnchorPane student_form;

    @FXML
    private ComboBox<String> student_gender;

    @FXML
    private TextField student_mobile;

    @FXML
    private TextField student_name;

    @FXML
    private TextField student_password;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;
    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private final AlertMessage alert = new AlertMessage();

    public void dashboardDisplayIP(){
        String sql = "SELECT COUNT(id) FROM student WHERE status = 'Inactive' AND teacher = '"
        + Data.teacher_id + "'";

        connect = Database.connectDb();
        int getIP = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                getIP = result.getInt("COUNT(id)");
            }
            dashboard_AT.setText(String.valueOf(getIP));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardDisplayTP(){
        String sql = "SELECT COUNT(id) FROM student WHERE teacher = '"
                + Data.teacher_id + "'";

        connect = Database.connectDb();
        int getTP = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                getTP = result.getInt("COUNT(id)");
            }
            dashboard_TS.setText(String.valueOf(getTP));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardDisplayAP(){
        String sql = "SELECT COUNT(id) FROM student WHERE status = 'Active' AND teacher = '"
                + Data.teacher_id + "'";

        connect = Database.connectDb();
        int getAP = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                getAP = result.getInt("COUNT(id)");
            }
            dashboard_AS.setText(String.valueOf(getAP));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardDisplayTA(){
        String sql = "SELECT COUNT(id) FROM appointment WHERE status = 'Active' AND teacher = '"
                + Data.teacher_id + "'";

        connect = Database.connectDb();
        int getTA = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                getTA = result.getInt("COUNT(id)");
            }
            dashboard_TA.setText(String.valueOf(getTA));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<AppointmentData> dashboardAppointmentTableView(){
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE teacher = '"
                + Data.teacher_id + "'";

        connect = Database.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while(result.next()){
                aData = new AppointmentData(result.getInt("appointment_id")
                , result.getString("name"), result.getString("description")
                , result.getDate("date"), result.getString("status"));

                listData.add(aData);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<AppointmentData> dashboardGetData;
    public void dashboardDisplayData(){
        dashboardGetData = dashboardAppointmentTableView();

        dashboard_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboard_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboard_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboard_col_appDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboard_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboard_tableview.setItems(dashboardGetData);
    }
    public void dashboardNOP(){
        dashboard_char_SD.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM student WHERE teacher = '"
                + Data.teacher_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 8";


        connect = Database.connectDb();

        try{
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_char_SD.getData().add(chart);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void dashboardNOA(){
        dashboard_char_TD.getData().clear();

        String sql ="SELECT date, COUNT(id) FROM appointment WHERE teacher = '"
                                    + Data.teacher_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 7";

        connect = Database.connectDb();

        try{
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_char_TD.getData().add(chart);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void studentConfirmBtn(){
        if(student_ID.getText().isEmpty()
        || student_name.getText().isEmpty()
        || student_gender.getSelectionModel().getSelectedItem() == null
        || student_mobile.getText().isEmpty()
        || student_password.getText().isEmpty()
        || student_address.getText().isEmpty()){
            alert.errorMessage("Please fill all blank fields");
        }else{
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            student_ST_ID.setText(student_ID.getText());
            student_ST_password.setText(student_password.getText());
            student_ST_DateCre.setText(String.valueOf(sqlDate));

            student_SI_name.setText(student_name.getText());
            student_SI_gender.setText(student_gender.getSelectionModel().getSelectedItem());
            student_SI_mobile.setText(student_mobile.getText());
            student_SI_address.setText(student_address.getText());
        }
    }
    public void studentAddBtn(){
        if(student_ST_ID.getText().isEmpty()
        || student_ST_password.getText().isEmpty()
        || student_ST_DateCre.getText().isEmpty()
        || student_SI_name.getText().isEmpty()
        || student_SI_gender.getText().isEmpty()
        || student_SI_mobile.getText().isEmpty()
        || student_SI_address.getText().isEmpty()){
            alert.errorMessage("Something went wrong");
        }else{
            Database.connectDb();
            try{
                String teacherName = "";
                String teacherSpecialized = "";

                String getTeacher = "SELECT * FROM teacher WHERE teacher_id = '"
                        + nav_adminID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(getTeacher);

                if(result.next()){
                    teacherName = result.getString("fullname");
                    teacherSpecialized = result.getString("specialized");
                }
                String checkPatientID = "SELECT * FROM student WHERE student_id = '"
                        + student_ST_ID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientID);

                if(result.next()){
                    alert.errorMessage(student_ST_ID.getText() + " is already exist");
                }else{
                    String insertData = "INSERT INTO student (student_id, password, full_name, mobile_number, "
                            + "address, teacher, specialized, date, "
                            + "status) "
                            + "VALUES(?,?,?,?,?,?,?,?,?)";

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, student_ST_ID.getText());
                    prepare.setString(2, student_ST_password.getText());
                    prepare.setString(3, student_SI_name.getText());
                    prepare.setString(4, student_SI_mobile.getText());
                    prepare.setString(5, student_SI_address.getText());
                    prepare.setString(6, nav_adminID.getText());
                    prepare.setString(7, teacherSpecialized);
                    prepare.setString(8, "" + sqlDate);
                    prepare.setString(9, "Confirm");

                    prepare.executeUpdate();
                    alert.successMessage("Added Successfully");

                    studentClear();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void studentRecordBtn(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/RecordPageForm.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Online Learning System | Record of Student");
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void studentClear(){
        student_ID.clear();
        student_name.clear();
        student_gender.getSelectionModel().clearSelection();
        student_mobile.clear();
        student_password.clear();
        student_address.clear();

        student_ST_ID.setText("");
        student_ST_password.setText("");
        student_ST_DateCre.setText("");
        student_SI_name.setText("");
        student_SI_gender.setText("");
        student_SI_mobile.setText("");
        student_SI_address.setText("");

    }

    private void studentGenderList(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.gender){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        student_gender.setItems(listData);
    }

    public void appointmentInsertBtn(){
        if(appointment_ID.getText().isEmpty()
         || appointment_name.getText().isEmpty()
         || appointment_gender.getSelectionModel().getSelectedItem() == null
         || appointment_mobile.getText().isEmpty()
         || appointment_description.getText().isEmpty()
         || appointment_address.getText().isEmpty()
         || appointment_status.getSelectionModel().getSelectedItem() == null
         || appointment_schedule.getValue() == null){
            alert.errorMessage("Please fill all the fields");
        }else{
            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = "
                    + appointment_ID.getText();
            connect = Database.connectDb();

            try{
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if(result.next()){
                    alert.errorMessage(appointment_ID.getText() + "was already taken");
                }else{
                    String getSpecialized = "";
                    String getTeacherData = "SELECT * FROM teacher WHERE teacher_id = '"
                            + Data.teacher_id + "'";

                    statement =connect.createStatement();
                    result = statement.executeQuery(getTeacherData);

                    if(result.next()){
                        getSpecialized = result.getString("specialized");
                    }
                    String insertData = "INSERT INTO appointment (appointment_id, name, gender"
                            + ", description, diagnosis, treatment, mobile_number"
                            + ", address, date, status, teacher, specialized, schedule) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, appointment_ID.getText());
                    prepare.setString(2, appointment_name.getText());
                    prepare.setString(3, (String)appointment_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, appointment_description.getText());
                    prepare.setString(5, appointment_diagnosis.getText());
                    prepare.setString(6, appointment_treatment.getText());
                    prepare.setString(7, appointment_mobile.getText());
                    prepare.setString(8, appointment_address.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                    prepare.setString(9, "" + sqlDate);
                    prepare.setString(10, (String)appointment_status.getSelectionModel().getSelectedItem());
                    prepare.setString(11, Data.teacher_id);
                    prepare.setString(12, getSpecialized);
                    prepare.setString(13, "" + appointment_schedule.getValue());

                    prepare.executeUpdate();
                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successfully added");

                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void appointmentUpdateBtn(){
        if(appointment_ID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobile.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null){
            alert.errorMessage("Please fill all the fields");
        }else{
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = '"
                    + appointment_name.getText() + "', gender = '"
                    + appointment_gender.getSelectionModel().getSelectedItem() +"', mobile_number = '"
                    + appointment_mobile.getText() + "', description = '"
                    + appointment_description.getText() + "', address = '"
                    + appointment_address.getText() + "', status = '"
                    + appointment_status.getSelectionModel().getSelectedItem() + "', schedule = '"
                    + appointment_schedule.getValue() + "', date_modify = '"
                    + sqlDate + "' WHERE appointment_id = '"
                    + appointment_ID.getText() + "'";

            connect = Database.connectDb();
            try{
                if(alert.confirmationMessage("Are you want to UPDATE appointmentID: "
                 + appointment_ID.getText() + "?")){
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successfully Updated");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void appointmentDeleteBtn(){
        if(appointment_ID.getText().isEmpty()){
            alert.errorMessage("Please select the item first");
        }else{
            String updateData = "UPDATE appointment SET date_delete = ? WHERE appointment_id = '"
                    + appointment_ID.getText() + "'";

            connect = Database.connectDb();
            try{
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if(alert.confirmationMessage("Are you want to DELETE appointmentID: "
                + appointment_ID.getText() + "?")){
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successfully Deleted");
                }else{
                    alert.errorMessage("Cancelled");
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void appointmentClearBtn(){
        appointment_ID.clear();
        appointment_name.clear();
        appointment_gender.getSelectionModel().clearSelection();
        appointment_mobile.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_status.setValue(null);
    }

    private Integer appointmentID;
    public void appointmentGetAppointmentId(){
        String sql = "SELECT MAX(appointment_id) FROM appointment";
        connect = Database.connectDb();

        int tempAppID = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                tempAppID = result.getInt("MAX(appointment_id)");
            }
            if(tempAppID == 0){
                tempAppID += 1;
            }else{
                tempAppID += 1;
            }
            appointmentID = tempAppID;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void appointmentAppointmentID(){
        appointmentGetAppointmentId();

        appointment_ID.setText("" + appointmentID);
        appointment_ID.setDisable(true);
    }

    public void appointmentGenderList(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.gender){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        appointment_gender.setItems(listData);
    }

    public void appointmentStatusList(){
        List<String> listS = new ArrayList<>();

        for(String data : Data.status){
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        appointment_status.setItems(listData);
    }
    public ObservableList<AppointmentData> appointmentGetData() {
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL";
        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData appData;

            while(result.next()){
                appData= new AppointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("description"),
                        result.getString("diagnosis"), result.getString("treatment"),
                        result.getString("address"),
                        result.getDate("date"), result.getDate("date_modify"),
                        result.getDate("date_delete"), result.getString("status"), result.getDate("schedule"));
                listData.add(appData);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    public ObservableList<AppointmentData> appointmentListData;
    public void appointmentShowData(){
        appointmentListData = appointmentGetData();

        appointment_col_ID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointment_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointment_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointment_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointment_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointment_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointment_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointment_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointment_tableview.setItems(appointmentListData);

    }
    public void appointmentSelect(){
        AppointmentData appData = appointment_tableview.getSelectionModel().getSelectedItem();
        int num = appointment_tableview.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1) return;

        appointment_ID.setText("" + appData.getAppointmentID());
        appointment_name.setText(appData.getName());
        appointment_gender.getSelectionModel().select(appData.getGender());
        appointment_mobile.setText("" + appData.getMobileNumber());
        appointment_description.setText(appData.getDescription());
        appointment_diagnosis.setText(appData.getDiagnosis());
        appointment_treatment.setText(appData.getTreatment());
        appointment_address.setText(appData.getAddress());
        appointment_status.getSelectionModel().select(appData.getStatus());
    }
    public void profileUpdateBtn() {

        connect = Database.connectDb();

        if (profile_teacherID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobile.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specializion.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            // CHECK IF THE PATH IS NULL
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE teacher SET fullname = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE teacher_id = '"
                        + Data.teacher_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobile.getText());
                    prepare.setString(5, profile_address.getText());
                    prepare.setString(6, profile_specializion.getSelectionModel().getSelectedItem());
                    prepare.setString(7, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE teacher SET fullname = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, image = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE teacher_id = '"
                        + Data.teacher_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobile.getText());
                    prepare.setString(5, profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    // LINK YOUR DIRECTORY FOLDER
                    Path copy = Paths.get("C:\\Users\\Dieu Linh\\IdeaProjects\\doancoso1\\src\\main\\resources\\com\\example\\doancoso1\\Image"
                            + Data.teacher_id + ".jpg");

                    try {
                        // TO PUT THE IMAGE FILE TO YOUR DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7, profile_specializion.getSelectionModel().getSelectedItem());
                    prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_circle.setFill(new ImagePattern(image));
        }

    }

    public void profileLabel(){
        String selectData = "SELECT *  FROM teacher WHERE teacher_id = '"
                + Data.teacher_id + "'";

        connect = Database.connectDb();
        try{
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if(result.next()){
                profile_lable_teacherID.setText(result.getString("teacher_id"));
                profile_lable_name.setText(result.getString("fullname"));
                profile_lable_email.setText(result.getString("email"));
                profile_lable_dateCre.setText(result.getString("date"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void profile(){
        String selectData = "SELECT * FROM teacher WHERE teacher_id = '"
                 + Data.teacher_id + "'";

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if(result.next()){
                profile_teacherID.setText(result.getString("teacher_id"));
                profile_name.setText(result.getString("fullname"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobile.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specializion.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void profileDisplayImages() {

        String selectData = "SELECT * FROM teacher WHERE teacher_id = '"
                + Data.teacher_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_path1 = "File:" + result.getString("image");
                temp_path2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(temp_path1, 1164, 18, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 137, 104, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void profileGenderList(){
        List<String> listS = new ArrayList<>();

        for(String data : Data.gender){
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableList(listS);
        profile_gender.setItems(listData);
    }

    private String[] specializion = {"Math", "English", "Literature", "History", "Geography", "Physics", "Chemistry"};

    public void profileSpecialized(){
        List<String> listS = new ArrayList<>();

        for(String data : specializion){
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableList(listS);
        profile_specializion.setItems(listData);
    }
    public void profileStatusList(){
        List<String> listS = new ArrayList<>();

        for(String data : Data.status){
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminID(){
        String name = Data.teacher_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.teacher_id);
        top_username.setText(name);
    }


    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
            profile_form.setVisible(false);
        }else if(event.getSource() == student_btn){
            dashboard_form.setVisible(false);
            student_form.setVisible(true);
            appointment_form.setVisible(false);
            profile_form.setVisible(false);
        }else if(event.getSource() == appointment_btn){
            dashboard_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(true);
            profile_form.setVisible(false);
        }else if(event.getSource() == profile_btn){
            dashboard_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
            profile_form.setVisible(true);
        }
    }
    public void logoutBtn(){
        try{
            if(alert.confirmationMessage("Are you sure you want  to logout?")) {
                Data.teacher_id = "";
                Data.teacher_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Teacher.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                //TO HIDE YOUR MAIN FORM
                Logout_btn.getScene().getWindow().hide();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void runTime(){
        new Thread(){
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
        displayAdminID();
        runTime();

        dashboardDisplayAP();
        dashboardDisplayTA();
        dashboardDisplayIP();
        dashboardDisplayTP();

        appointmentShowData();
        appointmentGenderList();
        appointmentStatusList();
        appointmentAppointmentID();
        dashboardDisplayData();
//        dashboardNOP();
//        dashboardNOA();

//        displayAdminID();

        studentGenderList();

        profileLabel();
        profile();
        profileSpecialized();
        profileStatusList();
        profileGenderList();
        profileDisplayImages();
    }
}