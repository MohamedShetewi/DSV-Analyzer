package Model;

import Model.Formats.Format;
import Model.Formats.StringFormat;

import java.util.ArrayList;
import java.util.Hashtable;

@SuppressWarnings("All")
public class DSVFile {

    private final Hashtable<String, String> delimiterNames;
    private final String name;
    private String[][] table;
    private char delimiter;
    private ArrayList<String> columnNames;
    private int rowsCount;
    private Format[] columnsType;


    public DSVFile(String name, String[][] table, char delimiter, ArrayList<String> columnNames) {
        this.name = name;
        this.table = table;
        this.delimiter = delimiter;
        this.columnNames = columnNames;
        this.rowsCount = table.length;
        delimiterNames = new Hashtable<>();
        delimiterNames.put("\t", "tab");
        delimiterNames.put(",", "comma");
        delimiterNames.put(";", "semi-colon");
    }

    private String getDelimiterName(String delimiter) {
        return delimiterNames.get(delimiter);
    }

    public void setColumnsType(Format[] columnsType) {
        for(int i = 0; i < columnsType.length;i ++)
            if(columnsType[i] == null) columnsType[i] = new StringFormat("String");

        this.columnsType = columnsType;
    }

    public Format[] getColumnsType() {
        return columnsType;
    }

    public String[][] getTable() {
        return table;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("General File Info:\n");
        stringBuilder.append("-----------------------------------\n");
        stringBuilder.append("File Name :- ").append(this.name).append("\n");
        stringBuilder.append("Delimiter Type :- ").append(getDelimiterName("" + this.getDelimiter())).append("\n");
        stringBuilder.append("Columns Count:- ").append(getColumnNames().size()).append("\n");
        stringBuilder.append("Rows Count:- ").append(getRowsCount()).append("\n");
        stringBuilder.append("-----------Columns DataTypes------------").append("\n");
        for (int i = 0; i < columnNames.size(); i++)
            stringBuilder.append(columnNames.get(i))
                    .append(" -> ")
                    .append(columnsType[i] == null ? "String" : columnsType[i])
                    .append("\n");

        return stringBuilder.append("\n\n").toString();
    }


}
