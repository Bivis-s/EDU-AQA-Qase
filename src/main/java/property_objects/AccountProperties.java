package property_objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountProperties {
    private String login;
    private String password;
    private String apiToken;
}
