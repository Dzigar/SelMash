package com.selfmash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.DialogDAO;
import com.selfmash.model.Dialog;
import com.selfmash.model.User;
import com.selfmash.service.DialogService;

@Service
@Transactional
public class DialogServiceImpl implements DialogService {

    @Autowired
    private DialogDAO dialogDAO;

    @Override
    public void saveDialog(Dialog dialog) {
        dialogDAO.saveDialog(dialog);
    }

    @Override
    public void updateDialog(Dialog dialog) {
        dialogDAO.updateDialog(dialog);
    }

    @Override
    public Dialog getDialogByUsers(User user1, User user2) {
        return dialogDAO.getDialogByUsers(user1, user2);
    }

}
