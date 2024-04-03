package se.miun.dt170g.projektdt170g.API;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") // Set the base URI for all JAX-RS resources
public class RestApplication extends Application {
    // No need to override methods for a basic setup
    // maybe a description on how to use the API could be printed here?
}
