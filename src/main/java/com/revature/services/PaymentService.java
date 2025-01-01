package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.PaymentDAO;
import com.revature.exceptions.payment.PaymentNotFound;
import com.revature.models.Account;
import com.revature.models.Payment;
import com.revature.requests.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final AccountService accountService;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO, AccountService accountService) {
        this.paymentDAO = paymentDAO;
        this.accountService = accountService;
    }

    /** TODO:
     *  Get All By account Id
     *
     * */

    public PaymentDTO addPayment(Payment payment, Integer accountId) throws Exception {
        Account account = accountService.searchById(accountId);
        Payment createdCard = new Payment();
        createdCard.setCardNumber(payment.getCardNumber());
        createdCard.setCardHolderName(payment.getCardHolderName());
        createdCard.setCvv(payment.getCvv());
        createdCard.setPostalCode(payment.getPostalCode());
        createdCard.setAccount(account);

        Payment savedCard = paymentDAO.save(createdCard);

        PaymentDTO submitCard = new PaymentDTO();
        submitCard.setPaymentId(savedCard.getPaymentId());
        submitCard.setCardNumber(savedCard.getCardNumber());
        submitCard.setCardHolderName(savedCard.getCardHolderName());
        submitCard.setCvv(savedCard.getCvv());
        submitCard.setPostalCode(savedCard.getPostalCode());
        submitCard.setAccount(savedCard.getAccount());

        return submitCard;
    }

    public String deletePayment(Integer accountId, Integer paymentId) throws Exception {
            String result = "";
            Account account = accountService.searchById(accountId);
            Optional<Payment> checkPayment = paymentDAO.findById(paymentId);
            if(checkPayment.isEmpty()){
                throw new PaymentNotFound("PAYMENT ID NOT FOUND");
            } else {
                paymentDAO.deleteById(paymentId);
                result = "Account Id : " + account.getAccountId() + " Payment Id : " + paymentId +
                        " Payment Removed";
                return result;
            }
    }
}

