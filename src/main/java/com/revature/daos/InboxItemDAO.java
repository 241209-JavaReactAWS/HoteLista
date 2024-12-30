package com.revature.daos;

import com.revature.models.InboxItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboxItemDAO extends JpaRepository<InboxItem, Integer> {
    List<InboxItem> findByAccountId(int accountId);

}
