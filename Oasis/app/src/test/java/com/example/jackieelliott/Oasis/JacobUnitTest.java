package com.example.jackieelliott.Oasis;


import org.junit.Assert;
import org.junit.Test;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("ALL")
public class JacobUnitTest {
    /**
     * Example unit test method
     * @throws Exception when not equal
     */
    @Test
    public final void maxLat() throws Exception {
        double[] latitudes = new double[6];
        latitudes[0] = 5.08;
        latitudes[1] = 3.9;
        latitudes[2] = 10.23;
        latitudes[3] = 20.8;
        latitudes[4] = 15.49;
        latitudes[5] = 1.9;

        double max = 0;

        for (double e : latitudes) {
            if (e > max) {
                max = e;
            }
        }

        Assert.assertEquals(20.8, max, 0);

    }
}
