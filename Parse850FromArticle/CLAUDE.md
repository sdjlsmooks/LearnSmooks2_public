# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Smooks EDI (Electronic Data Interchange) parsing example that demonstrates converting X12 850 Purchase Order transactions between EDI, XML, JSON, and YAML formats. The project is based on the tutorial from https://agilevision.io/blog/generate-electronic-data-interchange-edi-x12-850-using-smooks-v2/.

## Build and Development Commands

### Building the Project
```bash
mvn clean compile
```

### Running the Main Application
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

### Running Tests
```bash
mvn test
```

## Key Architecture Components

### Core Parser Classes
- **X12_850_Parser** (`src/main/java/org/example/XML/X12_850_Parser.java`): Main parser utility that handles EDI↔XML↔JSON↔YAML conversions using Smooks framework
- **X12_850_Interchange** (`src/main/java/org/example/XML/X12_850_Interchange.java`): Data model representing the complete X12 850 interchange structure with nested classes for headers, items, trailers, etc.

### Configuration Files
- **parse-config.xml**: Smooks configuration for EDI→XML conversion, references `mapping.dfdl.xsd`
- **serialize-config.xml**: Smooks configuration for XML→EDI conversion
- **mapping.dfdl.xsd**: DFDL schema defining X12 850 EDI format structure and field mappings
- **inputmessage.edi**: Sample X12 850 EDI message for testing

### Key Dependencies
- **Smooks 2.1.0**: Core EDI processing framework with EDI cartridge
- **Jackson**: Multiple modules for XML, JSON, and YAML serialization
- **Lombok**: For reducing boilerplate code with annotations
- **MongoDB Driver**: For database operations (used in CLI example)
- **Apache Commons CLI**: For command-line argument parsing

### Processing Flow
1. **EDI Input**: Raw X12 850 EDI string/bytes
2. **Parse to XML**: Using Smooks with DFDL schema mapping
3. **Convert to Java Object**: XML mapped to X12_850_Interchange using Jackson
4. **Multi-format Output**: Convert to JSON, YAML, or back to EDI
5. **Round-trip Conversion**: EDI→XML→Object→XML→EDI

### Important Notes
- The project uses Java 17 as compiler source/target
- All format conversions maintain data integrity through structured object mapping
- DFDL schema handles complex EDI segment parsing with proper field validation
- Jackson mappers are configured to ignore unknown properties for flexibility