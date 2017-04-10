package com.example.jackieelliott.Oasis;

import com.example.jackieelliott.Oasis.Model.QualityReport;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AWiremanJUNIT {
    /**
     * Example unit test method
     *
     * @throws Exception when not equal
     */
    @Test
    public final void checkLongLat() throws Exception {
        double latitude = 9.03;
        double longitude = 1.03;

        QualityReport check = new QualityReport("tester");
        
        assertEquals(9.3009, check.getLongLat(latitude, longitude), 0);

    }
}