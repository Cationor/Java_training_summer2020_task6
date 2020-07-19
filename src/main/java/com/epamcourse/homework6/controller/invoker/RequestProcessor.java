package com.epamcourse.homework6.controller.invoker;

import com.epamcourse.homework6.controller.command.provider.ActionProvider;
import com.epamcourse.homework6.controller.command.ActionCommand;

import java.util.Map;

public class RequestProcessor {
    public Map<String, Object> doRequest(String action, Map<String, Object> params) {
        ActionCommand actionCommand = ActionProvider.defineAction(action);
        return actionCommand.execute(params);
    }
}
