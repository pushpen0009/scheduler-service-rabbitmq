package io.egrow.frontend.scheduler_service.configuration.logger;

import biz.paluch.logging.gelf.jul.GelfLogHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;

@Configuration
public class GelfLoggerConfiguration {

    @Value("${gelflog.host}")
    String host;

    @Value("${gelflog.port}")
    int port;

    @Value("${gelflog.version}")
    String version;

    @Value("${gelflog.facility}")
    String facility;

    @Value("${gelflog.extractStackTrace}")
    String extractStackTrace;

    @Value("${gelflog.filterStackTrace}")
    boolean filterStackTrace;

    @Value("${gelflog.timestampPattern}")
    String timestampPattern;

    @Value("${gelflog.maximumMessageSize}")
    int maximumMessageSize;

    @Value("${gelflog.level}")
    String level;


    @Bean
    GelfLogHandler getGelfLogHandler() {

        GelfLogHandler handler = new GelfLogHandler();

        handler.setHost(this.host);
        handler.setPort(this.port);
        handler.setVersion(this.version);
        handler.setFacility(this.facility);
        handler.setExtractStackTrace(this.extractStackTrace);
        handler.setFilterStackTrace(this.filterStackTrace);
        handler.setTimestampPattern(this.timestampPattern);
        handler.setMaximumMessageSize(this.maximumMessageSize);
        handler.setLevel(Level.parse(this.level));

        return handler;
    }

}
