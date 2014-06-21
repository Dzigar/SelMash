package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Message;

public interface MessageDAO {

    void saveMessage(Message message);

    List<Message> getMessagesByDialoogId(long dialogId);
}
