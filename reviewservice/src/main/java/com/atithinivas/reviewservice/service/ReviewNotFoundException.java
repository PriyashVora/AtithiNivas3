package com.atithinivas.reviewservice.service;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException() { super(); }
    public ReviewNotFoundException(String message) { super(message); }
}