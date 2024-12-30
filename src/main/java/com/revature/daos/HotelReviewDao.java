package com.revature.daos;

<<<<<<< HEAD:src/main/java/com/revature/daos/AccountDao.java
import java.util.List;

=======
import com.revature.models.HotelReview;
>>>>>>> ab8eb4b47d7a6654a6631b68af6ef1011a29fd60:src/main/java/com/revature/daos/HotelReviewDao.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD:src/main/java/com/revature/daos/AccountDao.java
public interface AccountDAO extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);
    List<Account> findByRole(boolean isOwner); 
=======
public interface HotelReviewDao extends JpaRepository<HotelReview, Integer> {
>>>>>>> ab8eb4b47d7a6654a6631b68af6ef1011a29fd60:src/main/java/com/revature/daos/HotelReviewDao.java
}
