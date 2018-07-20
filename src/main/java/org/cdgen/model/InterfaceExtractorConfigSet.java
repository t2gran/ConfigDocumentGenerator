package org.cdgen.model;


import org.cdgen.docgen.ConfigSet;

import java.lang.reflect.Method;
import java.util.Iterator;

import static org.cdgen.model.ConfigDocAnnotationHelper.resolveAnnotationStringValue;

public class InterfaceExtractorConfigSet implements ConfigSet {

    private Class configClass;

    public InterfaceExtractorConfigSet(Class<?> configClass) {
        this.configClass = configClass;
    }

    @Override
    public String description() {
        return resolveAnnotationStringValue(configClass, "description");
    }

    @Override
    public Class<?> type() {
        return configClass;
    }

    public Iterable<MethodExtractorConfigParam> parameters() {
        return () -> new Iterator<MethodExtractorConfigParam>() {
            private Method[] methods = configClass.getDeclaredMethods();
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < methods.length;
            }

            @Override
            public MethodExtractorConfigParam next() {
                return new MethodExtractorConfigParam(methods[i++]);
            }
        };
    }

    @Override
    public String title() {
        return configClass.getSimpleName();
    }
}
