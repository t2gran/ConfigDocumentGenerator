package org.cdgen.example;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.cdgen.docgen.SimpleDocGenerator.generateFullJsonExampleDocument;
import static org.cdgen.docgen.SimpleDocGenerator.generateMarkdownDocument;

public class MainExample {

    private static final File OUTPUT_FILE = new File("target/docs", "config.md");

    public static void main(String... args) throws IOException {

//        String markdown = generateMarkdownDocument(MyConfig.class);
//        String json = generateFullJsonExampleDocument(MyConfig.class);
        String markdown = generateMarkdownDocument(AlfaServiceConfig.class);
        String json = generateFullJsonExampleDocument(AlfaServiceConfig.class);

        printToFile("" +
                         markdown +
                        "\n\n# Configuration example in JSON format\n```json\n" +
                        json +
                "\n```\n",

                OUTPUT_FILE
        );
    }


    @SuppressWarnings("SameParameterValue")
    private static void printToFile(String text, File file) throws IOException {
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();

        FileWriter out = new FileWriter(file);

        out.write(text);

        out.flush();
        out.close();
    }
}
