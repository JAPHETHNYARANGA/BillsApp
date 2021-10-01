package dao;

import java.util.List;
import models.*;

public interface UsersDao {

    void add(Users users); //create

    void addBill(Bills bills);

    Users findById(int id); //read by id

    List<Users>getAll(); //read All

    List<Bills>getBillsForUser();

    void update(int id, String username, String password); //not sure about password

    void deleteById(int id); //delete specific user based on id

    void clearAllUsers(); //delete all users
}