import java.sql.Timestamp;
import java.util.Date;

public class Bills {
    private String name;
    private int amount;
    private long dueDate;
    private Timestamp billEntryDate;

    public Bills(String name, int amount, long dueDate, Timestamp billEntryDate) {
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
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

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getBillEntryDate() {
        return billEntryDate;
    }

    public void setBillEntryDate(Timestamp billEntryDate) {
        this.billEntryDate = billEntryDate;
    }
}
