package com.example.lab4good;

import com.example.lab4good.repository.RepositoryException;

public class DuplicateObjectException extends RepositoryException {
    public DuplicateObjectException(String message) {
        super(message);
    }
}