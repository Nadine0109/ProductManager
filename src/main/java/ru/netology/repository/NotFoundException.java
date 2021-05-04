package ru.netology.repository;

import ru.netology.domain.Product;

public class NotFoundException extends RuntimeException {

    String message;

    public NotFoundException(String str) {
        message = str;
        printStackTrace();
    }

    public String toString() {
        return ("NotFoundException Occurred: " + message);
    }

}
