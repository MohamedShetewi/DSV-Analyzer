package Model.Detectors;

import Model.Formats.DateFormat;
import Model.Formats.Format;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DateFormatFormatDetector extends FormatDetector {


    private final String dd = "(0[1-9]|[12][0-9]|3[01])";
    private final String mm = "(0[1-9]|1[0-2])";
    private final String yyyy = "\\d{4}";
    private final String[] dateRegex = new String[]{
            dd + "/" + mm + "/" + yyyy,
            mm + "/" + dd + "/" + yyyy,
            yyyy + "/" + mm + "/" + dd,
            dd + "\\." + mm + "\\." + yyyy,
            mm + "\\." + dd + "\\." + yyyy,
            yyyy + "\\." + mm + "\\." + dd
    };
    private final String[] dateFormats = new String[]{
            "DD/MM/YYYY", "MM/DD/YYYY", "YYYY/MM/DD", "DD.MM.YYYY", "MM.DD.YYYY", "YYYY.MM.DD"
    };

    private Format[] columnStates;
    private String[][] table;


    public DateFormatFormatDetector(String[][] table, Format[] columnStates) {
        this.columnStates = columnStates;
        this.table = table;
        analyze(table, columnStates);
    }


    public DateFormat matchFormats(ArrayList<String> column) {

        boolean[] matchedDateFormats = new boolean[6];

        for (int i = 0; i < dateRegex.length; i++) {
            String curRegex = dateRegex[i];
            matchedDateFormats[i] = column.stream()
                    .allMatch(entry -> matchesDateFormat(entry, curRegex));
        }

        for (int i = 0; i < matchedDateFormats.length; i++)
            if (matchedDateFormats[i])
                return new DateFormat(dateFormats[i]);

        return null;
    }

    private boolean matchesDateFormat(String date, String dateFormat) {
        return date.isEmpty() || Pattern.compile(dateFormat).matcher(date).find();
    }


}
