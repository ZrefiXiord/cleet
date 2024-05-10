package fr.zrefixiord.cleet.payload.request;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private final String username;
    private final String password;
    private final String displayName;
    private final String email;

    public RegisterRequest(String username, String password, String displayName, String email) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
    }
}
