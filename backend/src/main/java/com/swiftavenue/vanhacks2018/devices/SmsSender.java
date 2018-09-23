package com.swiftavenue.vanhacks2018.devices;

import javax.annotation.PostConstruct;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {
    @Value("${twilio.auth.sid}")
    private String sid;

    @Value("${twilio.auth.token}")
    private String token;

    @Value("${twilio.sender.no}")
    private String sender;

    @PostConstruct
    public void init() {
        Twilio.init(sid, token);
    }

    public void sendSms(String message, String to) {
        Message.creator(new PhoneNumber(to), // to
                        new PhoneNumber(sender), // from
                        message).create();
    }
}
