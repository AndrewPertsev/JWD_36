package by.epam.ap.hotelapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String login;
    private char[] password;
    private int role_id;
    private String role;
    private int passport;
    private String email;
    private String phone;
    private String country;
    private int attribute_id;
    private boolean vip;
    private boolean nongrata;
    private String comment;

    public User() {}

    public User(int id, String name, String surname, String login, char[] password, int role_id, String role, int passport, String email, String phone, String country, int attribute_id, boolean vip, boolean nongrata, String comment) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role_id = role_id;
        this.role = role;
        this.passport = passport;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.attribute_id = attribute_id;
        this.vip = vip;
        this.nongrata = nongrata;
        this.comment = comment;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPassport() {
        return passport;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(int attribute_id) {
        this.attribute_id = attribute_id;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isNongrata() {
        return nongrata;
    }

    public void setNongrata(boolean nongrata) {
        this.nongrata = nongrata;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getRole_id() == user.getRole_id() &&
                getPassport() == user.getPassport() &&
                getAttribute_id() == user.getAttribute_id() &&
                getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                getLogin().equals(user.getLogin()) &&
                getRole().equals(user.getRole()) &&
                getEmail().equals(user.getEmail()) &&
                getPhone().equals(user.getPhone()) &&
                getCountry().equals(user.getCountry()) &&
                getComment().equals(user.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getLogin(), getRole_id(), getRole(), getPassport(), getEmail(), getPhone(), getCountry(), getAttribute_id(), getComment());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", role_id=" + role_id +
                ", role='" + role + '\'' +
                ", passport=" + passport +
                ", mail='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", attribute_id=" + attribute_id +
                ", vip=" + vip +
                ", nongrata=" + nongrata +
                ", comment='" + comment + '\'' +
                '}';
    }
}
