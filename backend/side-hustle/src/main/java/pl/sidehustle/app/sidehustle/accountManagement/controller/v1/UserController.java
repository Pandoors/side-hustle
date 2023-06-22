package pl.sidehustle.app.sidehustle.accountManagement.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sidehustle.app.sidehustle.accountManagement.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
