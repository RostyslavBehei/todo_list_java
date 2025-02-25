package org.example.exeption;

public class InvalidTaskDateException extends Exception {
    public InvalidTaskDateException(String message) {
        super(message);
    }
}
