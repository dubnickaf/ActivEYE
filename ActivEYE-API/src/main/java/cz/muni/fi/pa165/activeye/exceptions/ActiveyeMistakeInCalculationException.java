package cz.muni.fi.pa165.activeye.exceptions;

/**
 * Exception for mistakes in calculations
 * @author Filip Dubnička [445647]
 */
public class ActiveyeMistakeInCalculationException extends RuntimeException {
    public ActiveyeMistakeInCalculationException(String msg) {
        super(msg);
    }

    public ActiveyeMistakeInCalculationException(String msg, Throwable thr) {
        super(msg, thr);
    }

}
