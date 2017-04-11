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
public class CHessJUnit {
    /**
     * Example unit test method
     * @throws Exception when not equal
     */
    @Test
    public final void checkGreaterPPMVal() throws Exception {
        double v1 = 5.64;
        double c1 = 2.72;

        double v2 = 4.20;
        double c2 = 4.21;

        double v3 = 6.11;
        double c3 = 6.11;

        QualityReport test = new QualityReport("TestReport");

        assertEquals("Virus PPM is greater", test.greaterPPMVal(v1, c1));
        assertEquals("Contaminant PPM is greater", test.greaterPPMVal(v1, c1));
        assertEquals("Virus and Contaminant PPM are equal", test.greaterPPMVal(v1, c1));
    }
}