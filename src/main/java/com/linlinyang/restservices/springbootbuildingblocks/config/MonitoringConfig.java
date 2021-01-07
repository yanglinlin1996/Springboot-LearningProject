package com.linlinyang.restservices.springbootbuildingblocks.config;

import com.sun.istack.Nullable;
import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfig {

    AppOpticsConfig appopticsConfig = new AppOpticsConfig() {
        @Override
        public String apiToken() {
            return "irTzcTwE710HQlkD61GGrfZptxTIZQPdzUP8lCYVJgAkbGALHvwJSegv4e7L401Ena-VAtU";
        }

        @Override
        @Nullable
        public String get(String k) {
            return null;
        }
    };
    MeterRegistry registry = new AppOpticsMeterRegistry(appopticsConfig, Clock.SYSTEM);

}
