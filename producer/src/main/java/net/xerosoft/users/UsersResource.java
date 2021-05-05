package net.xerosoft.users;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("users")
public class UsersResource {
    @Inject
    UserService userService;

    @POST
    public void create(User user) {
        userService.create(user);
    }
}
