package cz.muni.fi.pa165.activeye.dto;

/**
 * DTO representing user that has to be authenticated.
 * @author Filip Dubnička [445647]
 */
public class NotAuthenticatedUserDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
