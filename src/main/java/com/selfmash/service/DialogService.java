package com.selfmash.service;

import com.selfmash.model.Dialog;
import com.selfmash.model.User;

public interface DialogService {

    void saveDialog(Dialog dialog);

    void updateDialog(Dialog dialog);

    Dialog getDialogByUsers(User user1, User user2);

}
