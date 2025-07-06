package org.example.XML;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.List;

/**
 * Represents an X850 interchange structure, which encapsulates various
 * hierarchical components such as headers, parties, items, and trailers
 * for electronic data interchange (EDI) messages.
 *
 * The primary purpose of this class is to model the data elements and
 * relationships required for the X850 document type, adhering to a
 * specific standard format for business transactions.
 *
 * This class includes nested static classes that represent various functional
 * and structural components, such as interchange control, transaction details,
 * and party identifications. Each component manages specific data fields with
 * serialized JSON properties.
 *
 * The following are the main components encapsulated:
 *
 * 1. InterchangeHeader: Contains authentication, security, sender and
 *    receiver details, and other control-level data for the interchange.
 *
 * 2. GroupHeader: Includes identification and metadata regarding a functional
 *    group, such as standard version, control numbers, and timestamps.
 *
 * 3. TransactionSetHeader: Represents metadata for a transaction set,
 *    identifying the transaction type and control number.
 *
 * 4. PartyIdentifications: Captures the list of parties involved in
 *    the transaction and their identification details.
 *
 * 5. Items: Represents the list of items in the transaction, including
 *    details about baseline data, descriptions, and references.
 *
 * 6. TransactionTotals: Contains aggregation details, such as the total
 *    number of line items in the transaction.
 *
 * 7. TransactionSetTrailer: Holds summarizing details for a transaction set,
 *    including segment and control number information.
 *
 * 8. FunctionalGroupTrailer: Provides data summarizing the functional group
 *    details, such as transaction set counts and control numbers.
 *
 * 9. InterchangeControlTrailer: Tracks interchange-level counts and control
 *    number metadata.
 *
 * Each nested class provides a cohesive representation of its respective
 * component and is annotated with JSON-related metadata for easy serialization
 * and deserialization.
 */
@Data
@JsonRootName("X850Interchange")
public class X12_850_Interchange {

    /**
     * Represents the interchange header within an EDI (Electronic Data Interchange) segment.
     * This field contains metadata and control information necessary for parsing and processing
     * the EDI document, such as sender and receiver details, dates, standards, and control numbers.
     *
     * The structure of this object is defined by the {@code InterchangeHeader} class, which encapsulates
     * components like authentication details, sender and receiver identifiers, date, time, version information,
     * and other interchange-level settings.
     *
     * Annotated with {@code @JsonProperty} to define the JSON property mapping as "interchange-header".
     */
    @JsonProperty("interchange-header")
    private InterchangeHeader interchangeHeader;

    /**
     * Represents the group header information within an X12 850 interchange structure.
     * This field is mapped to the corresponding JSON property named "group-header".
     * The group header typically contains metadata about the functional group, including
     * sender and receiver information, date, time, and version details.
     */
    @JsonProperty("group-header")
    private GroupHeader groupHeader;

    /**
     * Represents the transaction set header within the X850Interchange object.
     * This field contains metadata about the specific transaction set, including
     * its code and a control number to uniquely identify the transaction set.
     *
     * Annotated with `@JsonProperty("transaction-set-header")`, this field is
     * mapped to the "transaction-set-header" key in the JSON or XML representation
     * of the X850Interchange during serialization and deserialization processes.
     */
    @JsonProperty("transaction-set-header")
    private TransactionSetHeader transactionSetHeader;

    /**
     * Represents the party identifications section of the X850Interchange object.
     * This field holds information about one or more parties involved in the interchange.
     * It is a structured representation of party details, using the {@link PartyIdentifications}
     * class to encapsulate a list of party identification data.
     */
    @JsonProperty("party-identifications")
    private PartyIdentifications partyIdentifications;

    /**
     * Represents the collection of items within an X850Interchange.
     * Each item in the collection corresponds to an instance of the {@code Item} class.
     * The items are deserialized from a JSON property named "items"
     * and are typically used to encapsulate details of individual items
     * within a transaction set.
     *
     * This field is annotated with Jackson annotations to handle JSON and XML
     * serialization and deserialization:
     * - {@code @JsonProperty} specifies the mapping from the JSON property "items"
     *   to this field.
     * - {@code @JacksonXmlElementWrapper} with {@code useWrapping = false} ensures
     *   that in XML format, the items are not wrapped in an additional container element.
     *
     * Note: The {@code Items} class itself contains a list of {@code Item} objects.
     */
    @JsonProperty("items")
    private Items items;

    /**
     * Represents the transaction totals for the X850Interchange, providing details
     * about aggregate transaction data within an interchange. This field is intended
     * to store relevant totals, such as the number of line items in the transaction set.
     *
     * Annotated with @JsonProperty to ensure the property is serialized/deserialized
     * with the name "transaction-totals" when using JSON.
     */
    @JsonProperty("transaction-totals")
    private TransactionTotals transactionTotals;

    /**
     * Represents the trailer segment of an X12 transaction set, which contains
     * information marking the conclusion of a transaction set within
     * an interchange message. This field encapsulates metadata
     * about the transaction set, including the number of
     * included segments and the transaction set control number.
     *
     * The `TransactionSetTrailer` class is a static nested class
     * within `X850Interchange` and is serialized/deserialized
     * using the `@JsonProperty` annotation.
     */
    @JsonProperty("transaction-set-trailer")
    private TransactionSetTrailer transactionSetTrailer;

