package org.cdgen.docgen;

public interface ConfigParam {
    String name();

    Class<?> type();

    String description();

    String defaultValue();

    String exampleValue();

    boolean hasExampleValue();

    boolean isOptional();

    boolean isString();

    boolean isBean();

    boolean isCollection();
}
