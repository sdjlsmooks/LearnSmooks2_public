package org.example.XML;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.extern.slf4j.Slf4j;
import org.smooks.Smooks;
import org.smooks.io.sink.StringSink;
import org.smooks.io.source.StreamSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Utility class for parsing XML using Jackson's XML mapper.
 */
@Slf4j
public class XML850Parser {

    private static final XmlMapper xmlMapper = new XmlMapper();
    private static final JsonMapper jsonMapper = new JsonMapper();
    private static final YAMLMapper yamlMapper = new YAMLMapper();

    static {
        // Configure the XML mapper
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static String parseX12850EDIString(String ediString) throws IOException, SAXException {
        return parseEDI(ediString.getBytes());
    }

    public static String parseEDI(byte[] ediInput) throws IOException, SAXException {
        Smooks ediToXml = new Smooks("parse-config.xml");
        log.debug("Loaded EDI input file with {} bytes", ediInput.length);

        StringSink result = new StringSink();
        ediToXml.filterSource(new StreamSource(new ByteArrayInputStream(ediInput)), result);
        String xmlResult = result.getResult();
        log.info("Successfully converted EDI to XML");
        log.debug("XML result: {}", xmlResult);
        System.out.printf("Converted to XML:%s %n", xmlResult);
        return xmlResult;
    }

    /**
     * Parse XML string into X850Interchange object.
     *
     * @param xml The XML string to parse
     * @return The parsed X850Interchange object
     * @throws IOException If parsing fails
     */
    public static X850Interchange parseX850(String xml) throws IOException {
        try {
            log.debug("Parsing XML to X850Interchange: {}", xml);
            X850Interchange result = xmlMapper.readValue(xml, X850Interchange.class);
            log.debug("Successfully parsed XML to X850Interchange");
            return result;
        } catch (Exception e) {
            log.error("Error parsing XML to X850Interchange: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Convert X850Interchange object to XML string.
     *
     * @param interchange The X850Interchange object to convert
     * @return The XML string representation
     * @throws IOException If conversion fails
     */
    public static String toXml(X850Interchange interchange) throws IOException {
        try {
            log.debug("Converting X850Interchange to XML");
            String result = xmlMapper.writeValueAsString(interchange);
            log.debug("Successfully converted X850Interchange to XML: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error converting X850Interchange to XML: {}", e.getMessage(), e);
            throw e;
        }
    }


    /**
     * Convert X850Interchange object to JSON string.
     *
     * @param interchange The X850Interchange object to convert
     * @return The XML string representation
     * @throws IOException If conversion fails
     */
    public static String toJson(X850Interchange interchange) throws IOException {
        try {
            log.debug("Converting X850Interchange to JSON");
            String result = jsonMapper.writeValueAsString(interchange);
            log.debug("Successfully converted X850Interchange to JSON: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error converting X850Interchange to JSON: {}", e.getMessage(), e);
            throw e;
        }
    }

    public static String toYaml(X850Interchange interchange) throws IOException {
        String result = null;
        try {
            log.debug("Converting X850Interchange to YAML");
            result = yamlMapper.writeValueAsString(interchange);
            log.debug("Successfully converted X850Interchange to YAML: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error converting X850Interchange to YAML: {}", e.getMessage(), e);
        }
        return result;
    }
}