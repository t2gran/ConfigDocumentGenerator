# ConfigDocumentGenerator
Generate documentation from Java code in a simple way keeping code and doc in the same place.

## Example

Given the following documented configuration interface:

```java
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

```

Then this markdown document is generated: 


> # AlfaServiceConfig
>
> The Alfa Servie configuration
>
> ### enable
>
> * **Java type:** `boolean`
> * **Optional**
> * **Default:** `true`
>
> Turn the Alfa Service on or off
>
>
> ### strategy
>
> * **Java type:** `java.lang.String`
> * **Required**
> * **Example:** `"Advanced"`
>
> The strategy used: `Plain` or `Advanced`

And this JSON example:

```json
{
    "enable" : true,
    "strategy" : "Advanced"
}
```

## Features
* Generate documentation in MarkDown format
* Generate JSON example
* Documentation is done using the `@ConfigDoc` annotation. Each parameter can e described with: 
  * `description` - Describe your parameter
  * `defaultValue`/`optional` - By default all parameters are required, to make it optional you provide a `defaultValue` 
  or tag it with `optinal`
  * `exampleValue` An example is often better than 1000 words.
* Easy to extend to support other formats.
* The framework does not use JavaDocs (which would be better), but this allow the JavaDoc and config 
documentation to differ.  

## Context
This framework aims at projects that define their configuration in a contract using an Java interface. Configuration can 
be injected directly into the code property-by-property using `System.getProperty()` or Spring´s `@Value` - this 
framework does not support such a model. Instead it aims for a model where configuration is defined by *interfaces* - a 
contract between the outside world and the code. Each module in the code define an interface with the needed 
configuration, and then an instance of that interface is injected into the code.

This framework does not provide configuration loading, just a very simple way to generation the configuration 
documentation. You may load properties yourself or use a framework like [Cfg4j](https://github.com/cfg4j).

