package ru.netology;


public class NotFoundException extends RuntimeException {
    String message;

    public NotFoundException(String str) {
        message = str;
    }

    public String toString() {
        return ("NotFoundException Occurred: " + message);
    }
}
