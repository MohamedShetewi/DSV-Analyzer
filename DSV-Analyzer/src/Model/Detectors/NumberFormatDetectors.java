package Model.Detectors;

import Model.Formats.Format;
import Model.Formats.NumberFormat;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class NumberFormatDetectors extends FormatDetector {

    private final String[] digitalSeparators = new String[]{"\\.", ","};
    private final String[] thousandSeparators = new String[]{"\\.", ",", " "};

    private String[][] table;
    private Format[] columnStates;


    public NumberFormatDetectors(String[][] table, Format[] columnStates) {
        this.table = table;
        this.columnStates = columnStates;
        analyze(table, columnStates);
    }

    public NumberFormat matchFormats(ArrayList<String> column) {

        String numberRegex1 = "^[+-]?[0-9]{1,3}(%s[0-9]{3})*(%s[0-9]+)$"; // with digital and thousands separators
        String numberRegex3 = "^[+-]?[0-9]{1,3}([0-9]{3})*$"; // without any Separators

        for (String thousandSeparator : thousandSeparators)
            for (String digitalSeparator : digitalSeparators)
                if (!thousandSeparator.equals(digitalSeparator)) {


                    String regex = String.format(numberRegex1, thousandSeparator, digitalSeparator);

                    boolean matching = column.stream().allMatch(entry -> matchesDateFormat(entry, regex));
                    if (matching) {
                        return new NumberFormat(thousandSeparator, digitalSeparator);
                    }
                }

        boolean matching = column.stream().allMatch(entry -> matchesDateFormat(entry, numberRegex3));
        if (matching)
            return  new NumberFormat("null", "null");

        return null;
    }

    private boolean matchesDateFormat(String string, String numberFormat) {
        return string.isEmpty() || Pattern.compile(numberFormat).matcher(string).find();
    }

}
