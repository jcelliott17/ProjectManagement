/**
 * Created by JackieElliott on 4/4/17.
 */

import org.junit.Test;
import com.example.jackieelliott.Oasis.Model.QualityReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class SortReportsTest {
    /**
     * test method for sortReports located in QualityReports
     */
    @SuppressWarnings("FeatureEnvy")
    @Test
    public final void testSortReports() {

        //Creates the quality reports
        QualityReport report1 = new QualityReport("Report 1", 12.0, 15.0);
        QualityReport report2 = new QualityReport("Report 2", 78, -23);
        QualityReport report3 = new QualityReport("Report 3", 12.0, 15.0);
        QualityReport report4 = new QualityReport("Report 4", 12.0, 65);
        QualityReport report5 = new QualityReport("Report 5", -20, -19);
        QualityReport report6 = new QualityReport("Report 6", -90, -18);
        QualityReport report7 = new QualityReport("Report 7", 0, -1);
        QualityReport report8 = new QualityReport("Report 8", 12, 15);
        QualityReport report9 = new QualityReport("Report 9", 66, 67);
        QualityReport report10 = new QualityReport("Report 10", 13, -13);
        QualityReport report11 = new QualityReport("Report 11", 12, 15);

        //Sets certain times and dates for the reports
        report1.setTimeAndDate(new Date(117,9,11));
        report2.setTimeAndDate(new Date(116,5,11));
        report3.setTimeAndDate(new Date(117,2,11));
        report4.setTimeAndDate(new Date(117,4,11));
        report5.setTimeAndDate(new Date(116,9,11));
        report6.setTimeAndDate(new Date(115,8,11));
        report7.setTimeAndDate(new Date(117,2,11));
        report8.setTimeAndDate(new Date(114,2,12));
        report9.setTimeAndDate(new Date(115,7,11));
        report10.setTimeAndDate(new Date(116,2,11));
        report11.setTimeAndDate(new Date(117,9,14));

        //Creates the lists that will contain the reports
        ArrayList<QualityReport> emptyQualityReportArrayList = new ArrayList<>();
        ArrayList<QualityReport> fullQualityReportArrayList = new ArrayList<>();
        ArrayList<QualityReport> partialQualityReportArrayList = new ArrayList<>();
        ArrayList<QualityReport> totalMatchQualityReportArrayList = new ArrayList<>();

        //Adds reports to lists
        fullQualityReportArrayList.add(report1);
        fullQualityReportArrayList.add(report2);
        fullQualityReportArrayList.add(report3);
        fullQualityReportArrayList.add(report4);
        fullQualityReportArrayList.add(report5);
        fullQualityReportArrayList.add(report6);
        fullQualityReportArrayList.add(report7);
        fullQualityReportArrayList.add(report8);
        fullQualityReportArrayList.add(report9);
        fullQualityReportArrayList.add(report10);
        fullQualityReportArrayList.add(report11);

        partialQualityReportArrayList.add(report2);
        partialQualityReportArrayList.add(report4);
        partialQualityReportArrayList.add(report5);
        partialQualityReportArrayList.add(report6);
        partialQualityReportArrayList.add(report7);
        partialQualityReportArrayList.add(report8);
        partialQualityReportArrayList.add(report9);
        partialQualityReportArrayList.add(report10);

        totalMatchQualityReportArrayList.add(report1);
        totalMatchQualityReportArrayList.add(report3);
        totalMatchQualityReportArrayList.add(report11);

        //Creates correct lists to compare with the returned array of linkedLists
        LinkedList<QualityReport>[] emptyList = new LinkedList[12];
        LinkedList<QualityReport>[] testList1 = new LinkedList[12];

        //Fills elements of the array of linkedLists
        testList1[2] = new LinkedList<>();
        testList1[9] = new LinkedList<>();
        testList1[2].add(report3);
        testList1[9].add(report1);
        testList1[9].add(report11);

        //actual testing here
        assertArrayEquals("Passing an empty quality list should return an empty array "
                + "of linkedLists.",emptyList, QualityReport.sortReports(2017, 12, 12,
                emptyQualityReportArrayList));
        assertArrayEquals("Passing null should return an empty array of linkedLists.",
                emptyList, QualityReport.sortReports(2017, 12, 12,null));
        assertArrayEquals("Passing a non empty quality list with no matches should return"
                + " an empty array of linkedLists.", emptyList, QualityReport.
                sortReports(2013, 12, 15, partialQualityReportArrayList));
        assertArrayEquals("Passing a non empty quality list with some matches should return"
                + " a correctly sorted array of linkedLists.", testList1,
                QualityReport.sortReports(2017, 12.0, 15.0, fullQualityReportArrayList));
        assertArrayEquals("Passing a non empty quality list with all matches should return"
                + " a correctly sorted array of linkedLists", testList1,
                QualityReport.sortReports(2017, 12.0, 15.0, totalMatchQualityReportArrayList));
    }


}
