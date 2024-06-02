package Model;

import java.util.Date;

public class StudentData {
    private Integer id;
    private Integer studentID;
    private String password;
    private String fullName;
    private Long mobileNumber;
    private String address;
    private String image;
    private String subject;
    private String evaluate;
    private String lop;
    private String teacher;
    private String specialized;
    private String gender;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;

    public StudentData(Integer id, Integer studentID, String password, String fullName,
                       Long mobileNumber, String gender, String address, String image, String subject,
                       String evaluate, String lop, String teacher, String specialized,
                       Date date, Date dateModify, Date dateDelete, String status){
        this.id = id;
        this.studentID = studentID;
        this.password = password;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.address = address;
        this.image = image;
        this.subject = subject;
        this.evaluate = evaluate;
        this.lop = lop;
        this.teacher = teacher;
        this.specialized = specialized;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
    }
    public StudentData(Integer id, Integer studentID, String fullName,String gender, Long mobileNumber, String address, String status, Date date,
                       Date dateModify, Date dateDelete){
        this.id = id;
        this.studentID = studentID;
        this.fullName = fullName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.status = status;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;

    }

    public StudentData(Integer id, Integer studentID, String fullName, String gender
            , String subject, String evaluate, String lop
            , String teacher, String image, Date date){
        this.id = id;
        this.studentID = studentID;
        this.fullName = fullName;
        this.gender = gender;
        this.subject = subject;
        this.evaluate = evaluate;
        this.lop = lop;
        this.teacher = teacher;
        this.image = image;
        this.date = date;
    }
    public StudentData(Integer id, Integer studentID, String subject, String evaluate, String lop, java.sql.Date date) {
        this.id = id;
        this.studentID = studentID;
        this.subject = subject;
        this.evaluate = evaluate;
        this.lop = lop;
        this.date = date;
    }


    public Integer getId(){
        return id;
    }
    public Integer getStudentID(){
        return studentID;
    }
    public String getPassword(){
        return password;
    }
    public String getFullName(){
        return fullName;
    }
    public String getGender(){
        return gender;
    }
    public Long getMobileNumber(){
        return mobileNumber;
    }
    public String getAddress(){
        return address;
    }
    public String getImage(){
        return image;
    }
    public String getSubject(){
        return subject;
    }
    public String getEvaluate(){
        return evaluate;
    }
    public String getLop(){
        return lop;
    }
    public String getTeacher(){
        return teacher;
    }
    public String getSpecialized(){
        return specialized;
    }
    public Date getDate(){
        return date;
    }
    public Date getDateModify(){
        return dateModify;
    }
    public Date getDateDelete(){
        return dateDelete;
    }
    public String getStatus(){
        return status;
    }
}