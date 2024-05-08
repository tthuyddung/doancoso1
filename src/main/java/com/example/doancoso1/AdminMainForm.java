package com.example.doancoso1;

import Database.Database;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import Module.Data;

public class AdminMainForm implements Initializable {

    @FXML
    private TableColumn<?, ?> appointment_ID;

    @FXML
    private TableColumn<?, ?> appointment_action;

    @FXML
    private Button appointment_btn;

    @FXML
    private TableColumn<?, ?> appointment_contact;

    @FXML
    private TableColumn<?, ?> appointment_date;

    @FXML
    private TableColumn<?, ?> appointment_dateDelete;

    @FXML
    private TableColumn<?, ?> appointment_dateModify;

    @FXML
    private TableColumn<?, ?> appointment_description;

    @FXML
    private AnchorPane appointment_form;

    @FXML
    private TableColumn<?, ?> appointment_gender;

    @FXML
    private TableColumn<?, ?> appointment_name;

    @FXML
    private TableColumn<?, ?> appointment_status;

    @FXML
    private TableView<?> appointment_tableview;

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
    private TableColumn<?, ?> dashboard_col_name;

    @FXML
    private TableColumn<?, ?> dashboard_col_specialized;

    @FXML
    private TableColumn<?, ?> dashboard_col_status;

    @FXML
    private TableColumn<?, ?> dashboard_col_teacherID;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<?> dashboard_tableview;

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
    private TableColumn<?, ?> student_col_ID;

    @FXML
    private TableColumn<?, ?> student_col_action;

    @FXML
    private TableColumn<?, ?> student_col_contact;

    @FXML
    private TableColumn<?, ?> student_col_date;

    @FXML
    private TableColumn<?, ?> student_col_dateDelete;

    @FXML
    private TableColumn<?, ?> student_col_dateModify;

    @FXML
    private TableColumn<?, ?> student_col_description;

    @FXML
    private TableColumn<?, ?> student_col_gender;

    @FXML
    private TableColumn<?, ?> student_col_name;

    @FXML
    private TableColumn<?, ?> student_col_status;

    @FXML
    private AnchorPane student_form;

    @FXML
    private TableView<?> student_tableview;

    @FXML
    private Button teacher_btn;

    @FXML
    private TableColumn<?, ?> teacher_col_ID;

    @FXML
    private TableColumn<?, ?> teacher_col_action;

    @FXML
    private TableColumn<?, ?> teacher_col_address;

    @FXML
    private TableColumn<?, ?> teacher_col_contact;

    @FXML
    private TableColumn<?, ?> teacher_col_email;

    @FXML
    private TableColumn<?, ?> teacher_col_gender;

    @FXML
    private TableColumn<?, ?> teacher_col_name;

    @FXML
    private TableColumn<?, ?> teacher_col_specialized;

    @FXML
    private TableColumn<?, ?> teacher_col_status;

    @FXML
    private AnchorPane teacher_form;

    @FXML
    private TableView<?> teacher_tableview;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            teacher_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
        }else if(event.getSource() == teacher_btn){
            dashboard_form.setVisible(false);
            teacher_form.setVisible(true);
            student_form.setVisible(false);
            appointment_form.setVisible(false);
        }else if(event.getSource() == student_btn){
            dashboard_form.setVisible(false);
            teacher_form.setVisible(false);
            student_form.setVisible(true);
            appointment_form.setVisible(false);
        }else if(event.getSource() == appointment_btn){
            dashboard_form.setVisible(false);
            teacher_form.setVisible(false);
            student_form.setVisible(false);
            appointment_form.setVisible(true);
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
    }
}