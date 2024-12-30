package com.revature.services;

import com.revature.daos.AccountDao;
import com.revature.daos.InboxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboxService {
    private final InboxDao inboxDao;
    private final AccountDao accountDao;

    @Autowired
    public InboxService(InboxDao inboxDao, AccountDao accountDao) {
        this.inboxDao = inboxDao;
        this.accountDao = accountDao;
    }
}
