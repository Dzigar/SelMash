package com.selfmash.strings;

public class PhotoQueries {

    // For Photo entity
    public static final String DELETE_PHOTO_BY_ID = "delete Photo where id = :photoId";

    public static final String GET_USER_PHOTOS = "from Photo as p where p.user = :user";

    public static final String GET_PHOTO_BY_ID = "from Photo as u where u.id = :id";
}
