package com.example.pokusaj3.Model;

public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String Spol;
    private String Age;
    private String Height;
    private String Weight;
    private String Activity;
    private String FirstLogin;



    public User(String name, String password, String spol, String age, String height, String weight, String activity,String firstLogin) {
        Name = name;
        Password = password;
        Spol = spol;
        Age = age;
        Height = height;
        Weight = weight;
        Activity = activity;
        FirstLogin = firstLogin;


    }

    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getPhone(){
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSpol() {
        return Spol;
    }

    public void setSpol(String spol) {
        Spol = spol;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    public String getFirstLogin() {
        return FirstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        FirstLogin = firstLogin;
    }
}
