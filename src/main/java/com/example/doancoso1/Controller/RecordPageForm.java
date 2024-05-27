package com.example.doancoso1.Controller;

import DataConnection.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Model.StudentData;
import Model.Data;
import Model.AlertMessage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class RecordPageForm implements Initializable {
    @FXML
    private TableColumn<StudentData, String> record_col_ID;

    @FXML
    private TableColumn<StudentData, String> record_col_action;

    @FXML
    private TableColumn<StudentData, String> record_col_address;

    @FXML
    private TableColumn<StudentData, String> record_col_dateCre;

    @FXML
    private TableColumn<StudentData, String> record_col_dateDele;

    @FXML
    private TableColumn<StudentData, String> record_col_dateModi;

    @FXML
    private TableColumn<StudentData, String> record_col_gender;

    @FXML
    private TableColumn<StudentData, String> record_col_mobile;

    @FXML
    private TableColumn<StudentData, String> record_col_name;

    @FXML
    private AnchorPane record_form;

    @FXML
    private TextField record_search;

    @FXML
    private TableView<StudentData> record_tableview;

    AlertMessage alert = new AlertMessage();

    private Connection connect;
    private Statement statement;
    private ResultSet result;
    private PreparedStatement prepare;

    public ObservableList<StudentData> getStudentRecordData(){
        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String selectData = "SELECT * FROM student WHERE date_delete IS NULL AND teacher = '"
                + Data.teacher_id + "'";
        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            StudentData sData;

            while(result.next()){
                sData = new StudentData(result.getInt("id"), result.getInt("student_id"),
                        result.getString("full_name"),result.getString("gender"), result.getLong("mobile_number"),
                        result.getString("address"),result.getString("status"), result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"));
                listData.add(sData);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<StudentData> studentRecordData;

    public void displayStudenData(){
        studentRecordData = getStudentRecordData();

        record_col_ID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        record_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        record_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        record_col_mobile.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        record_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        record_col_dateCre.setCellValueFactory(new PropertyValueFactory<>("date"));
        record_col_dateModi.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        record_col_dateDele.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));

        record_tableview.setItems(studentRecordData);
    }

    public void actionButtons() {

        connect = Database.connectDb();
        studentRecordData = getStudentRecordData();

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

                                StudentData pData = record_tableview.getSelectionModel().getSelectedItem();
                                int num = record_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_studentID = pData.getStudentID();
                                Data.temp_name = pData.getFullName();
                                Data.temp_gender = pData.getGender();
                                Data.temp_number = pData.getMobileNumber();
                                Data.temp_address = pData.getAddress();
                                Data.temp_status = pData.getStatus();

                                Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/EditStudentForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            StudentData pData = record_tableview.getSelectionModel().getSelectedItem();
                            int num = record_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE student SET date_delete = ? WHERE student_id = "
                                    + pData.getStudentID();

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete student ID: " + pData.getStudentID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    alert.successMessage("Deleted Successfully!");

                                    displayStudenData();
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
            return cell;
        };

        record_col_action.setCellFactory(cellFactory);
        record_tableview.setItems(studentRecordData);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStudenData();
        actionButtons();
    }
}
