package dao;

import java.util.List;
import models.*;

public interface UsersDao {

    void add(Users users); //create

    Users findById(int id); //read by id

    List<Users>getAll(); //read All

    void update(int id, String userName, String password); //not sure about password

    void deleteById(int id); //delete specific user based on id

    void clearAllUsers(); //delete all users
}