package com.tauseef.app.entities;

import com.tauseef.app.core.Console;
import java.util.HashMap;

public class Patient {

    private static int indexId = 1;
    private int id;
    private String name;
    private String email;
    private String phone;
    private String nic;
    private int age;

    public Patient() {
        this.id = indexId++;
        this.name = "";
        this.email = "";
        this.phone = "";
        this.nic = "";
        this.age = 0;
    }

    public Patient(String name, String email, String phone, String nic, int age) {
        this.id = indexId++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nic = nic;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getNic() {
        return nic;
    }

    public int getAge() {
        return age;
    }

    public boolean updateField(String field, String value, Console console) {
        try {
            switch (field) {
                case "name":
                    this.name = value;
                    console.success("Patient Name successfully updated!");
                    return true;
                case "email":
                    this.email = value;
                    console.success("Patient Email successfully updated!");
                    return true;
                case "phone":
                    this.phone = value;
                    console.success("Patient Phone Number successfully updated!");
                    return true;
                case "nic":
                    this.nic = value;
                    console.success("Patient NIC successfully updated!");
                    return true;
                case "age":
                    this.age = Integer.parseInt(value);
                    console.success("Patient Age successfully updated!");
                    return true;
                default:
                    console.error("The field entered is invalid!");
                    return false;
            }
        } catch (Exception e) {
            console.error("The value entered is invalid!");
            return false;
        }
    }

    public HashMap<String, String> fieldSet()
    {
        HashMap<String, String> fieldList = new HashMap<String, String>();
        fieldList.put("name", "Name");
        fieldList.put("email", "Email");
        fieldList.put("phone", "Phone Number");
        fieldList.put("nic", "Nic");
        fieldList.put("age", "Age");

        return fieldList;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", email=" + email +
                ", phone=" + phone +
                ", nic=" + nic +
                ", age=" + age +
                '}';
    }
}
