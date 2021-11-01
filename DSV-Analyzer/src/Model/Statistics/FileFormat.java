package Model.Statistics;


import Model.Formats.Format;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("All")
public class FileFormat {

    private String delimiter;
    private Format[]usedFormats;

    public FileFormat(String delimiter, Format[]usedFormats) {
        this.delimiter = delimiter;
        this.usedFormats = usedFormats;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getDelimiterName(){
        return delimiter.equals(";")? "Semi-Colon":
                delimiter.equals("\t")? "Tab":
                        "Comma";
    }

    public Format[] getUsedFormats() {
        return usedFormats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileFormat that = (FileFormat) o;
        return delimiter.equals(that.delimiter) && Arrays.equals(usedFormats, that.usedFormats);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(delimiter);
        result = 31 * result + Arrays.hashCode(usedFormats);
        return result;
    }

    @Override
    public String toString() {
        return "FileFormat{\n" +
                "delimiter='" + delimiter + '\'' +
                ",\n usedFormats=" + Arrays.toString(usedFormats) +
                '}';
    }

}
