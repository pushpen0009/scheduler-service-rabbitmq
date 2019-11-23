package io.egrow.frontend.scheduler_service.configuration.logger;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.slf4j.MDC;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides a Logger instance for the provides Class. Each logging will start in a new thread and the context is attached.
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GlobalLogger {

    Logger classLogger;

    public GlobalLogger(Class tClass) {

        this.classLogger = Logger.getLogger(tClass.getName());
    }

    public void log(@NonNull Level level, @NonNull String message) {

        log(level, message, null);
    }

    public void log(@NonNull Level level, @NonNull String message, Throwable throwable) {

        final Map<String, String> map = MDC.getCopyOfContextMap();

        new Thread(() -> {
            try {
                // Add Context information
                ObjectMapper objectMapper = new ObjectMapper();
                String context = objectMapper.writeValueAsString(map);
                String finalMessage = message + "\nContext: " + context;
                this.classLogger.log(level, finalMessage, throwable);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }).start();
    }
}

