package com.revature.requests;


import com.revature.models.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
        private Integer paymentId;
        private String cardHolderName;
        private String cardNumber;
        private Integer cvv;
        private String postalCode;

        public PaymentDTO(Payment payment){
                this.paymentId = payment.getPaymentId();
                this.cardHolderName = payment.getCardHolderName();
                this.cardNumber = payment.getCardNumber();
                this.cvv = payment.getCvv();
                this.postalCode = payment.getPostalCode();
        }
}
