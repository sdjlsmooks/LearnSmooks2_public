package org.example.XML;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

/**
 * Minimal Java representation of a 276 Claim Status message.
 * <p>
 * Only the segments used in the sample file are modeled.  This is just
 * enough to demonstrate how Smooks can map the EDI message into Java
 * objects using Jackson for the XML binding.
 */
@Data
@JsonRootName("X12_276_ClaimStatus")
public class X12_276_ClaimStatus {

    @JsonProperty("interchange-header")
    private InterchangeHeader interchangeHeader;

    @JsonProperty("group-header")
    private GroupHeader groupHeader;

    @JsonProperty("transaction-set-header")
    private TransactionSetHeader transactionSetHeader;

    @JsonProperty("bht")
    private Bht bht;

    @JsonProperty("information-source")
    private InformationSource informationSource;

    @JsonProperty("information-receiver")
    private InformationReceiver informationReceiver;

    @JsonProperty("provider")
    private Provider provider;

    @JsonProperty("subscriber")
    private Subscriber subscriber;

    @JsonProperty("transaction-set-trailer")
    private TransactionSetTrailer transactionSetTrailer;

    @JsonProperty("functional-group-trailer")
    private FunctionalGroupTrailer functionalGroupTrailer;

    @JsonProperty("interchange-control-trailer")
    private InterchangeControlTrailer interchangeControlTrailer;

    // -------------------------------------------------------------
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
        @JsonProperty("implementation-convention")
        private String implementationConvention;
    }

    @Data
    public static class Bht {
        @JsonProperty("hierarchical-structure-code")
        private String hierarchicalStructureCode;
        @JsonProperty("transaction-set-purpose-code")
        private String transactionSetPurposeCode;
        @JsonProperty("reference-identification")
        private String referenceIdentification;
        @JsonProperty("date")
        private String date;
        @JsonProperty("time")
        private String time;
    }

    @Data
    public static class Hl {
        @JsonProperty("hierarchical-id-number")
        private String hierarchicalIdNumber;
        @JsonProperty("hierarchical-parent-id")
        private String hierarchicalParentId;
        @JsonProperty("hierarchical-level-code")
        private String hierarchicalLevelCode;
        @JsonProperty("hierarchical-child-code")
        private String hierarchicalChildCode;
    }

    @Data
    public static class Nm1 {
        @JsonProperty("entity-id-code")
        private String entityIdCode;
        @JsonProperty("entity-type-qualifier")
        private String entityTypeQualifier;
        @JsonProperty("name-last-or-org")
        private String nameLastOrOrg;
        @JsonProperty("name-first")
        private String nameFirst;
        @JsonProperty("name-middle")
        private String nameMiddle;
        @JsonProperty("name-prefix")
        private String namePrefix;
        @JsonProperty("name-suffix")
        private String nameSuffix;
        @JsonProperty("id-code-qualifier")
        private String idCodeQualifier;
        @JsonProperty("id-code")
        private String idCode;
    }

    @Data
    public static class Per {
        @JsonProperty("contact-function-code")
        private String contactFunctionCode;
        @JsonProperty("name")
        private String name;
        @JsonProperty("communication-number-qualifier")
        private String communicationNumberQualifier;
        @JsonProperty("communication-number")
        private String communicationNumber;
    }

    @Data
    public static class Trn {
        @JsonProperty("trace-type-code")
        private String traceTypeCode;
        @JsonProperty("reference-identification")
        private String referenceIdentification;
        @JsonProperty("originating-company-id")
        private String originatingCompanyId;
        @JsonProperty("reference-id")
        private String referenceId;
    }

    @Data
    public static class Ref {
        @JsonProperty("qualifier")
        private String qualifier;
        @JsonProperty("id")
        private String id;
    }

    @Data
    public static class N3 {
        @JsonProperty("address1")
        private String address1;
        @JsonProperty("address2")
        private String address2;
    }

    @Data
    public static class N4 {
        @JsonProperty("city")
        private String city;
        @JsonProperty("state")
        private String state;
        @JsonProperty("postal")
        private String postal;
    }

    @Data
    public static class Dmg {
        @JsonProperty("format")
        private String format;
        @JsonProperty("date")
        private String date;
        @JsonProperty("gender")
        private String gender;
    }

    @Data
    public static class Dtp {
        @JsonProperty("qualifier")
        private String qualifier;
        @JsonProperty("format")
        private String format;
        @JsonProperty("date")
        private String date;
    }

    @Data
    public static class Eq {
        @JsonProperty("service-type-code")
        private String serviceTypeCode;
    }

    // Loop groupings
    @Data
    public static class InformationSource {
        @JsonProperty("hl")
        private Hl hl;
        @JsonProperty("nm1")
        private Nm1 nm1;
    }

    @Data
    public static class InformationReceiver {
        @JsonProperty("hl")
        private Hl hl;
        @JsonProperty("nm1")
        private Nm1 nm1;
        @JsonProperty("per")
        private Per per;
    }

    @Data
    public static class Provider {
        @JsonProperty("hl")
        private Hl hl;
        @JsonProperty("nm1")
        private Nm1 nm1;
        @JsonProperty("trn")
        private Trn trn;
    }

    @Data
    public static class Subscriber {
        @JsonProperty("hl")
        private Hl hl;
        @JsonProperty("nm1")
        private Nm1 nm1;
        @JsonProperty("ref")
        private Ref ref;
        @JsonProperty("n3")
        private N3 n3;
        @JsonProperty("n4")
        private N4 n4;
        @JsonProperty("dmg")
        private Dmg dmg;
        @JsonProperty("dtp")
        private Dtp dtp;
        @JsonProperty("eq")
        private Eq eq;
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
