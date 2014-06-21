package com.selfmash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.MessageDAO;
import com.selfmash.model.Message;
import com.selfmash.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public void saveMessage(Message message) {
        messageDAO.saveMessage(message);
    }

    @Override
    public List<Message> getMessagesByDialoogId(long dialogId) {
        return messageDAO.getMessagesByDialoogId(dialogId);
    }

}
