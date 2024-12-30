package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.InboxItemDao;
import com.revature.models.InboxItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboxItemService {
    private final InboxItemDao inboxItemDAO;
    private final AccountDAO accountDAO;

    @Autowired
    public InboxItemService(InboxItemDao inboxItemDao, AccountDAO accountDAO) {
        this.inboxItemDao = inboxItemDao;
        this.accountDAO = accountDAO;
    }

    public InboxItem getInboxItem
}
