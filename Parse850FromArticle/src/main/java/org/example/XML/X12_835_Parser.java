package org.example.XML;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.smooks.FilterSettings;
import org.smooks.Smooks;
import org.smooks.io.sink.StringSink;
import org.smooks.io.source.StreamSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * X12_835_Parser provides EDI<->XML conversion for HIPAA 5010 X12 835
 * using Smooks 2.x and the 835_mapping.dfdl.xsd mapping.
 */
@Slf4j
public class X12_835_Parser {

    private static final XmlMapper xmlMapper = new XmlMapper();

    static {
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Parse an X12 835 EDI string into XML using Smooks and the 835 DFDL mapping.
     */
    public static String parseEDI(String edi) throws IOException, SAXException {
        return parseEDI(edi.getBytes());
    }

    /**
     * Parse an X12 835 EDI byte[] into XML using Smooks and the 835 DFDL mapping.
     */
    public static String parseEDI(byte[] ediBytes) throws IOException, SAXException {
        try (Smooks smooks = new Smooks("parse-835-config.xml")) {
            log.debug("Parsing 835 EDI input ({} bytes)...", ediBytes.length);
            StringSink result = new StringSink();
            smooks.filterSource(new StreamSource<>(new ByteArrayInputStream(ediBytes)), result);
            String xml = result.getResult();
            log.info("Successfully converted 835 EDI to XML.");
            log.debug("835 XML:\n{}", xml);
            return xml;
        } catch (Exception ex) {
            // Surface parsing errors so we can fix schema/config issues (no fallback).
            log.error("Smooks failed to parse 835 EDI.", ex);
            throw ex;
        }
    }

    /**
     * Serialize XML (conforming to 835_mapping.dfdl.xsd) back to X12 835 EDI using Smooks.
     */
    public static String xmlToEDI(String xml) throws IOException, SAXException {
        try (Smooks smooks = new Smooks("serialize-835-config.xml")) {
            smooks.setFilterSettings(FilterSettings.newSaxNgSettings().setDefaultSerializationOn(false));
            final byte[] xmlBytes = xml.getBytes();
            log.debug("Serializing 835 XML input ({} bytes)...", xmlBytes.length);
            log.debug("Input XML structure: {}", xml.substring(0, Math.min(500, xml.length())));

            // Validate XML structure before processing
            if (!xml.contains("<interchange-header>")) {
                log.error("XML is missing required interchange-header element");
                throw new IllegalArgumentException("XML must contain interchange-header element as defined in 835_mapping.dfdl.xsd");
            }

            StringSink ediResult = new StringSink();
            smooks.filterSource(new StreamSource<>(new ByteArrayInputStream(xmlBytes)), ediResult);
            String edi = ediResult.getResult();
            log.info("Successfully converted XML to 835 EDI.");
            log.debug("835 EDI:\n{}", edi);
            return edi;
        } catch (Exception ex) {
            // Surface serialization errors as well to ensure schema/config are correct.
            log.error("Smooks failed to serialize 835 XML. Input XML: {}", 
                     xml.substring(0, Math.min(200, xml.length())), ex);
            throw ex;
        }
    }
}
