package org.example.claimstatus;

import lombok.extern.slf4j.Slf4j;
import org.example.XML.X12_276_ClaimStatus;
import org.example.XML.X12_276_Parser;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Demonstrates parsing an X12 276 Claim Status request into Java objects using Smooks.
 * <p>
 * The program converts the EDI message to XML and then uses Jackson to bind the
 * XML into the {@link X12_276_ClaimStatus} model.
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        byte[] ediInput = Files.readAllBytes(Paths.get(Main.class.getClassLoader()
                .getResource("input276.edi").toURI()));

        // Convert EDI -> XML
        String xml = X12_276_Parser.parseEDI(ediInput);
        log.info("Parsed XML:\n{}", xml);

        // Map XML to Java object
        X12_276_ClaimStatus claimStatus = X12_276_Parser.parseXML(xml);
        log.info("Interchange control number: {}", claimStatus.getInterchangeHeader().getInterchangeControlNumber());

        // Convert back to other formats
        log.info("As JSON: {}", X12_276_Parser.toJson(claimStatus));
        log.info("As YAML: {}", X12_276_Parser.toYaml(claimStatus));
    }
}
