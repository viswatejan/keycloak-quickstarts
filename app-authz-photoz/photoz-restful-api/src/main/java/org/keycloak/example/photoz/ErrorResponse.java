package org.keycloak.example.photoz;

import org.springframework.http.HttpStatus;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
public class ErrorResponse extends RuntimeException {

    private final HttpStatus status;

    public ErrorResponse(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ErrorResponse(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}