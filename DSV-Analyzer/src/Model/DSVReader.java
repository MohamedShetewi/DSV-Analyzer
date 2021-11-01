package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("All")
public class DSVReader implements Runnable{

    private final char[] delimiters = new char[]{',', '\t', ';'};

    @SuppressWarnings("FieldMayBeFinal")
    private final File file;
    private int rowsCount;
    private int columnsCount;
    private ArrayList<String> columnNames;
    private String[][] table;
    private char delimiterType;


    public DSVReader(File file) throws IOException {
        this.file = file;
        this.columnNames = new ArrayList<>();

        startReading();
    }

    private void startReading() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String> dsvFileContent = new ArrayList<>();
        String curLine;

        while ((curLine = bufferedReader.readLine()) != null)
            dsvFileContent.add(curLine);

        rowsCount = dsvFileContent.size();
        prepareTable(dsvFileContent);
    }


    private boolean isDelimiter(char a) {
        for (char c : delimiters)
            if ((int)c == (int)a) return true;
        return false;
    }

    private void prepareTable(ArrayList<String> dsvFileContent) {

        String header = dsvFileContent.get(0);
        StringBuilder acc = new StringBuilder();

        boolean isDoubleQuoted = false;
        for (int i = 0; i < header.length(); i++) {
            char curChar = header.charAt(i);
            if (curChar == '"')
                isDoubleQuoted = !isDoubleQuoted;
            else if (!isDoubleQuoted && isDelimiter( curChar)) {
                delimiterType = curChar;
                columnNames.add(acc.toString());
                acc = new StringBuilder();
                continue;
            }
            acc.append(curChar);
        }
        columnNames.add(acc.toString());

        columnsCount = columnNames.size();

        table = new String[rowsCount][columnsCount];

        for (int i = 0; i < dsvFileContent.size(); i++) {
            String curRow = dsvFileContent.get(i);

            int col = 0;
            isDoubleQuoted = false;
            StringBuilder curString = new StringBuilder();
            for (int j = 0; j < curRow.length(); j++) {
                char curChar = curRow.charAt(j);
                if ((curChar == delimiterType && !isDoubleQuoted) || j == curRow.length() - 1) {
                    if (j == curRow.length() - 1)
                        curString.append(curChar);
                    table[i][col] = curString.toString();
                    curString = new StringBuilder();
                    col++;
                    continue;
                }
                if (curChar == '"') isDoubleQuoted = !isDoubleQuoted;
                curString.append(curChar);
            }
        }
    }

    public String[][] getTable() {
        return table;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public char getDelimiterType() {
        return delimiterType;
    }

    @Override
    public void run() {
        try {
            this.startReading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
