package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.PostConstruct;

@Component
public class ActionFactory {

    private static final Logger logger = LoggerFactory.getLogger(ActionFactory.class);

    @Autowired
    private QuitAction quitPrototype;

    private Map<String, Action> actions = new HashMap<>();

    @PostConstruct
    public void init() {
        actions.put(quitPrototype.getCommand(), quitPrototype);

        logger.debug("init: actions={}", actions);
    }

    public Action findAction(String command) throws PaintException {
        Action action = actions.get(command);
        if (action == null) {
            throw new PaintException(command, "unknown command");
        }
        return action;
    }
}

