package Model.Formats;

import java.util.Objects;

@SuppressWarnings("All")

public class DateFormat extends Format {


    private String format;

    public DateFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "Date : " +
                this.format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateFormat that = (DateFormat) o;
        return format.equals(that.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(format);
    }
}
