package com.epamcourse.homework6.controller.command.imp;

import com.epamcourse.homework6.controller.command.param.RequestParam;
import com.epamcourse.homework6.controller.command.param.ResponseParam;
import com.epamcourse.homework6.model.entity.Book;
import com.epamcourse.homework6.model.exception.ServiceException;
import com.epamcourse.homework6.model.service.BookService;
import com.epamcourse.homework6.controller.command.ActionCommand;

import java.util.HashMap;
import java.util.Map;

public class RemoveBookCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        Book book = new Book((int) params.get(RequestParam.ID), (String) params.get(RequestParam.BOOK_NAME),
                (int) params.get(RequestParam.NUMBER_OF_PAGE), (String[]) params.get(RequestParam.AUTHOR));
        try {
            new BookService().removeBook(book);
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
        } catch (ServiceException e) {
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        }
        return response;
    }
}
