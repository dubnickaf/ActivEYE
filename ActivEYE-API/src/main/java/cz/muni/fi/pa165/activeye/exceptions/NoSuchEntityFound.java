package cz.muni.fi.pa165.activeye.exceptions;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   21-11-2016
 */

public class NoSuchEntityFound extends RuntimeException {

    public NoSuchEntityFound(String msg) {
        super(msg);
    }

    public NoSuchEntityFound(String msg, Throwable thr) {
        super(msg, thr);
    }

}
