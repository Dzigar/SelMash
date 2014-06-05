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

    // For Estimation entity
    public static String QUERY_GET_EDMIRERS_BY_PHOTO_ID = "select * from  user as u "
            + "join user_estimation as ue on ue.user_id = u.id"
            + " join estimation as e on e.id = ue.estimation_id"
            + " join photo_estimation as pe on pe.estimation_id = e.id"
            + " where pe.photo_id = :photoId";

    public static String QUERY_GET_ESTIMATION_BY_PHOTO_ID = "select * from  estimation as e"
            + " join photo_estimation as pe on pe.estimation_id = e.id"
            + " join user_estimation as ue on"
            + " ue.estimation_id = e.id"
            + " join post_estimation as post_est on post_est.estimation_id = e.id"
            + " where pe.photo_id = :photoId";

    public static String QUERY_CHECH_FOR_APPRECIATE = "select ue.user_id = u.id as containce from user_estimation as ue"
            + " join user as u on u.id = ue.user_id"
            + " join photo_estimation as pe on pe.estimation_id = ue.estimation_id"
            + " where pe.photo_id = :photoId and ue.user_id = :userId";

    public static final String QUERY_DELETE_USER_ESTIMATION = "delete from user_estimation where user_id = :userId "
            + "and estimation_id = :estimationId";
}
