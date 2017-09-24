package ua.rd.domain;

/**
 * Created by Olha Chuchuk on 24.09.2017.
 */
public class DifferentUserException extends RuntimeException {
    public DifferentUserException(String s) {
        super(s);
    }
}
