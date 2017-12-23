package hr.fer.opp.onedayjob.hr.fer.opp.onedayjob.model;

import java.io.Serializable;

/**
 * Created by Ivan on 20.12.2017..
 */

public class Korisnik implements Serializable{

    //@SerializedName("avatar_location")
    String avatarLocation;

    //@SerializedName("first_name")
    String firstName;

   //@SerializedName("last_name")
    String lastName;

    //@SerializedName("phone_no")
    String phoneNum;

    //@SerializedName("email_sknf")
    String email;

    //@SerializedName("spouse")
    String spouse;

    //@SerializedName("age")
    String age;


    public String getAvatarLocation() {
        return avatarLocation;
    }

    public void setAvatarLocation(String avatarLocation) {
        this.avatarLocation = avatarLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
