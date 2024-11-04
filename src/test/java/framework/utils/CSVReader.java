package framework.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */


public class CSVReader {

    public static Object[][] readCSV(String filePath) {
        List<Object[]> data = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                List<Object> row = new ArrayList<>();
                record.forEach(row::add);
                data.add(row.toArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toArray(new Object[0][0]);
    }

}
