package com.epamcourse.homework6.controller.command.imp;

import com.epamcourse.homework6.controller.command.param.ResponseParam;
import com.epamcourse.homework6.controller.command.ActionCommand;
import java.util.HashMap;
import java.util.Map;

public class EmptyCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        return response;
    }
}
