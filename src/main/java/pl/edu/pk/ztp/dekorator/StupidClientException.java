package pl.edu.pk.ztp.dekorator;

public class StupidClientException extends RuntimeException {

    public StupidClientException() {
        super("You're stupid, or what?");
    }
}
