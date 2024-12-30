package com.revature.daos;

import com.revature.models.InboxItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxItemDAO extends JpaRepository<InboxItem, Integer> {
}
