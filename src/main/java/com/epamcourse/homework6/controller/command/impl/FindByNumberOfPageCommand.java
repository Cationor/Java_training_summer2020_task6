package com.epamcourse.homework6.controller.command.impl;

import com.epamcourse.homework6.controller.command.param.RequestParam;
import com.epamcourse.homework6.controller.command.param.ResponseParam;
import com.epamcourse.homework6.model.entity.Book;
import com.epamcourse.homework6.model.service.BookService;
import com.epamcourse.homework6.controller.command.ActionCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FindByNumberOfPageCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        Stream<Book> book = new BookService().findByNumberOfPage((int) params.get(RequestParam.NUMBER_OF_PAGE));
        response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
        response.put(ResponseParam.RESPONSE_RESULT, book);
        return response;
    }
}