package com.revature.services;

import com.revature.daos.InboxItemDAO;
import com.revature.exceptions.inboxitem.InvalidInboxItemException;
import com.revature.models.InboxItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboxItemService {
    private final InboxItemDAO inboxItemDAO;
    private final AccountService accountService;

    @Autowired
    public InboxItemService(InboxItemDAO inboxItemDAO, AccountService accountService) {
        this.inboxItemDAO = inboxItemDAO;
        this.accountService = accountService;
    }

    public InboxItem addInboxItem(InboxItem inboxItem) throws InvalidInboxItemException, Exception {
        accountService.searchById(inboxItem.getToAccount().getAccountId());
        accountService.searchById(inboxItem.getFromAccount().getAccountId());

        if (inboxItem.getNotificationTime() == null || inboxItem.getDetails().isEmpty()) {
            throw new InvalidInboxItemException();
        }

        return inboxItemDAO.save(inboxItem);
    }

    public void deleteInboxItem(int inboxItemId) {
        inboxItemDAO.deleteById(inboxItemId);
    }

    public List<InboxItem> getAllAccountInboxItems(int fromAccountId) {
        return inboxItemDAO.findByFromAccount_AccountId(fromAccountId);
    }
}
