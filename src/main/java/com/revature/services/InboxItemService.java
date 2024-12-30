package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.InboxItemDAO;
import com.revature.models.InboxItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboxItemService {
    private final InboxItemDAO inboxItemDAO;
    private final AccountDAO accountDAO;

    @Autowired
    public InboxItemService(InboxItemDAO inboxItemDAO, AccountDAO accountDAO) {
        this.inboxItemDAO = inboxItemDAO;
        this.accountDAO = accountDAO;
    }

}
