package by.epam.heritage.ap.model;


import java.io.Serializable;
import java.util.Objects;

public class Role extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int roleId;
    private String role;

    public Role() {
    }

    public Role(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != getClass()) return false;
        Role role1 = (Role) o;
        return getRoleId() == role1.getRoleId() &&
                getRole().equals(role1.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + roleId +
                ", role=" + role +
                '}';
    }
}
