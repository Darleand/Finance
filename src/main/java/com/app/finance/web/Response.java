package com.app.finance.web;

public class Response {
    private String message;
    private String messageJava;

    public Response(String message, String messageJava) {
        this.message = message;
        this.messageJava = messageJava;
    }

    public String getMessageJava() {
        return messageJava;
    }

    public void setMessageJava(String messageJava) {
        this.messageJava = messageJava;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
