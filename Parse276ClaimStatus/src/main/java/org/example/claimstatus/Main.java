package org.example.claimstatus;

import org.smooks.Smooks;
import org.smooks.io.sink.StringSink;
import org.smooks.io.source.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Example program that parses an X12 276 Claim Status request using Smooks and
 * prints out the segment values.  The Smooks configuration relies on the
 * claimstatus276.xsd DFDL schema.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        byte[] ediInput = Files.readAllBytes(Paths.get(Main.class.getClassLoader()
                .getResource("input276.edi").toURI()));

        // Convert the EDI message to XML using Smooks
        String xml = parseEDI(ediInput);

        // Display the resulting XML
        System.out.println("Parsed XML:\n" + xml);

        // Print each segment and its values
        printSegments(xml);
    }

    /**
     * Use Smooks to convert the EDI message into XML using the configuration in
     * parse-config.xml.
     */
    private static String parseEDI(byte[] ediInput) throws IOException {
        StringSink result;
        try (Smooks smooks = new Smooks("parse-config.xml")) {
            result = new StringSink();
            smooks.filterSource(new StreamSource<>(new ByteArrayInputStream(ediInput)), result);
        }
        return result.getResult();
    }

    /**
     * Parse the XML produced by Smooks and print each segment element and the
     * values of its child elements.
     */
    private static void printSegments(String xml) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new ByteArrayInputStream(xml.getBytes()));

        Element root = doc.getDocumentElement();
        NodeList segments = root.getChildNodes();
        for (int i = 0; i < segments.getLength(); i++) {
            Node seg = segments.item(i);
            if (seg.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            System.out.println("Segment: " + seg.getNodeName());
            NodeList fields = seg.getChildNodes();
            for (int j = 0; j < fields.getLength(); j++) {
                Node f = fields.item(j);
                if (f.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                System.out.println("  " + f.getNodeName() + " = " + f.getTextContent());
            }
        }
    }
}
