package com.revature.controllers;

import com.revature.services.InboxItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
// TODO: Add CrossOrign with credetials and modify endpoints accordingly
public class InboxItemController {
    private final InboxItemService inboxService;

    @Autowired
    public InboxItemController(InboxItemService inboxService) {
        this.inboxService = inboxService;
    }
}
