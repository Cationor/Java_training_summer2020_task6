package com.epamcourse.homework6.controller.command.imp;

import com.epamcourse.homework6.controller.command.param.RequestParam;
import com.epamcourse.homework6.model.entity.Book;
import com.epamcourse.homework6.model.exception.ServiceException;
import com.epamcourse.homework6.model.service.BookService;
import com.epamcourse.homework6.controller.command.ActionCommand;

import java.util.HashMap;
import java.util.Map;
import com.epamcourse.homework6.controller.command.param.ResponseParam;

public class FindByIdCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        try {
            Book book = new BookService().findById((int) params.get(RequestParam.ID));
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
            response.put(ResponseParam.RESPONSE_RESULT, book);
        } catch (ServiceException e) {
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        }
        return response;
    }
}
