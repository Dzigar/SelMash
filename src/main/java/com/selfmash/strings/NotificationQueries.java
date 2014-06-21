package com.selfmash.strings;

public class NotificationQueries {

    // For Notification entity
    public static final String GET_NOTIFICATION_BY_ID = "from Notification as n where n.id = :notificationId";

    public static final String GET_NOTIFICATIONS_BY_USER_ID = "select * from notification as n "
            + "join notification_user as nu "
            + "on nu.notification_id = n.id "
            + "join notification_sender as sn "
            + "on sn.notification_id = n.id "
            + "where nu.user_id = :userId and review = 0";

    public static final String DELETE_NOTIFICATION_BY_PHOTO = "delete Notification as n where n.photo = :photo";
}
