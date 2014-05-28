package com.selfmash.beans.enums;

public enum ActionBody {

    ADD_USER, UPLOAD_PHOTO;

    private String actionBody;

    private ActionBody() {
        // TODO Auto-generated constructor stub
    }

    private ActionBody(String str) {
        this.actionBody = str;
    }

    /**
     * @return the actionBody
     */
    public String getActionBody() {
        return actionBody;
    }

    /**
     * @param actionBody
     *            the actionBody to set
     */
    public void setActionBody(String actionBody) {
        this.actionBody = actionBody;
    }

}
