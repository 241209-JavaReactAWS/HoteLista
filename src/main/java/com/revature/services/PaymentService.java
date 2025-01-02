package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.PaymentDAO;
import com.revature.exceptions.payment.PaymentNotFound;
import com.revature.models.Account;
import com.revature.models.Payment;
import com.revature.models.Room;
import com.revature.requests.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final AccountService accountService;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO, AccountService accountService) {
        this.paymentDAO = paymentDAO;
        this.accountService = accountService;
    }



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

    public List<PaymentDTO> fetchAllPaymentList(Integer accountId) {
        List<PaymentDTO> resultList = new ArrayList<>();
        List<Payment> listOfAllPaymentMethods = paymentDAO.findAll();
        List<Payment> newList = listOfAllPaymentMethods.stream()
                .filter(item -> item.getAccount().getAccountId()==accountId)
                .toList();
        if(newList.isEmpty()){
            throw new PaymentNotFound("NO LIST OF PAYMENTS FOUND");
        } else {
            for (Payment item: newList){

                PaymentDTO convertClass = new PaymentDTO();
                convertClass.setPaymentId(item.getPaymentId());
                convertClass.setCvv(item.getCvv());
                convertClass.setPostalCode(item.getPostalCode());
                convertClass.setCardHolderName(item.getCardHolderName());
                convertClass.setCardNumber(item.getCardNumber());

                resultList.add(convertClass);
            }
            return resultList;
        }
    }

//    public Payment checkoutPayment(Account account, Integer paymentId) {
//        Payment sumbitedPayment = new Payment();
//        Optional<Payment>retreivePayment=paymentDAO.findById(paymentId);
//        if(retreivePayment.isEmpty()){
//            throw new PaymentNotFound("NO LIST OF PAYMENTS FOUND");
//        }else{
//             sumbitedPayment.setAccount(account);
//             sumbitedPayment.setCvv(retreivePayment.get().getCvv());
//             sumbitedPayment.setCardHolderName(retreivePayment.get().getCardHolderName());
//             sumbitedPayment.setCardNumber(retreivePayment.get().getCardNumber());
//             sumbitedPayment.setPostalCode(retreivePayment.get().getPostalCode());
//             Payment approvedPayment = paymentDAO.save(sumbitedPayment);
//
//            return approvedPayment;
//        }
//    }
}

