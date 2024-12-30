package com.revature.daos;

import com.revature.models.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxDao extends JpaRepository<Inbox, Integer> {
}
