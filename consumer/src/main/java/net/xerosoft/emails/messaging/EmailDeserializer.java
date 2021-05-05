package net.xerosoft.emails.messaging;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import net.xerosoft.emails.Email;

public class EmailDeserializer extends ObjectMapperDeserializer<Email> {
    public EmailDeserializer() {
        super(Email.class);
    }
}
