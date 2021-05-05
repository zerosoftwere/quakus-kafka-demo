package net.xerosoft.emails.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;
import io.vertx.core.AbstractVerticle;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import net.xerosoft.emails.Email;

public class EmailProducerVerticle extends AbstractVerticle {
    private static final Logger log = LoggerFactory.getLogger(EmailProducerVerticle.class);

    private String bootstrapServers;
    private KafkaProducer<String, Email> producer;

    public EmailProducerVerticle(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    @Override
    public void start() throws Exception {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", bootstrapServers);
        config.put("key.serializer", StringSerializer.class.getName());
        config.put("value.serializer", ObjectMapperSerializer.class.getName());
        config.put("acks", "1");

        producer = KafkaProducer.create(vertx, config);
        super.start();
        log.info("connected to kafka");
    }

    public void write(Email email) {
        KafkaProducerRecord<String, Email> record = KafkaProducerRecord.create("emails", email);
        producer.write(record);
    }

    @Override
    public void stop() throws Exception {
        producer.close();
        super.stop();
        log.info("disconnected from kafka");
    }
}