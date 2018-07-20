package org.cdgen.model;

import org.cdgen.docgen.ConfigParam;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.cdgen.model.ConfigDocAnnotationHelper.resolveAnnotationBooleanValue;
import static org.cdgen.model.ConfigDocAnnotationHelper.resolveAnnotationStringValue;

public class MethodExtractorConfigParam implements ConfigParam {
    private Method method;
    private static final List<Class> COLLECTION_TYPES = Arrays.asList(List.class, Map.class);
    private final String exampleValue;
    private final String defaultValue;

    MethodExtractorConfigParam(Method method) {
        this.method = method;
        this.exampleValue = quote(resolveExampleValue());
        this.defaultValue = quote(resolveDefaultValue());
    }

    @Override
    public String name() {
        return method.getName();
    }

    @Override
    public Class<?> type() {
        return method.getReturnType();
    }

    @Override
    public String description() {
        return resolveAnnotationStringValue(method, "description");
    }

    @Override
    public String defaultValue() {
        return defaultValue;
    }


    @Override
    public String exampleValue() {
        return exampleValue;
    }

    @Override
    public boolean hasExampleValue() {
        return exampleValue != null;
    }

    @Override
    public boolean isOptional() {
        boolean optional = resolveAnnotationBooleanValue(method, "optional");
        return optional || defaultValue() != null;
    }
    @Override
    public boolean isString() {
        return String.class.equals(type());
    }

    @Override
    public boolean isBean() {
        String type = type().getName();
        return type.contains(".") && !type.startsWith("java.");
    }

    @Override
    public boolean isCollection() {
        return  COLLECTION_TYPES.contains(type());
    }


    /* private methods */

    private String quote(String value) {
        return value != null && isString() && value.charAt(0) != '\"' ? "\"" + value + "\"" : value;
    }

    private String resolveDefaultValue() {
        return resolveAnnotationStringValue(method, "defaultValue");
    }

    private String resolveExampleValue() {
        String exampleValue =  resolveAnnotationStringValue(method, "exampleValue");
        if(exampleValue == null) return null;
        if(isBean() || isCollection()) return exampleValue.replace('\'', '\"');
        return exampleValue;
    }
}
