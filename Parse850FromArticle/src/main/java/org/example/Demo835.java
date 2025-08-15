package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.XML.X12_835_Parser;

import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class Demo835 {
    public static void main(String[] args) throws Exception {
        // Load an 835 EDI message from resources
        byte[] ediBytes = Files.readAllBytes(
                Paths.get(Demo835.class.getClassLoader().getResource("input835.edi").toURI())
        );

        // EDI -> XML
        String xml = X12_835_Parser.parseEDI(ediBytes);
        System.out.println("Parsed 835 XML:\n" + xml);

        // XML -> EDI
        String edi = X12_835_Parser.xmlToEDI(xml);
        System.out.println("Re-serialized 835 EDI:\n" + edi);
    }
}
