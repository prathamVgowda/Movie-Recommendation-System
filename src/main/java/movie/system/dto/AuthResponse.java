package movie.system.dto;


public class AuthResponse {
    private String jwt;

    // Constructor
    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
