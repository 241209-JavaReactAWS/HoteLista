package com.revature.services;

import com.revature.daos.InboxItemDAO;
import com.revature.exceptions.inboxitem.InvalidDateInboxException;
import com.revature.exceptions.inboxitem.InvalidDetailsInboxException;
import com.revature.models.InboxItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboxItemService {
    private final InboxItemDAO inboxItemDAO;
    private final AccountService accountService;
    private final HotelServices hotelServices;

    @Autowired
    public InboxItemService(InboxItemDAO inboxItemDAO, AccountService accountService, HotelServices hotelServices) {
        this.inboxItemDAO = inboxItemDAO;
        this.accountService = accountService;
        this.hotelServices = hotelServices;
    }

    public InboxItem addInboxItem(InboxItem inboxItem) throws InvalidDetailsInboxException, InvalidDateInboxException {
        // TODO: check if account is valid
        // TODO: check if hotel is valid

        if (inboxItem.getNotificationTime() == null) {
            throw new InvalidDateInboxException();
        }

        if (inboxItem.getDetails().isEmpty()) {
            throw new InvalidDetailsInboxException();
        }

        return inboxItemDAO.save(inboxItem);
    }

    public void deleteInboxItem(InboxItem inboxItem) {
        inboxItemDAO.delete(inboxItem);
    }

    public List<InboxItem> getAllAccountInboxItems(int accountId) {
        return inboxItemDAO.findByAccountId(accountId);
    }
}
