package org.example;


import lombok.extern.java.Log;
import org.apache.commons.cli.*;

/**
 * This Java program preloads data from a JSON file into a MongoDB collection.
 * It assumes the JSON file contains an array of JSON objects, where each object
 * represents a document to be inserted into MongoDB.
 * <p>
 * Before running:
 * 1. Ensure MongoDB is running on your system (default: localhost:27017).
 * 2. Add the MongoDB Java Driver and JSON library dependencies to your project's pom.xml (if using Maven):
 * <dependency>
 * <groupId>org.mongodb</groupId>
 * <artifactId>mongodb-driver-sync</artifactId>
 * <version>4.11.1</version> <!-- Use the latest stable version -->
 * </dependency>
 * <dependency>
 * <groupId>org.json</groupId>
 * <artifactId>json</artifactId>
 * <version>20231013</version> <!-- Use a recent version -->
 * </dependency>
 * 3. Create a sample JSON file (e.g., 'data.json') in the same directory as your compiled .class file,
 * or provide the full path to the file.
 * <p>
 * Example 'data.json' structure:
 * [
 * {
 * "name": "Alice",
 * "age": 30,
 * "city": "New York"
 * },
 * {
 * "name": "Bob",
 * "age": 24,
 * "city": "Los Angeles"
 * },
 * {
 * "name": "Charlie",
 * "age": 35,
 * "city": "Chicago"
 * }
 * ]
 */
@Log
public class ExampleApacheCommonsCLI {

    private static String MONGO_URI = "mongodb://localhost:27017"; // Default MongoDB URI
    private static String DEFAULT_DATABASE_NAME = "sdjltestdb"; // Default database name
    private static String DEFAULT_COLLECTION_NAME = "sdjlcollection"; // Default collection name
    private static String DEFAULT_FILENAME = "sdjldata.json"; // Default data filename;
    private static boolean DEFAULT_DROP = false;  // Default drop collection before populating


    private static void parseCommandLineArgs(String[] args) {
        // Create an Options object to define the command-line options
        Options options = new Options();

        // Define the 'mongourl' option
        // It's a required option, takes an argument (the URL), and has a description.
        options.addOption(Option.builder("m") // Short option name
                .longOpt("mongourl") // Long option name
                .hasArg() // Indicates it requires an argument
                .argName("URL") // Name for the argument in help message
                .required(true) // This option is mandatory
                .desc("MongoDB connection URL (e.g., mongodb://localhost:27017)")
                .build());

        // Define the 'dbname' option
        // It's a required option, takes an argument (the database name), and has a description.
        options.addOption(Option.builder("d")
                .longOpt("dbname")
                .hasArg()
                .argName("NAME")
                .required(true)
                .desc("Name of the MongoDB database")
                .build());

        // Define the 'collection' option
        // It's a required option, takes an argument (the collection name), and has a description.
        options.addOption(Option.builder("c")
                .longOpt("collection")
                .hasArg()
                .argName("COLLECTION")
                .required(true)
                .desc("The name of the collection to load the data into")
                .build());

        // Define the 'datafile' option
        // It's a required option, takes an argument (the file path), and has a description.
        options.addOption(Option.builder("f")
                .longOpt("filename")
                .hasArg()
                .argName("PATH")
                .required(true)
                .desc("Path to the data file to be imported")
                .build());

        // Define the 'drop' option
        // It's an optional option, takes an argument ("yes" or "no"), and has a description.
        // It is not required, meaning the program can run without it.
        options.addOption(Option.builder("x")
                .longOpt("drop")
                .hasArg()
                .argName("BOOLEAN")
                .required(false) // This option is not mandatory
                .desc("Whether to drop the database before import (yes/no, default: no)")
                .build());

        // Create a parser for command-line arguments
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            // Parse the command-line arguments provided to the program
            cmd = parser.parse(options, args);

            // Retrieve the value for 'mongourl'
            String mongoUrl = cmd.getOptionValue("mongourl");
            log.info("MongoDB URL: " + mongoUrl);
            if (mongoUrl != null) {
                MONGO_URI = mongoUrl;
            }

            // Retrieve the value for 'dbname'
            String dbName = cmd.getOptionValue("dbname");
            log.info("Database Name: " + dbName);
            if (dbName != null) {
                DEFAULT_DATABASE_NAME = dbName;
            }

            // Retrieve the value for 'collection'
            String collectionName = cmd.getOptionValue("collection");
            log.info("Collection Name: " + collectionName);
            if (collectionName != null) {
                DEFAULT_COLLECTION_NAME = collectionName;
            }

            // Retrieve the value for 'datafile'
            String dataFile = cmd.getOptionValue("filename");
            log.info("Data File Path: " + dataFile);
            if (dataFile != null) {
                DEFAULT_FILENAME = dataFile;
            }

            // Retrieve the value for 'drop'
            if (cmd.hasOption("drop")) {
                String dropValue = cmd.getOptionValue("drop");
                if ("yes".equalsIgnoreCase(dropValue)) {
                    DEFAULT_DROP = true;
                } else if ("no".equalsIgnoreCase(dropValue)) {
                    DEFAULT_DROP = false;
                } else {
                    // Handle invalid input for 'drop' option
                    log.severe("Warning: Invalid value for --drop option. Expected 'yes' or 'no', but got '" + dropValue + "'. Defaulting to 'no'.");
                }
            }
            log.info("Drop database: " + DEFAULT_DROP);

        } catch (ParseException e) {
            // If there's an error parsing the arguments (e.g., missing a required option)
            log.severe(e.getMessage());
            // Print the help message
            formatter.printHelp("java -jar MongoDBPreloader.jar", options);
            System.exit(1); // Exit with an error code
        }
    }


    public static void main(String[] args) {
        // Parse all command line args
        // Example:  java -jar MongoDBPreloader.jar --mongourl=mongodb://localhost:27017 --dbname=sdjltestdb --collection=items --filename=C:\temp\resources\example.json --drop=yes
        // sets the DEFAULT_???? to what the command line argument say.
        parseCommandLineArgs(args);
    }
}
