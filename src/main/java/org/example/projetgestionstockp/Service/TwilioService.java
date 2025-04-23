package org.example.projetgestionstockp.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("AC8b5aaea2adafd9c9203cf5a39443e852")
    private String accountSid;

    @Value("a1d9083d9077fb3424e84c03c2a5117e")
    private String authToken;

    @Value("+19786483224")
    private String fromPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        // Initialiser Twilio avec ton Account SID et Auth Token
        Twilio.init(accountSid, authToken);

        // Envoyer le message
        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber), // Numéro du destinataire
                        new PhoneNumber(fromPhoneNumber), // Ton numéro Twilio
                        messageBody)                    // Contenu du message
                .create();
        System.out.println("Message envoyé avec SID: " + message.getSid());
    }
}
