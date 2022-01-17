package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.util.Objects;

public class Manager extends User implements Serializable {
    private int managerId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private int roleId;
    private int passport;
    private String email;
    private String phone;

    public Manager() {
    }

    public Manager(int managerId, String name, String surname, String login, String password, int roleId, int passport, String email, String phone) {
        this.managerId = managerId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.passport = passport;
        this.email = email;
        this.phone = phone;
    }

    public int getGuestId() {
        return managerId;
    }

    public void setGuestId(int guestId) {
        this.managerId = guestId;
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

    public void setPhone(String phone) {this.phone = phone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        Manager manager = (Manager) o;
        return getGuestId() == manager.getGuestId() &&
                getRoleId() == manager.getRoleId() &&
                getPassport() == manager.getPassport() &&
                getName().equals(manager.getName()) &&
                getSurname().equals(manager.getSurname()) &&
                getLogin().equals(manager.getLogin()) &&
                getEmail().equals(manager.getEmail()) &&
                getPhone().equals(manager.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGuestId(), getName(), getSurname(), getLogin(), getRoleId(), getPassport(), getEmail(), getPhone());
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + managerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", roleId=" + roleId +
                ", passport=" + passport +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}