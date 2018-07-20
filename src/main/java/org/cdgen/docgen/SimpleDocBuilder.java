package org.cdgen.docgen;

public interface SimpleDocBuilder {
    void modelBegin(ConfigSet model);
    void modelEnd();
    void parametersBegin();
    void parametersEnd();
    void parameterBegin(ConfigParam param);
    void parameterEnd();

    @Override
    String toString();
}