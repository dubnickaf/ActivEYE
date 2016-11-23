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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof NotAuthenticatedUserDTO)) return false;

        NotAuthenticatedUserDTO that = (NotAuthenticatedUserDTO) o;

        if (!getEmail().equals(that.getEmail())) return false;
        return getPassword().equals(that.getPassword());

    }

    @Override
    public int hashCode() {
        int result = getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NotAuthenticatedUserDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
