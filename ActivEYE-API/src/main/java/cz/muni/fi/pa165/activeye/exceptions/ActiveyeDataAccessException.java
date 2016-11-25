package cz.muni.fi.pa165.activeye.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Subclass of DataAccessException for our project
 * @author Filip Dubnička [445647]
 */
public class ActiveyeDataAccessException extends DataAccessException{

    public ActiveyeDataAccessException(String msg) {
        super(msg);
    }

    public ActiveyeDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
