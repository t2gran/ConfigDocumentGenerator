package org.cdgen.example;

import org.cdgen.annotations.ConfigDoc;

@ConfigDoc(description = "Describe this type of config element")
interface MyConfigNestedFeature {

    @ConfigDoc(description = "All features must have a name.", exampleValue = "Ole")
    String name();

    @ConfigDoc(description = "Should be required.", exampleValue = "false")
    boolean enabled();
}
