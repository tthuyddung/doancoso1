package com.example.doancoso1.Controller;

import DataConnection.Database;
import Model.AlertMessage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.collections.ObservableList;

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
import Model.TeacherData;
import Model.StudentData;
import Model.AppointmentData;
import javafx.scene.image.Image;


public class StudentMainForm implements Initializable {
    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Button Logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button teacher_btn;

    @FXML
    private Button appointment_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private TableView<StudentData> home_student_tableView;

    @FXML
    private TableColumn<StudentData, String> home_student_col_description;

    @FXML
    private TableColumn<StudentData, String> home_student_col_diagnosis;

    @FXML
    private TableColumn<StudentData, String> home_student_col_treatment;

    @FXML
    private TableColumn<StudentData, String> home_student_col_datein;

    @FXML
    private TableColumn<StudentData, String> home_student_col_dateDischarge;

    @FXML
    private Circle home_teacher_circle;

    @FXML
    private Label home_teacher_name;

    @FXML
    private Label home_teacher_specialization;

    @FXML
    private Label home_teacher_email;

    @FXML
    private Label home_teacher_mobileNumber;

    @FXML
    private TableView<AppointmentData> home_appointment_tableView;

    @FXML
    private TableColumn<AppointmentData, String> home_appointment_col_appID;

    @FXML
    private TableColumn<AppointmentData, String> home_appointment_col_description;

    @FXML
    private TableColumn<AppointmentData, String> home_appointment_col_diagnosis;

    @FXML
    private TableColumn<AppointmentData, String> home_appointment_col_treatment;

    @FXML
    private TableColumn<AppointmentData, String> home_appointment_col_teacher;

    @FXML
    private TableColumn<AppointmentData, String> home_appointment_col_schedule;

    @FXML
    private AnchorPane teacher_form;

    @FXML
    private GridPane teacher_gridPane;

    @FXML
    private ScrollBar teacher_scScrollBar;


    @FXML
    private AnchorPane appointment_form;

    @FXML
    private Label appointment_ad_name;

    @FXML
    private Label appointment_ad_gender;

    @FXML
    private Label appointment_ad_address;

    @FXML
    private Label appointment_ad_description;

    @FXML
    private Label appointment_ad_teacherName;

    @FXML
    private Label appointment_ad_specialization;

    @FXML
    private Label appointment_ad_schedule;

    @FXML
    private Label appointment_ad_mobileNumber;

    @FXML
    private Button appointment_ad_bookBtn;

    @FXML
    private Button appointment_d_confirmBtn;

    @FXML
    private Button appointment_d_clearBtn;

    @FXML
    private TextArea appointment_d_description;

    @FXML
    private ComboBox<?> appointment_d_teacher;

    @FXML
    private DatePicker appointment_d_schedule;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_studentID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_mobileNumber;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_studentID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private TextField profile_password;

    @FXML
    private TextField profile_address;

    private AlertMessage alert = new AlertMessage();
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public StudentMainForm(){

    }

