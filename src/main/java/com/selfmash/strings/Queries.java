package com.selfmash.strings;

public class Queries {

    // For User entity
    public static final String QUERY_GET_USER_BY_LOGIN = "from User as u where u.login = :login";

    public static final String QUERY_GET_USER_BY_ID = "from User as u where u.id = :id";

    public static final String QUERY_SELECT_FOLLOWING = "select distinct * from user as u "
            + "inner join followers as f on f.user_id = u.id "
            + "join user_role  as ur on ur.user_id = u.id "
            + "where f.admirer_id = :userId";

    public static final String QUERY_SELECT_ADMIRERS = "select distinct * from user as u "
            + "inner join followers as f on f.admirer_id = u.id "
            + "join user_role  as ur on ur.user_id = u.id "
            + "where f.user_id = :userId";

    public static final String QUERY_SUBSCRIBE = "insert into followers  values ( :followerId, :admirerId)";

    public static final String QUERY_UNSUBSCRIBE = "delete from followers where user_id = :followerId "
            + "and admirer_id = :admirerId";

    // For Photo entity
    public static final String QUERY_DELETE_PHOTO_BY_ID = "delete Photo where id = :photoId";

    // For Notification entity
    public static final String QUERY_GET_NOTIFICATION_BY_ID = "from Notification as n where n.id = :notificationId";

    public static final String QUERY_GET_NOTIFICATIONS_BY_USER_ID = "select * from notification as n "
            + "join notification_user as nu "
            + "on nu.notification_id = n.id join sender_notification as sn"
            + " on sn.notification_id = n.id where nu.user_id = :userId and review = 0";

}
