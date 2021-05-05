package net.xerosoft.emails.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import net.xerosoft.emails.Email;

@ApplicationScoped
public class EmailProducer {
    private String deploymentID;
    private EmailProducerVerticle verticle;

    @Inject
    Vertx vertx;
    
    @ConfigProperty(name = "kafka.bootstrap.servers")
    String bootstrapServers;

    public void write(Email email) {
        verticle.write(email);
    }

    void onStart(@Observes StartupEvent event) {
        verticle = new EmailProducerVerticle(bootstrapServers);
        vertx.deployVerticle(verticle, handler -> {
            deploymentID = handler.result();
        });
    }

    void onStop(@Observes ShutdownEvent event) {
        vertx.undeploy(deploymentID);
    }
}
