package com.company.csvfilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.company.exceptions.CsvFileParsingException;

import static com.company.functions.Functions.collectAsTransactions;
import static com.company.functions.Functions.transactionToString;

public class CsvFilter {

    public void filter(URI inputFile1, URI inputFile2, URI outputFile) {

        try {
            List<String> resultLines = Stream.concat(collectAsTransactions(inputFile1).stream(), collectAsTransactions(inputFile2).stream())
                            .collect(Collectors.toList())
                            .stream()
                            .distinct()
                            .map(transactionToString)
                            .collect(Collectors.toList());

            try (PrintWriter pw = getWriter(outputFile)) {
                pw.println(Transaction.HEAD);
                resultLines.stream().forEach(pw::println);
            }

        } catch (IOException e) {
            throw new CsvFileParsingException(e);
        }
    }

    private PrintWriter getWriter(URI outputFile) throws IOException {
        return new PrintWriter(Files.newBufferedWriter(Paths.get(outputFile), StandardOpenOption.CREATE));
    }

}
