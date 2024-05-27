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
    private String description;
    private String diagnosis;
    private String treatment;
    private String teacher;
    private String specialized;
    private String gender;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;

    public StudentData(Integer id, Integer studentID, String password, String fullName,
                       Long mobileNumber, String gender, String address, String image, String description,
                       String diagnosis, String treatment, String teacher, String specialized,
                       Date date, Date dateModify, Date dateDelete, String status){
        this.id = id;
        this.studentID = studentID;
        this.password = password;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.address = address;
        this.image = image;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
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
            , String description, String diagnosis, String treatment
            , String teacher, String image, Date date){
        this.id = id;
        this.studentID = studentID;
        this.fullName = fullName;
        this.gender = gender;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.teacher = teacher;
        this.image = image;
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
    public String getDescription(){
        return description;
    }
    public String getDiagnosis(){
        return diagnosis;
    }
    public String getTreatment(){
        return treatment;
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
