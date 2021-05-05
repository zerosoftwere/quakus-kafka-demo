package net.xerosoft.emails.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import net.xerosoft.emails.Email;
import net.xerosoft.emails.EmailService;

@ApplicationScoped
public class EmailConsumer {
    private String deploymentID;

    @Inject
    Vertx vertx;

    @Inject
    EmailService emailService;

    @ConfigProperty(name = "kafka.bootstrap.servers")
    String bootstrapServers;

    void onStart(@Observes StartupEvent event) {
        EmailConsumerVerticle verticle = new EmailConsumerVerticle(bootstrapServers, this::process);
        vertx.deployVerticle(verticle, handler -> {
            deploymentID = handler.result();
        });
    }

    void onStop(@Observes ShutdownEvent event) {
        vertx.undeploy(deploymentID);
    }

    private void process(Email email) {
        vertx.executeBlocking(handler -> {
            emailService.create(email);
            handler.complete();
        }, handler -> {
            if (handler.failed()) handler.cause().printStackTrace();
        });
    }
}