    /**
     * Represents the functional group trailer section of an EDI (Electronic Data Interchange)
     * structure within the {@code X850Interchange} class. The functional group trailer contains
     * control information specific to a group of transaction sets and helps ensure that the data
     * within the group is processed correctly and completely.
     *
     * This field is mapped to the JSON property "functional-group-trailer" for
     * serialization and deserialization purposes.
     */
    @JsonProperty("functional-group-trailer")
    private FunctionalGroupTrailer functionalGroupTrailer;

    /**
     * Represents the interchange control trailer segment of an EDI (Electronic Data Interchange)
     * transaction in the X12 850 standard. This segment contains metadata about the interchange,
     * including the number of function groups included and the interchange control number.
     *
     * It is a part of the {@code X850Interchange} class and is serialized using JSON with
     * a custom property name "interchange-control-trailer".
     *
     * The {@code InterchangeControlTrailer} class contains fields for:
     * - Number of function groups included in the interchange.
     * - Control number for the interchange, used for identification and tracking.
     */
    @JsonProperty("interchange-control-trailer")
    private InterchangeControlTrailer interchangeControlTrailer;

    @Data
    public static class InterchangeHeader {
        @JsonProperty("auth-qual")
        private String authQual;
        
        @JsonProperty("auth-id")
        private String authId;
        
        @JsonProperty("security-qual")
        private String securityQual;
        
        @JsonProperty("security-id")
        private String securityId;
        
        @JsonProperty("sender-qual")
        private String senderQual;
        
        @JsonProperty("sender-id")
        private String senderId;
        
        @JsonProperty("receiver-qual")
        private String receiverQual;
        
        @JsonProperty("receiver-id")
        private String receiverId;
        
        @JsonProperty("date")
        private String date;
        
        @JsonProperty("time")
        private String time;
        
        @JsonProperty("standard")
        private String standard;
        
        @JsonProperty("version")
        private String version;
        
        @JsonProperty("interchange-control-number")
        private String interchangeControlNumber;
        
        @JsonProperty("ack")
        private String ack;
        
        @JsonProperty("test")
        private String test;
        
        @JsonProperty("s-delimiter")
        private String sDelimiter;
    }

    @Data
    public static class GroupHeader {
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("sender")
        private String sender;
        
        @JsonProperty("receiver")
        private String receiver;
        
        @JsonProperty("date")
        private String date;
        
        @JsonProperty("time")
        private String time;
        
        @JsonProperty("group-control-number")
        private String groupControlNumber;
        
        @JsonProperty("standard")
        private String standard;
        
        @JsonProperty("version")
        private String version;
    }

    @Data
    public static class TransactionSetHeader {
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("transaction-set-control-number")
        private String transactionSetControlNumber;
    }

    @Data
    public static class PartyIdentifications {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JsonProperty("party-identifications")
        private List<PartyIdentification> partyIdentificationList;
    }

    @Data
    public static class PartyIdentification {
        @JsonProperty("entity-ic")
        private String entityIc;
        
        @JsonProperty("name")
        private String name;
        
        @JsonProperty("id-code-qualifier")
        private String idCodeQualifier;
        
        @JsonProperty("id-code")
        private String idCode;
    }

    @Data
    public static class Items {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JsonProperty("items")
        private List<Item> itemList;
    }

    @Data
    public static class Item {
        @JsonProperty("baseline_item_data")
        private BaselineItemData baselineItemData;
        
        @JsonProperty("item_description")
        private ItemDescription itemDescription;
        
        @JacksonXmlElementWrapper(useWrapping = false)
        @JsonProperty("reference-information")
        private List<ReferenceInformation> referenceInformation;
    }

    @Data
    public static class BaselineItemData {
        @JsonProperty("assigned_identification")
        private String assignedIdentification;
        
        @JsonProperty("quantity")
        private String quantity;
    }

    @Data
    public static class ItemDescription {
        @JsonProperty("item_description_type")
        private String itemDescriptionType;
        
        @JsonProperty("description")
        private String description;
    }

    @Data
    public static class ReferenceInformation {
        @JsonProperty("id_qualifier")
        private String idQualifier;
        
        @JsonProperty("reference_id")
        private String referenceId;
    }

    @Data
    public static class TransactionTotals {
        @JsonProperty("number-of-line-items")
        private String numberOfLineItems;
    }

    @Data
    public static class TransactionSetTrailer {
        @JsonProperty("number-of-included-segments")
        private String numberOfIncludedSegments;
        
        @JsonProperty("transaction-set-control-number")
        private String transactionSetControlNumber;
    }

    @Data
    public static class FunctionalGroupTrailer {
        @JsonProperty("number-of-transaction-sets")
        private String numberOfTransactionSets;
        
        @JsonProperty("group-control-number")
        private String groupControlNumber;
    }

    @Data
    public static class InterchangeControlTrailer {
        @JsonProperty("number-of-function-groups-included")
        private String numberOfFunctionGroupsIncluded;
        
        @JsonProperty("interchange-control-number")
        private String interchangeControlNumber;
    }
}