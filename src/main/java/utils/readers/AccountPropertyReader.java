package utils.readers;

import lombok.Getter;
import lombok.Setter;
import property_objects.AccountProperties;
import throwables.PropertyError;

import java.util.Map;

public class AccountPropertyReader extends PropertyReader {
    private static final String ACCOUNTS_PROPERTIES_FILE_PATH = "src/test/resources/properties/accounts.properties";
    private static final String LOGIN_ENDING = "_login";
    private static final String PASSWORD_ENDING = "_password";
    private static final String API_TOKEN_ENDING = "_api_token";
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

    /**
     * Returns environment variables is it not null, else returns property from accounts.properties file
     *
     * @param propertyEnding for example: bivis_login=Bivis-s | _login - property login
     * @return environment variable or property
     */
    private String getSystemEnvOrProperty(String propertyEnding) {
        String loginEnv = System.getenv(accountName + propertyEnding);
        if (loginEnv != null) {
            return loginEnv;
        } else {
            return getProperty(propertyEnding);
        }
    }

    private String getLogin() {
        return getSystemEnvOrProperty(LOGIN_ENDING);
    }

    private String getPassword() {
        return getSystemEnvOrProperty(PASSWORD_ENDING);
    }

    private String getApiToken() {
        return getSystemEnvOrProperty(API_TOKEN_ENDING);
    }

    public AccountProperties getAccountsProperties() {
        AccountProperties properties = new AccountProperties();
        properties.setLogin(getLogin());
        properties.setPassword(getPassword());
        properties.setApiToken(getApiToken());
        return properties;
    }
}
