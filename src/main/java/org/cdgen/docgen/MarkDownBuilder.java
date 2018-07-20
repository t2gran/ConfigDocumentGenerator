package org.cdgen.docgen;


public class MarkDownBuilder implements SimpleDocBuilder {
    private static final char NEW_LINE = '\n';

    private StringBuilder buf = new StringBuilder();

    @Override
    public void modelBegin(ConfigSet model) {
        newLine().heading1(model.title()).newLine().newLine();
        text(model.description()).newLine();
    }

    @Override
    public void modelEnd() { }

    @Override
    public void parametersBegin() { }

    @Override
    public void parametersEnd() { }

    @Override
    public void parameterBegin(ConfigParam param) {
        newLine().heading3(param.name()).newLine().newLine();

        bullet().bold("Java type:").text(" ").code(param.type().getName()).newLine();

        if(param.isOptional()) {
            bullet().bold("Optional").newLine();
            bullet().bold("Default:").text(" ").code(param.defaultValue()).newLine();
        }
        else {
            bullet().bold("Required").newLine();
        }
        if(param.hasExampleValue()) bullet().bold("Example:").text(" ").code(param.exampleValue()).newLine();
        newLine();

        if(param.description() != null) {
            text(param.description()).newLine().newLine();
        }
    }

    @Override
    public void parameterEnd() {
    }

    @Override
    public String toString() {
        return buf.toString();
    }


    /* private methods */

    private MarkDownBuilder text(String text) {
        buf.append(text);
        return this;
    }
    private MarkDownBuilder bullet() {
        buf.append("* ");
        return this;
    }
    private MarkDownBuilder bold(String text) {
        buf.append("**").append(text).append("**");
        return this;
    }
    private MarkDownBuilder code(String text) {
        buf.append("`").append(text).append("`");
        return this;
    }

    private MarkDownBuilder heading1(String text) {
        buf.append("# ").append(text);
        return this;
    }
    private MarkDownBuilder heading2(String text) {
        buf.append("## ").append(text);
        return this;
    }

    private MarkDownBuilder heading3(String text) {
        buf.append("### ").append(text);
        return this;
    }
    private MarkDownBuilder newLine() {
        buf.append(NEW_LINE);
        return this;
    }
}
