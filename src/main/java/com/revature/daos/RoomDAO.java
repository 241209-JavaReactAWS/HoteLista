package com.revature.daos;

import com.revature.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDAO extends JpaRepository<Room, Integer> {
}
