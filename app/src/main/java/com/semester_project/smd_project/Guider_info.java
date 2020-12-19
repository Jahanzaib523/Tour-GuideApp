package com.semester_project.smd_project;

public class Guider_info
{
    private String Name;
    private String Age;
    private String Image;
    private String phone;
    private String address;
    private String country;
    private String Price;
    private String email;
    private String budget;
    private String experience;

    public Guider_info(String name, String age, String image, String year, String price, String email_, String phone_, String address_, String country_)
    {
        Name = name;
        Age = age;
        Image = image;
        experience = year;
        Price = price;
        email = email_;
        phone = phone_;
        address = address_;
        country = country_;
    }

    public Guider_info()
    {

    }

    @Override
    public String toString() {
        return "Guider_info{" +
                "Name='" + Name + '\'' +
                ", Age='" + Age + '\'' +
                ", Image='" + Image + '\'' +
                ", Price='" + Price + '\'' +
                ", email='" + email + '\'' +
                ", budget='" + budget + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
