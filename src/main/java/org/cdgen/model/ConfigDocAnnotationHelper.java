package org.cdgen.model;

import org.cdgen.annotations.ConfigDoc;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

class ConfigDocAnnotationHelper {

    static Boolean resolveAnnotationBooleanValue(AnnotatedElement element, String attributeName) {
        return (Boolean) resolveAnnotationValue(element, attributeName);
    }

    static String resolveAnnotationStringValue(AnnotatedElement element, String attributeName) {
        String value = (String) resolveAnnotationValue(element, attributeName);
        return ConfigDoc.NOT_SET.equals(value) ? null : value;
    }

    private static Object resolveAnnotationValue(AnnotatedElement element, String attributeName) {
        try {
            if(!element.isAnnotationPresent(ConfigDoc.class)) {
                throw new IllegalStateException("The the '" + element + "' should be annotated with @ConfigDoc.");
            }
            Annotation annotation = element.getAnnotation(ConfigDoc.class);
            return annotation.annotationType().getMethod(attributeName).invoke(annotation);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
