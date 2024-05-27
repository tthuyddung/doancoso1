package Model;

import java.util.Date;

public class TeacherData {
    public static TeacherData dData;
    private Integer id;
    private String teacherID;
    private String password;
    private String fullname;
    private String email;
    private String gender;
    private Long mobileNumber;
    private String specialized;
    private String address;
    private String image;
    private Date date;
    private Date dateDelete;
    private Date dateModify;
    private String status;


    public TeacherData(Integer id, String teacherID, String password, String fullname, String email
            , String gender, Long mobileNumber, String specialized, String address, String image, Date date, Date dateDelete, Date dateModify, String status){
        this.id = id;
        this.teacherID = teacherID;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.specialized = specialized;
        this.address = address;
        this.image = image;
        this.date = date;
        this.dateDelete = dateDelete;
        this.dateModify = dateModify;
        this.status = status;

    }

    public TeacherData(String teacherID, String fullname, String specialized, String status) {
        this.teacherID = teacherID;
        this.fullname = fullname;
        this.specialized = specialized;
        this.status = status;
    }

    public TeacherData(Integer id, String teacherID, String fullname, String specialized, String email, String image) {
        this.id = id;
        this.teacherID = teacherID;
        this.fullname = fullname;
        this.specialized = specialized;
        this.email = email;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }
    public String getTeacherID() {
        return teacherID;
    }
    public String getPassword() {
        return password;
    }
    public String getFullname() {
        return fullname;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return gender;
    }
    public Long getMobileNumber() {
        return mobileNumber;
    }
    public String getSpecialized() {
        return specialized;
    }
    public String getAddress() {
        return address;
    }
    public String getImage() {
        return image;
    }
    public Date getDate() {
        return date;
    }
    public Date getDateDelete() {
        return dateDelete;
    }
    public Date getDateModify() {
        return dateModify;
    }
    public String getStatus() {return status;}


}
