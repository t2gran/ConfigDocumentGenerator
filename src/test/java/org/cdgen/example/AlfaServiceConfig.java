package org.cdgen.example;

import org.cdgen.annotations.ConfigDoc;

@ConfigDoc(description = "The Alfa Servie configuration")
public interface AlfaServiceConfig {
    @ConfigDoc(
            description = "Turn the Alfa Service on or off",
            defaultValue = "true"
    )
    boolean enable();


    @ConfigDoc(
            description = "The strategy used: `Plain` or `Advanced`",
            exampleValue = "Advanced"
    )
    String strategy();
}
