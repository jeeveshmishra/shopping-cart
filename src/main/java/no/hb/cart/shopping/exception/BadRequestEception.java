package no.hb.cart.shopping.exception;

public class BadRequestEception extends RuntimeException {

    private static final long serialVersionUID = -3496767946698405459L;

    public BadRequestEception() { super(); }

    public BadRequestEception(String message) { super(message); }

    public BadRequestEception (String message, Throwable cause) { super(message, cause); }

}
