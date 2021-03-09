package property_objects;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import utils.PropertyReader;

import java.io.IOException;
import java.util.Map;

@Log4j2
public class AccountPropertyReader extends PropertyReader{
    private static final String ACCOUNTS_PROPERTIES_FILE = "src/test/resources/properties/accounts.properties";
    @Getter @Setter
    private String accountName;

    public AccountPropertyReader(String accountName) throws IOException {
        super(ACCOUNTS_PROPERTIES_FILE);
        this.accountName = accountName;
    }

    private Map<String, String> getAccountPropertiesMap() {
        Map<String, String> accountPropertiesMap = getMapWithKeysLike(s -> s.startsWith(getAccountName()));
        if (accountPropertiesMap.size() != 0) {
            return accountPropertiesMap;
        } else {
            String errorMessage = "There is no properties for account with name '" + accountName + "'";
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    public String getLogin () {
        String login = getAccountPropertiesMap().get(getAccountName() + "_login");
        if (!login.equals("")) {
            return login;
        } else {
            String errorMessage = "Account '" + accountName + "' login is empty, please fill in the line in the properties file, path: " + ACCOUNTS_PROPERTIES_FILE;
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    public String getPassword () {
        String password = getAccountPropertiesMap().get(getAccountName() + "_password");
        if (!password.equals("")) {
            return password;
        } else {
            String errorMessage = "Account '" + accountName + "' password is empty, please fill in the line in the properties file, path: " + ACCOUNTS_PROPERTIES_FILE;
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }
}
