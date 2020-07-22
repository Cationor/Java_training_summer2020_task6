package com.epamcourse.homework6.controller.command.provider;

import com.epamcourse.homework6.controller.command.ActionCommand;
import com.epamcourse.homework6.controller.command.user.CommandType;
import com.epamcourse.homework6.controller.command.imp.EmptyCommand;

public class ActionProvider {
    private ActionProvider() {
    }

    public static ActionCommand defineAction(String action) {
        ActionCommand command;
        try {
            command = CommandType.valueOf(action.toUpperCase()).getCurrentCommand();
        } catch (IllegalArgumentException e) {
            command = new EmptyCommand();
        }
        return command;
    }
}
