package property_objects;

import lombok.Data;

@Data
public class AccountProperties implements Properties {
    private String login;
    private String password;
    private String apiToken;
}
