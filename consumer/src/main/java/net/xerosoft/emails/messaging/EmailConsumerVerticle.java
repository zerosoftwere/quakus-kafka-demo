package net.xerosoft.emails.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.kafka.common.serialization.StringDeserializer;

import io.vertx.core.AbstractVerticle;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import net.xerosoft.emails.Email;

public class EmailConsumerVerticle extends AbstractVerticle {

    private final String bootstrapServers;
    private final Consumer<Email> handler;

    private KafkaConsumer<String, Email> consumer;
    
    public EmailConsumerVerticle(String bootstrapServers, Consumer<Email> handler) {
        this.bootstrapServers = bootstrapServers;
        this.handler = handler;
    }

    @Override
    public void start() throws Exception {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", bootstrapServers);
        config.put("key.deserializer", StringDeserializer.class.getName());
        config.put("value.deserializer", EmailDeserializer.class.getName());
        config.put("group.id", "email_consumer_group");
        config.put("enable.auto.commit", "false");

        consumer = KafkaConsumer.create(vertx, config);
        consumer.handler(record -> {
            handler.accept(record.value());
            consumer.commit();
        });
        consumer.subscribe("emails");
        
        super.start();
    }

    @Override
    public void stop() throws Exception {
        consumer.close();
        super.stop();
    }
}
