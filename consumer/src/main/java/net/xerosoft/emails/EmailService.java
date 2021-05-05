package net.xerosoft.emails;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import net.xerosoft.socket.SocketService;

@ApplicationScoped
public class EmailService {

    @Inject
    SocketService socketService;

    @Transactional
    public void create(Email email) {
        email.persist();
        socketService.broadcast(email);
    }

    @Transactional 
    public void deleteAll() {
        Email.deleteAll();
    }
}
