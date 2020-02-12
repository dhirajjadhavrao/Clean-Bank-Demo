package org.cleanbank.cleanbank.entity;

public class Response {
    private String message;
    private boolean status;

    //Constructors
    public Response(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public Response() {
    }

    //GettersAndSetters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
