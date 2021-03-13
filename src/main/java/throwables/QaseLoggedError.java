package throwables;

import lombok.extern.log4j.Log4j2;

/**
 * Logs every error message when a new QaseLoggedError object is created
 */
@Log4j2
public class QaseLoggedError extends Error {
    public QaseLoggedError(String message) {
        super(message);
        log.trace("[" + getClass().getSimpleName() + "]::: " + message);
    }
}
