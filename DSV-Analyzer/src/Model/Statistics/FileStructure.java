package Model.Statistics;

import Model.Formats.DateFormat;
import Model.Formats.Format;
import Model.Formats.NumberFormat;

import java.util.Arrays;

@SuppressWarnings("FieldMayBeFinal")
public class FileStructure {

    private String[] columns;
    private String[] dataTypes;

    public FileStructure(String[] columns, Format[] formats) {
        this.columns = columns;
        dataTypes = new String[columns.length];

        for (int i = 0; i < formats.length; i++)
            if (isDate(formats[i]))
                dataTypes[i] = "Date";
            else if (isNumber(formats[i]))
                dataTypes[i] = "Number";
            else
                dataTypes[i] = "String";
    }

    private boolean isDate(Format format) {
        return format instanceof DateFormat;
    }

    private boolean isNumber(Format format) {
        return format instanceof NumberFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStructure that = (FileStructure) o;
        return Arrays.equals(columns, that.columns) && Arrays.equals(dataTypes, that.dataTypes);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(columns);
        result = 31 * result + Arrays.hashCode(dataTypes);
        return result;
    }

    @Override
    public String toString() {
        return "FileStructure{" +
                "columns=" + Arrays.toString(columns) +
                ", dataTypes=" + Arrays.toString(dataTypes) +
                '}';
    }

    public String[] getColumns() {
        return columns;
    }

    public String[] getDataTypes() {
        return dataTypes;
    }
}
