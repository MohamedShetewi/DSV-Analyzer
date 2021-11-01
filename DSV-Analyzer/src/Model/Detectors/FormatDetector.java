package Model.Detectors;

import Model.Formats.Format;
import Model.Formats.NumberFormat;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class FormatDetector {

    public void analyze(String[][]table, Format[] columnStates) {


        for (int col = 0; col < table[0].length; col++) {

            if (columnStates[col] == null) {
                ArrayList<String> curColumn = new ArrayList<>();

                for (int row = 1; row < table.length; row++)
                    curColumn.add(removeDoubleQuotes(table[row][col]));
                columnStates[col] = matchFormats(curColumn);
            }
        }
    }

    public abstract Format matchFormats(ArrayList<String> column);

    public String removeDoubleQuotes(String entry) {

        if (entry == null || entry.isEmpty())
            return entry;
        if (entry.charAt(0) == '"')
            entry = entry.substring(1);
        if (entry.length() > 0 && entry.charAt(entry.length() - 1) == '"')
            entry = entry.substring(0, entry.length() - 1);

        return entry;
    }

}
