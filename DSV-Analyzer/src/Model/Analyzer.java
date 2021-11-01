package Model;

import Model.Detectors.DateFormatFormatDetector;
import Model.Detectors.NumberFormatDetectors;
import Model.Formats.Format;

public class Analyzer{



    public Analyzer(DSVFile dsvFile)
    {
        startAnalyzing(dsvFile);
    }


    private void startAnalyzing(DSVFile dsvFile){
        Format[]columnType = new Format[dsvFile.getColumnNames().size()];
        new DateFormatFormatDetector(dsvFile.getTable(), columnType);
        new NumberFormatDetectors(dsvFile.getTable(), columnType);
        dsvFile.setColumnsType(columnType);
        System.out.println(dsvFile);
    }

}
