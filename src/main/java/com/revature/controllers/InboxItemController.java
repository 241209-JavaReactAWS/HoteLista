package com.revature.controllers;

import com.revature.exceptions.inboxitem.InvalidInboxItemException;
import com.revature.models.InboxItem;
import com.revature.services.InboxItemService;
import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/{accountId}/inbox")
public class InboxItemController {
    private final InboxItemService inboxService;

    @Autowired
    public InboxItemController(InboxItemService inboxService) {
        this.inboxService = inboxService;
    }

    @PostMapping("/add")
    public ResponseEntity<InboxItem> addInboxItem(@PathVariable int accountId, @RequestBody InboxItem inboxItem){
        try{
            InboxItem newInboxItem = inboxService.addInboxItem(inboxItem);
            return ResponseEntity.ok(newInboxItem);
        } catch (InvalidInboxItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{inboxItemId}")
    public ResponseEntity<Void> deleteInboxItem(@PathVariable int accountId, @PathVariable int inboxItemId){
        inboxService.deleteInboxItem(inboxItemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<InboxItem>> getAllInboxItems(@PathVariable int accountId){
        List<InboxItem> inbox = inboxService.getAllAccountInboxItems(accountId);
        return ResponseEntity.ok(inbox);
    }
}
