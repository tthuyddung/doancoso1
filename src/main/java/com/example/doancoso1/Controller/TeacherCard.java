package com.example.doancoso1.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import Model.TeacherData;


public class TeacherCard implements Initializable {
    @FXML
    private Circle teacher_circle;

    @FXML
    private Label teacher_teacherID;

    @FXML
    private Label teacher_fullname;

    @FXML
    private Label teacher_specialization;

    @FXML
    private Label teacher_email;
    private Image image;

    public TeacherCard() {
    }

    public void setData(TeacherData dData) {
        if (dData.getImage() != null) {
            image = new Image("File:" + dData.getImage(), 41, 41, false, true);
            teacher_circle.setFill(new ImagePattern(image));
        }

        teacher_teacherID.setText(dData.getTeacherID());
        teacher_fullname.setText(dData.getFullname());
        teacher_specialization.setText(dData.getSpecialized());
        teacher_email.setText(dData.getEmail());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
