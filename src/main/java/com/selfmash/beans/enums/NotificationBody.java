package com.selfmash.beans.enums;

public enum NotificationBody {

    ADD_TO_FRIENDS;

    private String body;

    private NotificationBody(String body) {
        this.setBody(body);
    };

    private NotificationBody() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}
