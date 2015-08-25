package com.lawrence.web.health;

import com.codahale.metrics.health.HealthCheck;
import com.lawrence.web.HelloWorldConfiguration;
import com.lawrence.web.HelloWorldResource;
import io.dropwizard.setup.Environment;

public class MyHealthCheck extends HealthCheck {
    private final String template;

    public MyHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}