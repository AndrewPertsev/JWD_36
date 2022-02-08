package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.util.Objects;

public class Guest extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int guestId;
    private int roleId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String country;
    private String comment;
    private int passport;
    private boolean isVip;
    private boolean isNonGrata;

    public Guest() {
    }

    public Guest(String name, String surname, String login, String password, int passport, String email, String phone, String country) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.passport = passport;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }

    public Guest(int guestId, String name, String surname, String login, String password, int roleId, int passport, String email, String phone, String country, boolean isVip, boolean isNonGrata, String comment) {
        this.guestId = guestId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.passport = passport;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.isVip = isVip;
        this.isNonGrata = isNonGrata;
        this.comment = comment;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        this.isVip = vip;
    }

    public boolean isNonGrata() {
        return isNonGrata;
    }

    public void setNonGrata(boolean nonGrata) {
        this.isNonGrata = nonGrata;
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
        if (o.getClass() != getClass()) return false;
        Guest guest = (Guest) o;
        return getGuestId() == guest.getGuestId() &&
                getRoleId() == guest.getRoleId() &&
                getPassport() == guest.getPassport() &&
                getName().equals(guest.getName()) &&
                getSurname().equals(guest.getSurname()) &&
                getLogin().equals(guest.getLogin()) &&
                getEmail().equals(guest.getEmail()) &&
                getPhone().equals(guest.getPhone()) &&
                getCountry().equals(guest.getCountry()) &&
                getComment().equals(guest.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGuestId(), getName(), getSurname(), getLogin(), getRoleId(), getPassport(), getEmail(), getPhone(), getCountry(), getComment());
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + guestId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", role_id=" + roleId +
                ", passport=" + passport +
                ", mail='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", vip=" + isVip +
                ", nongrata=" + isNonGrata +
                ", comment='" + comment + '\'' +
                '}';
    }
}
