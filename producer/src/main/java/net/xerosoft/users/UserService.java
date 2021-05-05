package net.xerosoft.users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import net.xerosoft.emails.Email;
import net.xerosoft.emails.EmailService;

@ApplicationScoped
public class UserService {
    @Inject
    EmailService emailService;

    public void create(User user) {
        /*
        * create user
        */
        sendVerificationMail(user);
    }

    private void sendVerificationMail(User user) {
        Email email = new Email();
        email.body = String.format("Hi %s, \nPlease click link to activate your email", user.name);
        email.from = "no-reply@xerosoft.net";
        email.to = user.email;

        emailService.send(email);
    }
}
