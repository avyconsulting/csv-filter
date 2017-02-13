package com.company.functions;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.company.csvfilter.Transaction;
import com.company.exceptions.CsvFileParsingException;

/**
 * Functions
 */
public class Functions {

    private Functions() {}

    public static final Function<Transaction, String> transactionToString = transaction -> {
        return transaction.toString();
    };

    /**
     * convert each CSV line to a {@link Transaction}
     */
    public static final Function<String, Transaction> toTransaction = line -> {
        String[] values = line.split(",");

        return new Transaction(values[0], values[1], values[2], values[3], values[4], values[5]);
    };
    
    /**
     * read input file and collect lines as transaction {@link Transaction}
     * 
     * @param inputFile
     * @return list of transaction
     * @throws IOException exception
     */
    public static final List<Transaction> collectAsTransactions(URI inputFile) throws IOException {
        return Files.lines(Paths.get(inputFile))
                        .skip(1)
                        .map(toTransaction)
                        .collect(Collectors.toList());
    }
    
    public static final Function<URI, Boolean> isEmpty = file -> {
        try {
            if (Paths.get(file).toFile().exists() && Files.lines(Paths.get(file)).count() > 0) {
                return Boolean.FALSE;
            }
        } catch (IOException e) {
            throw new CsvFileParsingException(e);
        }
        return Boolean.TRUE;
    };
}
