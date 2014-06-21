package com.selfmash.strings;

public class UserQueries {

    // For User entity
    public static final String GET_USER_BY_LOGIN = "from User as u where u.login = :login";

    public static final String GET_USER_BY_ID = "from User as u where u.id = :id";

    public static final String GET_ALL_USERS = "select * from user as u join user_role as ur on "
            + "ur.user_id = u.id where u.id not in "
            + "(select f.user_id from followers as f where f.admirer_id = :userId)"
            + " and u.id != :userId";

    public static final String SELECT_FOLLOWING = "select distinct * from user as u "
            + "inner join followers as f on f.user_id = u.id "
            + "join user_role  as ur on ur.user_id = u.id "
            + "where f.admirer_id = :userId";

    public static final String SELECT_ADMIRERS = "select distinct * from user as u "
            + "inner join followers as f on f.admirer_id = u.id "
            + "join user_role  as ur on ur.user_id = u.id "
            + "where f.user_id = :userId";

    public static final String SUBSCRIBE = "insert into followers  values ( :followerId, :admirerId)";

    public static final String UNSUBSCRIBE = "delete from followers where user_id = :followerId "
            + "and admirer_id = :admirerId";

    public static final String REMOVE_PROFILE_PHOTO = "UPDATE user as u SET u.profilePhoto_id = NULL WHERE u.id = :userId";
}
