package ru.spring.mvc.app.repository.command;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginIsBusyCommand implements RegisterCommand {

    @Override
    public ModelAndView execute() {
        return null;
    }
}
