package org.cdgen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ConfigDoc {
    public static final String NOT_SET = "<NOT USED>";

    String description();
    /**
     * A config element is optional is this attribute is set to <code>true</code> OR a {@link #defaultValue} is provided.
     */
    boolean optional() default false;
    String defaultValue() default NOT_SET;
    String exampleValue() default NOT_SET;
}
