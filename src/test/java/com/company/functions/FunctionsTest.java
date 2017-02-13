package com.company.functions;

import java.util.function.Function;

import org.junit.Test;

import com.company.csvfilter.Transaction;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class FunctionsTest {

    private static final String LINE = "STU,290359876,2017-01-12T16:10:42,TYPED,380000.00,4300.00";
    
    final Function<String, Transaction> toTransaction = Functions.toTransaction;
    final Function<Transaction, String> transactionToString = Functions.transactionToString;
    
    @Test
    public void shouldCreateTransaction() throws Exception {
        Transaction transaction = toTransaction.apply(LINE);

        assertNotNull(transaction);
        assertThat(transaction.getId(), is("290359876"));
        assertThat(transaction.getSource(), is("STU"));
        assertThat(transaction.getType(), is("TYPED"));
    }
    
    @Test
    public void shouldCreateTransactionToString() throws Exception {
        Transaction transaction = toTransaction.apply(LINE);
        
        String line = transactionToString.apply(transaction);

        assertNotNull(line);
        assertThat(line, is(LINE));
    }
        
}
