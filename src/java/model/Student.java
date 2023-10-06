
package model;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private Date dob;
    private String gender;
    private Major major;

    public Student() {
    }

    public Student(int id, String name, Date dob, String gender, Major major) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
    
    
}
