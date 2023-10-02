
package model;

import java.sql.Date;

public class Human {
    private int id;
    private String name;
    private Date dob;
    private String gender;
    private HumanType type;

    public Human() {
    }

    public Human(int id, String name, Date dob, String gender, HumanType type) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.type = type;
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

    public HumanType getType() {
        return type;
    }

    public void setType(HumanType type) {
        this.type = type;
    } 
}
