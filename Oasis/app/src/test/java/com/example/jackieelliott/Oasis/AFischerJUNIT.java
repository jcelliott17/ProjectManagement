package com.example.jackieelliott.Oasis;

import com.example.jackieelliott.Oasis.Model.QualityReport;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("ALL")
public class AFischerJUNIT {
    /**
     * Example unit test method
     * @throws Exception when not equal
     */
    @Test
    public final void getAverage_isCorrect() throws Exception {
        int[] virus = {1, 2, 3};
        int[] contaminants = {4, 5, 6};

        QualityReport checker = new QualityReport("tester");

        assertEquals(2, checker.getAverage(contaminants, virus, "Virus"));
        assertEquals(5, checker.getAverage(contaminants, virus, "Contaminants"));
    }
}