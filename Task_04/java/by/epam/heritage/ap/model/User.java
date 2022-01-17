package by.epam.heritage.ap.model;

public abstract class User extends Entity{

    public abstract int getGuestId();

    public abstract void setGuestId(int guestId);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getSurname();

    public abstract void setSurname(String surname);

    public abstract String getLogin();

    public abstract void setLogin(String login);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract int getRoleId();

    public abstract void setRoleId(int roleId);

    public abstract int getPassport();

    public abstract void setPassport(int passport);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPhone();
    public abstract void setPhone(String phone);
}
