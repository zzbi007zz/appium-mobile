package test_data_model;

public class LoginCred {
    private String email;
    private String passsword;

    public LoginCred(String email,String password) {
        this.email = email;
        this.passsword = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPasssword() {
        return passsword;
    }

    @Override
    public String toString() {
        return "LoginCred{" +
                "email='" + email + '\'' +
                ", passsword='" + passsword + '\'' +
                '}';
    }
}
