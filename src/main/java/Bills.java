import java.sql.Timestamp;
import java.util.Date;

public class Bills {
    private String name;
    private int amount;
    private long  date;
    private Timestamp billEntryDate;


    public Bills(String name, int amount, long date, Timestamp billEntryDate) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.billEntryDate = billEntryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
    public Timestamp getBillEntryDate() {
        return billEntryDate;
    }

    public void setBillEntryDate(Timestamp billEntryDate) {
        this.billEntryDate = billEntryDate;
    }
}
