package org.example.XML;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.extern.slf4j.Slf4j;
import org.smooks.FilterSettings;
import org.smooks.Smooks;
import org.smooks.io.sink.StringSink;
import org.smooks.io.source.StreamSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Parser utilities for X12 276 Claim Status messages.
 * <p>
 * The implementation mirrors {@link X12_850_Parser} but targets the
 * simplified {@link X12_276_ClaimStatus} model.
 */
@Slf4j
public class X12_276_Parser {

    private static final XmlMapper xmlMapper = new XmlMapper();
    private static final JsonMapper jsonMapper = new JsonMapper();
    private static final YAMLMapper yamlMapper = new YAMLMapper();

    static {
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String parseEDI(String ediString) throws IOException, SAXException {
        return parseEDI(ediString.getBytes());
    }

    public static String parseEDI(byte[] ediInput) throws IOException, SAXException {
        StringSink result;
        try (Smooks smooks = new Smooks("parse-config.xml")) {
            result = new StringSink();
            smooks.filterSource(new StreamSource<>(new ByteArrayInputStream(ediInput)), result);
        }
        return result.getResult();
    }

    public static X12_276_ClaimStatus parseXML(String xml) throws IOException {
        return xmlMapper.readValue(xml, X12_276_ClaimStatus.class);
    }

    public static String toXml(X12_276_ClaimStatus claimStatus) throws IOException {
        return xmlMapper.writeValueAsString(claimStatus);
    }

    public static String toJson(X12_276_ClaimStatus claimStatus) throws IOException {
        return jsonMapper.writeValueAsString(claimStatus);
    }

    public static String toYaml(X12_276_ClaimStatus claimStatus) throws IOException {
        return yamlMapper.writeValueAsString(claimStatus);
    }

    public static String xmlToEDI(String xml) throws IOException, SAXException {
        StringSink result;
        try (Smooks smooks = new Smooks("serialize-config.xml")) {
            smooks.setFilterSettings(FilterSettings.newSaxNgSettings().setDefaultSerializationOn(false));
            result = new StringSink();
            smooks.filterSource(new StreamSource<>(new ByteArrayInputStream(xml.getBytes())), result);
        }
        return result.toString();
    }

    public static String xmlToEDI(X12_276_ClaimStatus claimStatus) throws IOException, SAXException {
        return xmlToEDI(toXml(claimStatus));
    }
}
