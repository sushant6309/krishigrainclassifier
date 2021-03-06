package com.prashant.java.krishi.classifier.app;

import lombok.Getter;
import org.kohsuke.args4j.Option;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 *
 */
@Getter
public class AppArguments {

    private static final String SRC_DATASET_USAGE = "This is the dataset file which holds all the files pre-classified.";
    @Option(name = "-sourceDataset", usage = SRC_DATASET_USAGE)
    private String sourceDatasetFileName;

    @Option(name = "-input", usage = "Image which should be classified.")
    private String processImage;

    @Option(name = "-help")
    private boolean help;




    public Reader getModalInputSupplier() {
        final Optional<Path> sourceFile = Optional.ofNullable(sourceDatasetFileName).map(File::new).map(File::toPath);
        try{
            if(sourceFile.isPresent()){
                return Files.newBufferedReader(sourceFile.get());
            }
            InputStreamReader inputStreamReader = new InputStreamReader(AppArguments.class.getResourceAsStream
                ("/basic_dataset.json"));
            return new BufferedReader(inputStreamReader);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
