package entity;

public class User {
    private String userName;
    private String userPassword;
    private long userPhone;
    private String address;
    private String email;

    public User(String userName, String userPassword, long userPhone) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPhone=" + userPhone +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