    public ObservableList<StudentData> homeStudentGetData() {
        ObservableList<StudentData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM student WHERE date_delete IS NULL AND student_id = " + Data.student_id;
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()) {
                StudentData pData = new StudentData(
                        result.getInt("id"),
                        result.getInt("student_id"),
                        result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"),
                        result.getDate("date"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
    private ObservableList<StudentData> homeStudentListData;
    public void homeStudentDisplayData() {
        homeStudentListData = homeStudentGetData();
        home_student_col_description.setCellValueFactory(new PropertyValueFactory("description"));
        home_student_col_diagnosis.setCellValueFactory(new PropertyValueFactory("diagnosis"));
        home_student_col_treatment.setCellValueFactory(new PropertyValueFactory("treatment"));
        home_student_col_datein.setCellValueFactory(new PropertyValueFactory("date"));
        home_student_tableView.setItems(homeStudentListData);
    }

    public ObservableList<AppointmentData> homeAppointmentGetData() {
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL AND student_id = " + Data.student_id;
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()) {
                AppointmentData aData = new AppointmentData(result.getInt("appointment_id"), result.getString("description"), result.getString("diagnosis"), result.getString("treatment"), result.getString("teacher"), result.getDate("schedule"));
                listData.add(aData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<AppointmentData> homeAppointmentListData;
    public void homeAppointmentDisplayData() {
//        AppointmentData(Integer appointmentID, String description, String diagnosis, String treatment, String teacherID, java.sql.Date schedule)
        homeAppointmentListData = homeAppointmentGetData();
        home_appointment_col_appID.setCellValueFactory(new PropertyValueFactory("appointmentID"));
        home_appointment_col_description.setCellValueFactory(new PropertyValueFactory("description"));
        home_appointment_col_diagnosis.setCellValueFactory(new PropertyValueFactory("diagnosis"));
        home_appointment_col_treatment.setCellValueFactory(new PropertyValueFactory("treatment"));
        home_appointment_col_teacher.setCellValueFactory(new PropertyValueFactory("teacherID"));
        home_appointment_col_schedule.setCellValueFactory(new PropertyValueFactory("schedule"));
        home_appointment_tableView.setItems(homeAppointmentListData);
    }

    // chưa in ra đc thông tin
    public void homeTeacherInfoDisplay() {
        String sql = "SELECT * FROM student WHERE student_id = "
                + Data.student_id;
        connect = Database.connectDb();
        String tempTeacherID = "";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempTeacherID = result.getString(1);
            }

            String checkTeacher = "SELECT * FROM teacher WHERE teacher_id = '" + tempTeacherID + "'";
            statement = connect.createStatement();
            result = statement.executeQuery(checkTeacher);
            if (result.next()) {
                home_teacher_name.setText(result.getString(4));
                home_teacher_specialization.setText(result.getString(8));
                home_teacher_email.setText(result.getString(5));
                home_teacher_mobileNumber.setText(result.getString(7));
                String path = result.getString(10);
                if (path != null) {
                    path = path.replace("\\", "\\\\");
                    image = new Image("File:" + path, 158, 97, false, true);
                    home_teacher_circle.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<TeacherData> teacherGetData() {
        String sql = "SELECT * FROM teacher WHERE status = 'Active'";
        connect = Database.connectDb();
        ObservableList<TeacherData> listData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()) {
                TeacherData dData = new TeacherData(result.getInt("id"), result.getString("teacher_id"), result.getString("fullname"), result.getString("specialized"), result.getString("email"), result.getString("image"));
                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
    private ObservableList<TeacherData> teacherList = FXCollections.observableArrayList();


    public void teacherShowCard() {
        teacherList.clear();
        teacherList.addAll(teacherGetData());
        teacher_gridPane.getChildren().clear();
        teacher_gridPane.getColumnConstraints().clear();
        teacher_gridPane.getRowConstraints().clear();
        int row = 0;
        int column = 0;

        for(int q = 0; q < teacherList.size(); ++q) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/doancoso1/View/TeacherCard.fxml"));
                StackPane stack = (StackPane)loader.load();
                TeacherCard dController = (TeacherCard)loader.getController();
                dController.setData((TeacherData)teacherList.get(q));
                if (column == 3) {
                    column = 0;
                    ++row;
                }

                teacher_gridPane.add(stack, column++, row);
                GridPane.setMargin(stack, new Insets(15.0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void appointmentAppointmentInfoDisplay() {
        String sql = "SELECT * FROM student WHERE student_id = " + Data.student_id;
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                appointment_ad_name.setText(result.getString("full_name"));
                appointment_ad_mobileNumber.setText(result.getString("mobile_number"));
                appointment_ad_gender.setText(result.getString("gender"));
                appointment_ad_address.setText(result.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void appointmentConfirmBtn() {
        if (!appointment_d_description.getText().isEmpty() && appointment_d_schedule.getValue() != null && !appointment_d_teacher.getSelectionModel().isEmpty()) {
            appointment_ad_description.setText(appointment_d_description.getText());
            appointment_ad_name.setText((String)appointment_d_teacher.getSelectionModel().getSelectedItem());
            String sql = "SELECT * FROM teacher WHERE teacher_id = '" + (String)appointment_d_teacher.getSelectionModel().getSelectedItem() + "'";
            connect = Database.connectDb();
            String tempSpecialized = "";

            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                if (result.next()) {
                    tempSpecialized = result.getString("specialized");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            appointment_ad_specialization.setText(tempSpecialized);
            appointment_ad_schedule.setText(String.valueOf(appointment_d_schedule.getValue()));
        } else {
            alert.errorMessage("Please fill all blank fields");
        }

    }

    public void appointmentTeacher() {
        String sql = "SELECT * FROM teacher WHERE delete_date IS NULL";
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();

            while(result.next()) {
                listData.add(result.getString("teacher_id"));
            }

            appointment_d_teacher.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void appointmentClearBtn() {
        appointment_d_teacher.getSelectionModel().clearSelection();
        appointment_d_description.clear();
        appointment_d_schedule.setValue(null);
        appointment_ad_description.setText("");
        appointment_ad_teacherName.setText("");
        appointment_ad_specialization.setText("");
        appointment_ad_schedule.setText("");
    }

    public void appointmentBookBtn() {
        connect = Database.connectDb();
        if (!appointment_ad_description.getText().isEmpty() && !appointment_d_teacher.getSelectionModel().isEmpty() && !appointment_ad_specialization.getText().isEmpty() && !appointment_ad_schedule.getText().isEmpty()) {
            String selectData = "SELECT MAX(appointment_id) FROM appointment";
            int tempAppID = 0;

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);
                if (result.next()) {
                    tempAppID = result.getInt("MAX(appointment_id)") + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String insertData = "INSERT INTO appointment (appointment_id, student_id, name, gender, description, mobile_number, address, date, teacher, specialized, schedule, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            Date date = new Date();
            new java.sql.Date(date.getTime());

            try {
                if (alert.confirmationMessage("Are you sure you want to book?")) {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(tempAppID));
                    prepare.setString(2, String.valueOf(Data.student_id));
                    prepare.setString(3, appointment_ad_name.getText());
                    prepare.setString(4, appointment_ad_gender.getText());
                    prepare.setString(5, appointment_ad_description.getText());
                    prepare.setString(6, appointment_ad_mobileNumber.getText());
                    prepare.setString(7, appointment_ad_address.getText());
                    prepare.setString(8, String.valueOf(appointment_d_schedule.getValue()));
                    prepare.setString(9, (String)appointment_d_teacher.getSelectionModel().getSelectedItem());
                    prepare.setString(10, appointment_ad_specialization.getText());
                    prepare.setString(11, appointment_ad_schedule.getText());
                    prepare.setString(12, "Active");
                    prepare.executeUpdate();
                    alert.successMessage("Successful !");
                    appointmentClearBtn();
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.errorMessage("Invalid");
        }

    }

    public void profileDisplayFields() {
        String sql = "SELECT * FROM student WHERE student_id = " + Data.student_id;
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                profile_studentID.setText(result.getString("student_id"));
                profile_name.setText(result.getString("full_name"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_password.setText(result.getString("password"));
                profile_address.setText(result.getString("address"));
                if (result.getString("image") != null) {
                    String imagePath = "File:" + result.getString("image");
                    image = new Image(imagePath, 128, 114, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }

                profileDisplayLabels();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileDisplayLabels() {
        String sql = "SELECT * FROM student WHERE student_id = " + Data.student_id;
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                profile_label_studentID.setText(result.getString("student_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_mobileNumber.setText(result.getString("mobile_number"));
                profile_label_dateCreated.setText(result.getString("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void profileDisplayImages() {
        String sql = "SELECT * FROM student WHERE student_id = " + Data.student_id;
        connect = Database.connectDb();
        String tempPath1 = "";
        String tempPath2 = "";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempPath1 = "File:" + result.getString("image");
                tempPath2 = "File:" + result.getString("image");
                if (result.getString("image") != null || "".equals(result.getString("image"))) {
                    image = new Image(tempPath1, 128, 114, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                    image = new Image(tempPath2, 1180, 38, false, true);
                    top_profile.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileUpdateBtn() {
        connect = Database.connectDb();
        if (!profile_studentID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_mobileNumber.getText().isEmpty()
                || profile_gender.getSelectionModel().isEmpty()
                || profile_password.getText().isEmpty()
                || profile_address.getText().isEmpty()) {
            if (alert.confirmationMessage("Are you sure you want to Update your Profile?")) {
                String updateData;
                if (Data.path != null && !"".equals(Data.path)) {
                    updateData = Data.path;
                    updateData = updateData.replace("\\", "\\\\");
                    Path transfer = Paths.get(updateData);
                    Path copy = Paths.get("C:\\Users\\Dieu Linh\\IdeaProjects\\doancoso1\\src\\main\\resources\\com\\example\\doancoso1\\Image" + Data.student_id + ".jpg");
                    String copyPath = copy.toAbsolutePath().toString();
                    copyPath = copyPath.replace("\\", "\\\\");
                     updateData = "UPDATE student SET full_name = '"
                             + profile_name.getText() + "', mobile_number = '"
                             + profile_mobileNumber.getText() + "', gender = '"
                             + (String)profile_gender.getSelectionModel().getSelectedItem() + "', password = '"
                             + profile_password.getText() + "', address = '"
                             + profile_address.getText() + "', image = '" + copyPath + "' WHERE student_id = "
                             + Data.student_id;

                    try {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                        alert.successMessage("Updated successfully!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    updateData = "UPDATE student SET full_name = '" + profile_name.getText() + "', mobile_number = '" + profile_mobileNumber.getText() + "', gender = '" + (String)profile_gender.getSelectionModel().getSelectedItem() + "', password = '" + profile_password.getText() + "', address = '" + profile_address.getText() + "' WHERE student_id = " + Data.student_id;

                    try {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        alert.successMessage("Updated Successfully");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                alert.errorMessage("Cancelled.");
            }
        } else {
            alert.errorMessage("Please fill all blank fields");
        }

        profileDisplayImages();
    }
    public void profileImportBtn() {
        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", new String[]{"*jpg", "*jpeg", "*png"}));
        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());
        if (file != null) {
            Data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 128, 114, false, true);
            profile_circle.setFill(new ImagePattern(image));
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

    @FXML
    void logoutBtn(ActionEvent event) {
        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Parent root = (Parent) FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Student.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            home_form.setVisible(true);
            teacher_form.setVisible(false);
            appointment_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == teacher_btn) {
            home_form.setVisible(false);
            teacher_form.setVisible(true);
            appointment_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == this.appointment_btn) {
            home_form.setVisible(false);
            teacher_form.setVisible(false);
            appointment_form.setVisible(true);
            profile_form.setVisible(false);
        } else if (event.getSource() == profile_btn) {
            home_form.setVisible(false);
            teacher_form.setVisible(false);
            appointment_form.setVisible(false);
            profile_form.setVisible(true);
        }
    }

    public void displayStudentID() {

        nav_adminID.setText(String.valueOf(Data.student_id));
    }

    public void displayStudent() {
        String sql = "SELECT * FROM student WHERE student_id = " + Data.student_id;

        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                top_username.setText(result.getString("full_name"));
            }
        } catch (Exception e) {
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
        runTime();
        displayStudentID();
        displayStudent();
        homeStudentDisplayData();
        homeAppointmentDisplayData();
        homeTeacherInfoDisplay();
        teacherShowCard();
        appointmentAppointmentInfoDisplay();
        appointmentTeacher();
        profileDisplayFields();
        profileDisplayLabels();
        profileGenderList();
        profileDisplayImages();

    }
}
