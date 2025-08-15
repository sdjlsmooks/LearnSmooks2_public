package org.example;

import org.example.XML.X12_835_Parser;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class X12_835_ParserTest {

    @Test
    public void testParseAndSerialize835() throws Exception {
        byte[] ediBytes = Files.readAllBytes(
                Paths.get(X12_835_ParserTest.class.getClassLoader().getResource("input835.edi").toURI())
        );
        String xml = X12_835_Parser.parseEDI(ediBytes);
        assertNotNull(xml);
        System.out.println("XML:\n" + xml);
        // Validate the parsed XML has required structure
        assertTrue("XML should contain interchange-header", xml.contains("interchange-header"));
        assertTrue("XML should contain group-header", xml.contains("group-header"));

        String edi = X12_835_Parser.xmlToEDI(xml);
        assertNotNull(edi);
        System.out.println("EDI:\n" + edi);
        assertTrue("EDI should contain interchange-header", edi.contains("ISA"));
        assertTrue("EDI should contain group-header", edi.contains("GS"));

    }
}
