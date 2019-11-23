package io.egrow.frontend.scheduler_service.configuration.logger;

import biz.paluch.logging.gelf.jul.GelfLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * This class add the GelfLogHandler to the Logger after Spring is started.
 */
@Component
public class GelfLogHandlerInitialization implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${gelflog.isEnabled}")
    boolean isEnabled;

    @Autowired
    GelfLogHandler gelfLogHandler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        if (this.isEnabled) {
            java.util.logging.Logger rootLogger = java.util.logging.Logger.getGlobal().getParent();
            rootLogger.addHandler(this.gelfLogHandler);
        }
    }

}
