package com.example.doancoso1.Controller;

import DataConnection.Database;
import Model.AlertMessage;
import Model.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.ResourceBundle;

public class CheckOutStudent implements Initializable {
    @FXML
    private Label checkout_studentID;

    @FXML
    private Label checkout_studentName;

    @FXML
    private Label checkout_Teacher;

    @FXML
    private DatePicker checkout_Date;

    @FXML
    private ImageView checkout_imageView;

    @FXML
    private DatePicker checkout_checkout;

    @FXML
    private Label checkout_totalDay;

    @FXML
    private Label checkout_totalPrice;

    @FXML
    private Button checkout_payBtn;
    private Image image;
    private AlertMessage alert = new AlertMessage();
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public CheckOutStudent() {
    }

//     tính tổng số ngày giữa hai ngày bằng cách sử dụng ChronoUnit.DAYS.between.
//    Sau đó, nó tính toán tổng giá dựa trên tỷ giá cố định hàng ngày.
//    Cuối cùng, nó cập nhật nhãn để hiển thị số ngày đã tính và tổng giá.
    public void countBtn() {
        long countDays = 0L;
        if (checkout_Date.getValue() == null && checkout_checkout.getValue() == null) {
            alert.errorMessage("Something went wrong.");
        } else {
            countDays = ChronoUnit.DAYS.between((Temporal)checkout_Date.getValue(), (Temporal)checkout_checkout.getValue());
        }

        double price = 20.5;
        double totalprice = price * (double)countDays;
        checkout_totalDay.setText(String.valueOf(countDays));
        checkout_totalPrice.setText(String.valueOf(totalprice));
    }


    public void payBtn() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        if (checkout_checkout.getValue() != null && !checkout_totalDay.getText().isEmpty() && !checkout_totalPrice.getText().isEmpty()) {
            if (alert.confirmationMessage("Are you sure you want to pay?")) {
                String sql = "INSERT INTO payment (student_id, teacher, total_days, total_price, date, date_checkout, date_pay) VALUES(?,?,?,?,?,?,?)";
                connect = Database.connectDb();

                try {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, checkout_studentID.getText());
                    prepare.setString(2, checkout_Teacher.getText());
                    prepare.setString(3, checkout_totalDay.getText());
                    prepare.setString(4, checkout_totalPrice.getText());
                    prepare.setString(5, String.valueOf(checkout_Date.getValue()));
                    prepare.setString(6, String.valueOf(checkout_checkout.getValue()));
                    prepare.setString(7, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    String updateData = "UPDATE student SET status_pay = ? WHERE student_id = " + checkout_studentID.getText();
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, "Paid");
                    prepare.executeUpdate();
                    alert.successMessage("Successful!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled.");
            }
        } else {
            alert.errorMessage("Invalid");
        }

    }
    public void displayFields(){
        checkout_studentID.setText(String.valueOf(Data.temp_studentID));
        checkout_studentName.setText(Data.temp_name);
        checkout_Teacher.setText(Data.temp_teacherID);
        checkout_Date.setValue(LocalDate.parse(Data.temp_date));
        checkout_Date.setDisable(true);

        image = new Image("File:" + Data.temp_path, 141, 148, false, true);
        checkout_imageView.setImage(image);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayFields();
    }
}