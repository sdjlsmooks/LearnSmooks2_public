package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.XML.X12_850_Parser;
import org.example.XML.X12_850_Interchange;
import org.junit.Test;

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
            String xmlResult = X12_850_Parser.parseEDI(ediInput);
            X12_850_Interchange interchange = X12_850_Parser.parseXML(xmlResult);

            log.info("Starting XML to EDI conversion");
            // Parse XML using Jackson FasterXML
            log.info("Parsing XML using Jackson FasterXML");

            // 7/1/25 - Expand parser - convert to JSON then YAML.
            log.info("Successfully parsed XML using Jackson FasterXML");
            log.info("Parsed interchange XML: {}",interchange.toString());
            String xmlString = X12_850_Parser.toXml(interchange);
            log.info("Parsed interchange XML FROM INTERCHANGE: {}", xmlString);
            log.info("Parsed interchange JSON: {}", X12_850_Parser.toJson(interchange));
            log.info("Parsed interchange YAML: {}", X12_850_Parser.toYaml(interchange));

            // Convert back to EDI String.
            String ediResult = X12_850_Parser.xmlToEDI(xmlResult);
            System.out.printf("Converted to EDI:%s %n", ediResult);

            String ediResultString = X12_850_Parser.xmlToEDI(interchange);
            System.out.printf("Converted to EDI:%s %n", ediResultString);
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
