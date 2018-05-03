package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionReader {

    private static final Logger logger = LoggerFactory.getLogger(ActionReader.class);

    @Autowired
    private ActionFactory actionFactory;

    @Autowired
    private ConsoleReader reader;

    @Autowired
    private ConsoleWriter writer;

    public Action readNext(PaintRunner runner) {
        logger.info("run: waiting for next input...");

        Action action = null;

        while (action == null) {
            writer.writeLine();
            writer.write("enter command:");
            String line = reader.readLine();

            logger.info("run: line={}", line);

            if (line == null || line.isEmpty()) {
                continue;
            }

            String[] args = line.split(" ");

            try {
                Action prototype = actionFactory.findAction(args[0]);
                action = prototype.builder().withRunner(runner).withArguments(args).build();
            } catch(PaintException exc) {
                writer.writeLine();
                writer.writeLine("error: command '" + exc.getCommand() + "' failed. " + exc.getMessage());
            }

        }

        return action;
    }
}

