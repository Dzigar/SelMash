package com.selfmash.strings;

public class PostQueries {

    public static final String GET_FOLLOW_POSTS = "select  * from post p "
            + "join post_user pu on pu.post_id = p.id_post "
            + "left join post_photo as pp on p.id_post = pp.post_id "
            + "left join post_estimation as pe on p.id_post = pe.post_id "
            + "left join post_follower as pf on p.id_post = pf.post_id "
            + "join followers as f on f.user_id = pu.user_id "
            + "where f.admirer_id = :userId " + "order by p.id_post desc";

    public static final String DELETE_POST_BY_ID = "delete Post where id = :postId";

    public static final String INSERT_INTO_POST_USER = "insert into post_user value(:userId, :postId)";

    public static final String MERGE_WITH_ESTIMATION = "insert into post_estimation value(:estimationId, :postId)";
}
