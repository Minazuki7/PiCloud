package com.esprit.edusched.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    @Value("AC1fa522d398fc2ec727cdeaa69a882d62")
    private String accountSid;

    @Value("dde7b708720db698f3b0784f91f8e90b")
    private String authToken;

    @Value("+13342343615")
    private String twilioPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                messageBody
        ).create();
        System.out.println("SMS sent: " + message.getSid());
    }
}




