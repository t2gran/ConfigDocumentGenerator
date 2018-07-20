package org.cdgen.docgen;


public class JsonExampleBuilder implements SimpleDocBuilder {
    private static final char NEW_LINE = '\n';

    private StringBuilder buf = new StringBuilder();
    private int indentLevel = 0;
    private boolean lineTerminated = true;



    @Override
    public void modelBegin(ConfigSet model) {
        text("{").newLine();
        ++indentLevel;
    }

    @Override
    public void modelEnd() {
        terminateLineWithoutComma();
        --indentLevel;
        indent().text("}");
        terminateLineLater();
    }

    @Override
    public void parametersBegin() { }

    @Override
    public void parametersEnd() { }

    @Override
    public void parameterBegin(ConfigParam param) {
        terminateLineWithComma();

        indent().identifier(param.name()).text(" : ");

        if(!param.isBean()) {
            String value = param.hasExampleValue() ? param.exampleValue() : param.defaultValue();

            if (value != null) {
                text(value);
                terminateLineLater();
            }
        }
    }

    @Override
    public void parameterEnd() { }

    @Override
    public String toString() {
        return buf.toString();
    }


    /* private methods */

    private void terminateLineLater() {
        this.lineTerminated = false;
    }

    private void terminateLineWithComma() {
        if(!lineTerminated) text(",").newLine();
    }

    private void terminateLineWithoutComma() {
        if(!lineTerminated) newLine();
    }

    private JsonExampleBuilder indent() {
        for (int i = 0; i < indentLevel; ++i) buf.append("    ");
        return this;
    }

    private JsonExampleBuilder identifier(String text) {
        buf.append("\"").append(text).append("\"");
        return this;
    }

    private JsonExampleBuilder text(String text) {
        buf.append(text);
        return this;
    }

    private void newLine() {
        buf.append(NEW_LINE);
        this.lineTerminated = true;
    }
}
