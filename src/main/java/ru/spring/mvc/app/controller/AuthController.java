package ru.spring.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.mvc.app.repository.command.RegisterCommand;
import ru.spring.mvc.app.repository.command.StatusRegister;
import ru.spring.mvc.app.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final Map<StatusRegister, RegisterCommand> commandMap;

    @Autowired
    public AuthController(UserService userService,
                          @Qualifier("commandRegistration") Map<StatusRegister, RegisterCommand> commandMap) {

        this.userService = userService;
        this.commandMap = commandMap;
    }
}
