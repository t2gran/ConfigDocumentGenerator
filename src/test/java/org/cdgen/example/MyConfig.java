package org.cdgen.example;

import org.cdgen.annotations.ConfigDoc;

import java.util.List;
import java.util.Map;

@ConfigDoc(description = "MyConfig is used to document bla bla bla.")
public interface MyConfig {
    @ConfigDoc(
            description = "Bla *bla* _bla_, ref {nested feature is enabled | MyConfigNestedFeature#enabled}." +
                    "" +
                    "[Licence|http://licence.org/apache/2.0]" +
                    "More ...",
            defaultValue = "true"
    )
    boolean enable();


    @ConfigDoc(description = "My int description", defaultValue = "5")
    int myInt();

    @ConfigDoc(description = "The URL for the external service to connect to.", defaultValue = "https://ext.service.ol/service/alfa")
    String externalServiceUrl();

    @ConfigDoc(description = "List of real numbers", defaultValue = "[ 1.0, 30.0 ]")
    List<Double> listOfDoubles();

    @ConfigDoc(description = "Each feature is grouped by a color", exampleValue = "{ 'F1':'red', 'F2':'blue' }")
    Map<String, MyColor> featureColorTag();


    @ConfigDoc(description = "Required feature")
    MyConfigNestedFeature requiredFeature();

    @ConfigDoc(description = "Nested feature", optional = true)
    MyConfigNestedFeature optionalFeature();

}

