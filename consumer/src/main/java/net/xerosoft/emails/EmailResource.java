package net.xerosoft.emails;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("emails")
public class EmailResource {
    @Inject
    EmailService emailService;

    @GET
    public List<Email> list() {
        return Email.listAll();
    }
    
    @DELETE
    public void clear() {
        emailService.deleteAll();
    }
}
