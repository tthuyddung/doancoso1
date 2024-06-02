package Model;

import java.util.Date;

public class AppointmentData {

    private Integer id;
    private Integer appointmentID;
    private Integer studentID;
    private String name;
    private String gender;
    private String subject;
    private String evaluate;
    private String lop;
    private Long mobileNumber;
    private String address;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;
    private String teacherID;
    private String specialized;
    private Date schedule;

    public AppointmentData(Integer id, Integer appointmentID, String name,String gender, String teacherID, String specialized, Long mobileNumber,
                           String subject,String evaluate, String lop, String address, Date date, Date dateModify, Date DateDelete, String status, Date schedule){
        this.id=id;
        this.appointmentID = appointmentID;
        this.name = name;
        this.gender = gender;
        this.teacherID=teacherID;
        this.specialized=specialized;
        this.mobileNumber = mobileNumber;
        this.subject = subject;
        this.evaluate = evaluate;
        this.lop = lop;
        this.address = address;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete= dateDelete;
        this.status = status;
        this.schedule = schedule;
    }

    public AppointmentData( Integer appointmentID, String name,String gender, Long mobileNumber,
                            String subject,String evaluate, String lop, String address, Date date, Date dateModify, Date DateDelete, String status, Date schedule){
        this.appointmentID = appointmentID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.subject = subject;
        this.evaluate = evaluate;
        this.lop = lop;
        this.address = address;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete= dateDelete;
        this.status = status;
        this.schedule = schedule;
    }
    public AppointmentData(Integer appointmentID, String name,
                           String subject, Date date, String status){
        this.appointmentID = appointmentID;
        this.name = name;
        this.subject = subject;
        this.date = date;
        this.status = status;
    }

    public AppointmentData(Integer appointmentID, String subject, String evaluate, String lop, String teacherID, java.sql.Date schedule) {
        this.appointmentID = appointmentID;
        this.subject = subject;
        this.evaluate = evaluate;
        this.lop = lop;
        this.teacherID = teacherID;
        this.schedule = schedule;
    }
    public Integer getId(){ return id;}
    public Integer getAppointmentID(){
        return appointmentID;
    }
    public String getName(){
        return name;
    }
    public String getGender(){
        return gender;
    }
    public String getTeacherID(){ return teacherID; }
    public String getSpecialized(){ return specialized; }
    public Long getMobileNumber(){
        return mobileNumber;
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
    public String getAddress(){
        return address;
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
    public Date getSchedule(){
        return schedule;
    }
}