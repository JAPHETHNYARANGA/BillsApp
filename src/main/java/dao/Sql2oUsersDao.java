package dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUsersDao implements UsersDao {
    private final Sql2o sql2o;

    public Sql2oUsersDao( Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public void add(Users users){
        String sql = "INSERT INTO users (userName, password, confirm password) VALUES (:userName, :password, :confirm password)";
        try(Connection conn = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(users)
                    .executeUpdate()
                    .getKey();
            users.setId(id);
        }

    }

    @Override
    public void addBill(Bills bills){
        try(Connection con = sql2o.open()){
            String sql = "INSERT INTO users_bills (users_id, bills_id) VALUES (:users_id, :bills_id)";
            con.createQuery(sql)
                    .addParameter("users_id", this.getId()) //can change to users.getId()
                    .addParameter("bills_id", bills.getId())
                    .executeUpdate();
        }

    }
    public List<Bills> getBillsForUser() {
        try(Connection con = sql2o.open()){
            String joinQuery = "SELECT bills_id FROM users_bills WHERE users_id = :users_id";
            List<Integer> billsIds = con.createQuery(joinQuery)
                    .addParameter("users_id", this.getId()) //can change to user.getId
                    .executeAndFetch(Integer.class);

            List<Bills> bills = new ArrayList<Bills>();

            for (Integer billsId : billsIds) {
                String billsQuery = "SELECT * FROM bills WHERE id = :billsId";
                Bills bill = con.createQuery(billQuery)
                        .addParameter("billsId", billsId)
                        .executeAndFetchFirst(Bills.class);
                bills.add(bill);
            }
            return bills;
        }
    }

    @Override
    public findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE id = :id")
                    .executeAndFetchFirst(Users.class);
        }
    }

    @Override
    public List<Users> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(Users.class);
        }
    }
    @Override
    public void update(String userName, String password){
        String sql = "UPDATE users SET (userName, password) = (:userName, :password) WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)//check later for possible error
                    .addParameter("userName", userName)
                    .addParameter("password", password)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
