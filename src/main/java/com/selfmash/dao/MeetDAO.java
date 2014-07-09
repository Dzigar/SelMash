package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Meet;
import com.selfmash.model.User;

public interface MeetDAO {

    void createMeet(Meet meet);

    Meet getMeetById(long meetId);

    Meet getMeetByUsersId(User userFrom, User userTo);

    List<Meet> getMeetToFromUser(User userTo);

    void updateMeet(Meet meet);
}
