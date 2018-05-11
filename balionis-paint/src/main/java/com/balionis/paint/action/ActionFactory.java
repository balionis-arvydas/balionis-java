package com.balionis.paint.action;

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
    private ActionForQuit prototypeForQuit;

    @Autowired
    private ActionForCreate prototypeForCreate;

    @Autowired
    private ActionForPaint prototypeForPaint;

    @Autowired
    private ActionForLine prototypeForLine;

    @Autowired
    private ActionForRect prototypeForRect;

    @Autowired
    private ActionForUndo prototypeForUndo;

    private Map<String, Action> actions = new HashMap<>();

    @PostConstruct
    public void init() {
        actions.put(prototypeForQuit.getCommand(), prototypeForQuit);
        actions.put(prototypeForCreate.getCommand(), prototypeForCreate);
        actions.put(prototypeForPaint.getCommand(), prototypeForPaint);
        actions.put(prototypeForLine.getCommand(), prototypeForLine);
        actions.put(prototypeForRect.getCommand(), prototypeForRect);
        actions.put(prototypeForUndo.getCommand(), prototypeForUndo);

        logger.debug("init: actions={}", actions);
    }

    public Action findAction(String command) throws ActionException {
        Action action = actions.get(command);
        if (action == null) {
            throw new ActionException(command, "unknown command");
        }
        return action;
    }
}

