package dao;
//import models.Bills;

import java.sql.Timestamp;

public interface BillsDao {

    void add (Bills bills);

    Bills findById(int id);

    List<Bills>getAll();

    void update(String name, int amount, long dueDate, Timestamp billEntryDate); //review type for due date

    void deleteById(int id);

    void clearAllBills();
}