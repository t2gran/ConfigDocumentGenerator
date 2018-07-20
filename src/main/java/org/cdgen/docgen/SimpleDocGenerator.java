package org.cdgen.docgen;

import org.cdgen.model.InterfaceExtractorConfigSet;
import org.cdgen.model.MethodExtractorConfigParam;

import java.util.ArrayList;
import java.util.List;

public class SimpleDocGenerator {

    private final SimpleDocBuilder doc;
    private final boolean recursiveMode;
    private final List<Class<?>> modules = new ArrayList<>();
    private int moduleIndex = 0;

    private SimpleDocGenerator(Class<?> rootConfigInterface, SimpleDocBuilder doc, boolean recursiveMode) {
        this.doc = doc;
        this.recursiveMode = recursiveMode;
        modules.add(rootConfigInterface);
    }

    public static String generateFullJsonExampleDocument(Class<?> configInterface) {
        return generateDocument(configInterface, new JsonExampleBuilder(), true);
    }

    public static String generateMarkdownDocument(Class<?> configInterface) {
        return generateDocument(configInterface, new MarkDownBuilder(), false);
    }

    private static String generateDocument(Class<?> configInterface, SimpleDocBuilder builder, boolean recursiveMode) {
        return new SimpleDocGenerator(configInterface, builder, recursiveMode).generateDocument();
    }

    private String generateDocument() {
        while (hasMoreModels()) {
            render(nextModel());
        }
        return doc.toString();
    }

    private boolean hasMoreModels() {
        return moduleIndex < modules.size();
    }

    private InterfaceExtractorConfigSet nextModel() {
        return new InterfaceExtractorConfigSet(modules.get(moduleIndex++));
    }

    private void render(InterfaceExtractorConfigSet model) {
        doc.modelBegin(model);

        doc.parametersBegin();
        for (MethodExtractorConfigParam param : model.parameters()) {
            render(param);
        }
        doc.parametersEnd();
        doc.modelEnd();
    }

    private void handleSubModule(MethodExtractorConfigParam param) {
        if(!param.isBean()) return;

        if(recursiveMode) {
            render(new InterfaceExtractorConfigSet(param.type()));
        }
        else {
            if(!modules.contains(param.type())) {
                modules.add(param.type());
            }
        }
    }

    private void render(MethodExtractorConfigParam param) {
        //param.verify();
        doc.parameterBegin(param);
        handleSubModule(param);
        doc.parameterEnd();
    }
}
