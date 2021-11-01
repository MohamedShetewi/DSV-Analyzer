package Model.Formats;

import java.util.Objects;
@SuppressWarnings("All")
public class StringFormat extends Format {

    private String string;

    public StringFormat(String string) {
        this.string = string;
    }


    @Override
    public String toString() {
        return "String";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringFormat that = (StringFormat) o;
        return string.equals(that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
