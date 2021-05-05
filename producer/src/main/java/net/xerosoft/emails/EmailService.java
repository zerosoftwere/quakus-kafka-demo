package net.xerosoft.emails;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import net.xerosoft.emails.messaging.EmailProducer;

@ApplicationScoped
public class EmailService {
    @Inject
    EmailProducer producer;

    public void send(Email email) {
        producer.write(email);
    }
}
