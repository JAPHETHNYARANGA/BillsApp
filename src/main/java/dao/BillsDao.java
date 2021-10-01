package dao;

public interface BillsDao {

    void add (Bills bills);

    Bills findById(int id);

    List<Bills>getAll();

    void update(String billName, int amount, date dueDate); //review type for due date

    void deleteById(int id);

    void clearAllBills();
}