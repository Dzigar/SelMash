package com.selfmash.strings;

public class DialogQueries {

    public static final String GET_DIALOG_BY_USER_ID = "select * from dialogs where user1_id = :user1Id"
            + " and user2_id = :user2Id"
            + " or user1_id = :user2Id and user2_id = :user1Id";
}
