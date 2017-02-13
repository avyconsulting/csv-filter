package com.company.csvfilter;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;

import com.company.exceptions.CsvFileParsingException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static com.company.functions.Functions.isEmpty;

public class CsvFilterTest {

    final static File output = new File("src/main/resources/output.csv");

    CsvFilter underTest = new CsvFilter();

    @Test(expected = CsvFileParsingException.class)
    public void shouldOutputCreationFail() throws Exception {
        final URI inputFile1 = CsvFilterTest.class.getClassLoader().getResource("input/file1.csv").toURI();
        final URI inputFile2 = CsvFilterTest.class.getClassLoader().getResource("input/file2.csv").toURI();
        final URI outputFile = URI.create("file:///output/somefile");

        underTest.filter(inputFile1, inputFile2, outputFile);
    }

    @Test
    public void shouldOutputCreationPass() throws Exception {

        final URI inputFile1 = CsvFilterTest.class.getClassLoader().getResource("input/file1.csv").toURI();
        final URI inputFile2 = CsvFilterTest.class.getClassLoader().getResource("input/file2.csv").toURI();

        underTest.filter(inputFile1, inputFile2, output.toURI());

        Path outPath = Paths.get(output.toURI());
        List<String> allLines = Files.readAllLines(outPath);

        assertNotNull(allLines);
        assertThat(allLines.size(), is(9));

        assertThat(allLines.get(0), is(Transaction.HEAD));
        assertThat(allLines.get(1), is("ABC,923757023,2017-01-12T13:24:13,TYPEA,1200000.00,12000.00"));
        assertThat(allLines.get(2), is("XYZ,956808949,2017-01-12T13:28:56,TYPEB,450000.00,2000.00"));
        assertThat(allLines.get(3), is("LMN,748034958,2017-01-12T14:02:18,TYPEA,25500000.00,95000.00"));
        assertThat(allLines.get(4), is("DEF,693490394,2017-01-12T15:21:14,TYPEC,10000.00,500.00"));
        assertThat(allLines.get(5), is("STU,290359876,2017-01-12T16:10:42,TYPED,380000.00,4300.00"));
        assertThat(allLines.get(6), is("ABC,923757023,2017-01-12T16:11:09,TYPEC,2400000.00,24000.00"));
        assertThat(allLines.get(7), is("LMN,748034959,2017-01-12T16:28:16,TYPEA,500000.00,5000.00"));
        assertThat(allLines.get(8), is("XYZ,290359876,2017-01-12T16:31:24,TYPED,630000.00,800.00"));

    }

    @Test
    public void shouldHandleEmptyFilesGracefully() throws Exception {

        final URI inputFile1 = CsvFilterTest.class.getClassLoader().getResource("input/empty-file1.csv").toURI();
        final URI inputFile2 = CsvFilterTest.class.getClassLoader().getResource("input/empty-file2.csv").toURI();

        underTest.filter(inputFile1, inputFile2, Paths.get("output.csv").toUri());

        Path path = Paths.get(Paths.get("output.csv").toUri());

        assertTrue(Optional.of(path.toUri()).map(isEmpty).get());
    }
    
    @After
    public void after () {
        output.delete();
    }
}
