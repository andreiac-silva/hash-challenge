package com.hash.challenge.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "database")
public interface DatabaseConfig {

    @ConfigProperty(name = "name")
    String name();

    @ConfigProperty(name = "product.collection")
    String productCollection();
}
