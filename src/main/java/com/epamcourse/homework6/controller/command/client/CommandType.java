package com.epamcourse.homework6.controller.command.client;

import com.epamcourse.homework6.controller.command.ActionCommand;
import com.epamcourse.homework6.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK(new AddBookCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    FIND_BY_ID(new FindByIdCommand()),
    FIND_BY_NAME(new FindByNameCommand()),
    FIND_BY_NUMBER_OF_PAGE(new FindByNumberOfPageCommand()),
    FIND_BY_AUTHOR(new FindByAuthorCommand()),
    SORT_BY_NAME(new SortByNameCommand()),
    SORT_BY_NUMBER_OF_PAGE(new SortByNumberOfPageCommand());
    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
