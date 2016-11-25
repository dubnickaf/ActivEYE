package cz.muni.fi.pa165.activeye.exceptions;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   21-11-2016
 */

public class NoSuchEntityFoundException extends RuntimeException {

    public NoSuchEntityFoundException(String msg) {
        super(msg);
    }

    public NoSuchEntityFoundException(String msg, Throwable thr) {
        super(msg, thr);
    }

}
