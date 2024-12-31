package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.BookingDAO;
import com.revature.daos.PaymentDAO;
import com.revature.models.Account;
import com.revature.models.Payment;
import com.revature.requests.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final AccountDAO accountDAO;
    private final BookingDAO bookingDAO;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO, AccountDAO accountDAO, BookingDAO bookingDAO) {
        this.paymentDAO = paymentDAO;
        this.accountDAO = accountDAO;
        this.bookingDAO = bookingDAO;
    }

    public PaymentDto addPayment(Payment payment, Integer userId) {
        PaymentDto paymentDto = new PaymentDto();
            Optional<Account> retreiveUser = accountDAO.findById(userId);
            if (retreiveUser.isEmpty()) {
                throw new RuntimeException();
            }else{
                Payment createdCard = new Payment();
                createdCard.setCardNumber(payment.getCardNumber());
                createdCard.setCardHolderName(payment.getCardHolderName());
                createdCard.setCvv(payment.getCvv());
                createdCard.setPostalcode(payment.getPostalcode());
                createdCard.setAccount(retreiveUser.get());

                Payment result = paymentDAO.save(createdCard);

                //Adding result to paymentDto
                paymentDto.setPaymentId(result.getPaymentId());
                paymentDto.setCvv(result.getCvv());
                paymentDto.setCardNumber(result.getCardNumber());
                paymentDto.setPostalcode(result.getPostalcode());
                paymentDto.setAccount(retreiveUser.get());

                return paymentDto;
            }

    }
}

//Optional<Account> retreiveUser = accountDAO.findById(userId);
//            if (retreiveUser.isEmpty()) {
//Payment createdCard = new Payment();
//                createdCard.setCardNumber(cardToBeAdded.getCardNumber());
//        createdCard.setCardHolderName(cardToBeAdded.getCardHolderName());
//        createdCard.setCvv(cardToBeAdded.getCvv());
//        createdCard.setPostcode(cardToBeAdded.getPostcode());
//        createdCard.setUser(retreiveUser.get());
//Payment result = paymentRepository.save(createdCard);
//                return result;
//            }else{
//                    throw new RuntimeException();
//            }