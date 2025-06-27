package org.example.XML;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@JsonRootName("X850Interchange")
public class X850Interchange {

    @JsonProperty("interchange-header")
    private InterchangeHeader interchangeHeader;

    @JsonProperty("group-header")
    private GroupHeader groupHeader;

    @JsonProperty("transaction-set-header")
    private TransactionSetHeader transactionSetHeader;

    @JsonProperty("party-identifications")
    private PartyIdentifications partyIdentifications;

    @JsonProperty("items")
    private Items items;

    @JsonProperty("transaction-totals")
    private TransactionTotals transactionTotals;

    @JsonProperty("transaction-set-trailer")
    private TransactionSetTrailer transactionSetTrailer;

    @JsonProperty("functional-group-trailer")
    private FunctionalGroupTrailer functionalGroupTrailer;

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