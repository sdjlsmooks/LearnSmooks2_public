package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.XML.XML850Parser;
import org.example.XML.X850Interchange;
import org.junit.Test;
import org.smooks.FilterSettings;
import org.smooks.Smooks;
import org.smooks.io.sink.StringSink;
import org.smooks.io.source.StreamSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@Slf4j
public class Main {

    @Test
    public void parseBlog850() throws Exception {
        log.info("Starting EDI to XML conversion");
        try {
            final byte[] ediInput = Files.readAllBytes(Paths.get(Main.class.getClassLoader().getResource("inputmessage.edi").toURI()));
            String ediString = Files.readString(Paths.get(Main.class.getClassLoader().getResource("inputmessage.edi").toURI()));

            // Convert EDI -> XML
            String xmlResult = XML850Parser.parseEDI(ediInput);
            X850Interchange interchange = XML850Parser.parseX850(xmlResult);

            log.info("Starting XML to EDI conversion");
            // Parse XML using Jackson FasterXML
            log.info("Parsing XML using Jackson FasterXML");

            // 7/1/25 - Expand parser - convert to JSON then YAML.
            log.info("Successfully parsed XML using Jackson FasterXML");
            log.debug("Parsed interchange: {}", interchange);
            log.debug("Parsed interchange JSON: {}", XML850Parser.toJson(interchange));
            log.debug("Parsed interchange YAML: {}", XML850Parser.toYaml(interchange));

            // Convert XML -> EDI
            Smooks xmlToEdi = new Smooks("serialize-config.xml");

            xmlToEdi.setFilterSettings(FilterSettings.newSaxNgSettings().setDefaultSerializationOn(false));
            final byte[] xmlInput = xmlResult.getBytes();
            log.debug("Prepared XML input with {} bytes", xmlInput.length);

            StringSink ediResult = new StringSink();
            xmlToEdi.filterSource(new StreamSource(new ByteArrayInputStream(xmlInput)), ediResult);
            log.info("Successfully converted XML back to EDI");
            log.debug("EDI result: {}", ediResult.getResult());
            System.out.printf("Converted to EDI:%s %n", ediResult.getResult());
        } catch (Exception e) {
            log.error("Error parsing XML using Jackson FasterXML: {}", e.getMessage(), e);
        }

    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Main aMain = new Main();
        try {
            aMain.parseBlog850();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
