package com.selfmash.strings;

public class EstimationQueries {

    // For Estimation entity
    public static String GET_EDMIRERS_BY_PHOTO_ID = "select * from  user as u "
            + "join user_estimation as ue on ue.user_id = u.id"
            + " join estimation as e on e.id = ue.estimation_id"
            + " join photo_estimation as pe on pe.estimation_id = e.id"
            + " where pe.photo_id = :photoId";

    public static String GET_ESTIMATION_BY_PHOTO_ID = "select * from  estimation as e"
            + " join photo_estimation as pe on pe.estimation_id = e.id"
            + " join user_estimation as ue on"
            + " ue.estimation_id = e.id"
            + " join post_estimation as post_est on post_est.estimation_id = e.id"
            + " where pe.photo_id = :photoId";

    public static String CHECH_FOR_APPRECIATE = "select ue.user_id = u.id as containce from user_estimation as ue"
            + " join user as u on u.id = ue.user_id"
            + " join photo_estimation as pe on pe.estimation_id = ue.estimation_id"
            + " where pe.photo_id = :photoId and ue.user_id = :userId";

    public static final String DELETE_USER_ESTIMATION = "delete from user_estimation where user_id = :userId "
            + "and estimation_id = :estimationId";

    public static final String DELETE_ESTIMATION = "delete Estimation where id = :estimationId";
}
