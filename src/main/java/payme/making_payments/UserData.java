package payme.making_payments;

public class UserData {

    private String userFullName;
    private String userPassword;
    private String userAddress;
    private int userId;
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public UserData setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    private String userName;
    private String getUserAddress;

    @Override
    public String toString() {
        return "UserData{" +
                "userAddress='" + userAddress + '\'' +
                '}';
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserData setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public UserData setUserFullName(String userFullName) {
        this.userFullName = userFullName;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserData setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public UserData setUserId(int userId) {
        this.userId = userId;
        return this;
    }

}

