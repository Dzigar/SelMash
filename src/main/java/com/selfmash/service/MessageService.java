package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Message;

public interface MessageService {

    void saveMessage(Message message);

    List<Message> getMessagesByDialoogId(long dialogId);

}
