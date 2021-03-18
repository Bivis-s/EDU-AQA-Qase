package utils.readers;

import lombok.Getter;
import lombok.Setter;
import property_objects.AccountProperties;
import throwables.PropertyError;

import java.util.Map;

public class AccountPropertyReader extends PropertyReader {
    private static final String ACCOUNTS_PROPERTIES_FILE_PATH = "src/test/resources/properties/accounts.properties";
    @Getter
    @Setter
    private String accountName;

    public AccountPropertyReader(String accountName) {
        super(ACCOUNTS_PROPERTIES_FILE_PATH);
        this.accountName = accountName;
    }

    private Map<String, String> getAccountPropertiesMap() {
        Map<String, String> accountPropertiesMap = getMapWithKeysLike(s -> s.startsWith(getAccountName()));
        if (accountPropertiesMap.size() != 0) {
            return accountPropertiesMap;
        } else {
            throw new PropertyError("There is no properties for account with name '" + accountName + "'");
        }
    }

    private String getProperty(String propertyEnding) {
        String login = getAccountPropertiesMap().get(getAccountName() + propertyEnding);
        if (login != null && !login.equals("")) {
            return login;
        } else {
            throw new PropertyError("Account '" + accountName + "' property '" + propertyEnding + "' is empty, please fill in the line in the properties file, path: " + ACCOUNTS_PROPERTIES_FILE_PATH);
        }
    }

    private String getLogin() {
        return getProperty("_login");
    }

    private String getPassword() {
        return getProperty("_password");
    }

    private String getApiToken() {
        return getProperty("_api-token");
    }

    public AccountProperties getAccountsProperties() {
        AccountProperties properties = new AccountProperties();
        properties.setLogin(getLogin());
        properties.setPassword(getPassword());
        properties.setApiToken(getApiToken());
        return properties;
    }
}
