package utils.readers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import property_objects.AccountProperties;

import java.io.IOException;
import java.util.Map;

@Log4j2
public class AccountPropertyReader extends PropertyReader {
    private static final String ACCOUNTS_PROPERTIES_FILE_PATH = "src/test/resources/properties/accounts.properties";
    @Getter
    @Setter
    private String accountName;

    public AccountPropertyReader(String accountName) throws IOException {
        super(ACCOUNTS_PROPERTIES_FILE_PATH);
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

    private String getLogin() {
        String login = getAccountPropertiesMap().get(getAccountName() + "_login");
        if (login != null && !login.equals("")) {
            return login;
        } else {
            String errorMessage = "Account '" + accountName + "' login is empty, please fill in the line in the properties file, path: " + ACCOUNTS_PROPERTIES_FILE_PATH;
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    private String getPassword() {
        String password = getAccountPropertiesMap().get(getAccountName() + "_password");
        if (password != null && !password.equals("")) {
            return password;
        } else {
            String errorMessage = "Account '" + accountName + "' password is empty, please fill in the line in the properties file, path: " + ACCOUNTS_PROPERTIES_FILE_PATH;
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    public AccountProperties getAccountsProperties() {
        AccountProperties properties = new AccountProperties();
        properties.setLogin(getLogin());
        properties.setPassword(getPassword());
        return properties;
    }
}
