package com.selfmash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.MeetDAO;
import com.selfmash.model.Meet;
import com.selfmash.service.MeetService;
import com.selfmash.service.NotificationService;
import com.selfmash.service.UserService;

@Service("meetService")
@Transactional
public class MeetServiceImpl implements MeetService {

    @Autowired
    private MeetDAO meetDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void createMeet(Meet meet) {
        meetDAO.createMeet(meet);
    }

    @Override
    public Meet getMeetByUsersId(long userFromId, long userToId) {
        return meetDAO.getMeetByUsersId(userService.getUserById(userFromId),
                userService.getUserById(userToId));
    }

    @Override
    public List<Meet> getMeetToFromUser(long userToId) {
        return meetDAO.getMeetToFromUser(userService.getUserById(userToId));
    }

    @Override
    public Meet getMeetById(long meetId) {
        return meetDAO.getMeetById(meetId);
    }

    @Override
    public void acceptMeet(long meetId) {
        Meet meet = getMeetById(meetId);
        meet.setAccept(true);
        meetDAO.updateMeet(meet);
    }

}
