package Model.Formats;

import java.util.Hashtable;
import java.util.Objects;

@SuppressWarnings("ALL")
public class NumberFormat extends Format{

    private final String[] digitalSeparators = new String[]{"\\.", ","};
    private final String[] thousandSeparators = new String[]{"\\.", ",", " "};
    private final Hashtable<String, String> separatorNames;

    private String thousandSeparator;
    private String digitalSeparator;

    public NumberFormat(String thousandSeparator, String digitalSeparator) {
        this.thousandSeparator = thousandSeparator;
        this.digitalSeparator = digitalSeparator;
        separatorNames = new Hashtable<>();
        separatorNames.put("\\.", "dot");
        separatorNames.put(",", "comma");
        separatorNames.put(" ", "space");
    }

    public String getThousandSeparator() {
        return thousandSeparator;
    }

    public String getDigitalSeparator() {
        return digitalSeparator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberFormat that = (NumberFormat) o;
        return thousandSeparator.equals(that.thousandSeparator) && digitalSeparator.equals(that.digitalSeparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thousandSeparator, digitalSeparator);
    }

    public String toString() {
        String ans ="NumberFormat : ";
        if (!thousandSeparator.equals("null")&& !digitalSeparator.equals("null"))
            ans += "Thousand Separator->"+this.separatorNames.get(thousandSeparator) + ", Digital Separator ->" + this.separatorNames.get(digitalSeparator);
        else
            ans += "No format for the number";
        return ans;
    }
}


