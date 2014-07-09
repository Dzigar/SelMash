package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Meet;

public interface MeetService {

    void createMeet(Meet meet);

    Meet getMeetById(long meetId);

    Meet getMeetByUsersId(long userFromId, long userToId);

    List<Meet> getMeetToFromUser(long userToId);

    void acceptMeet(long meetId);
}
