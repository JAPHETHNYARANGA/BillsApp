package dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oBillsDao implements BillsDao {
//    private final Sql2o sql2o;
//
//    public Sql2oBillsDao( Sql2o sql2o){
//        this.sql2o = sql2o;
//    }
 Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/billsapp","japhethnyaranga","34120648");
    public void add(Bills bills){
        String sql = "INSERT INTO bills (billName, amount, billEntryDate, dueDate) VALUES (:billName, :amount, now(), dueDate)";
        try(Connection conn = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(bills)
                    .executeUpdate()
                    .getKey();
            users.setId(id);
        }

    }
    @Override
    public findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM bills WHERE id = :id")
                    .executeAndFetchFirst(Bills.class);
        }
    }

    @Override
    public List<Bills> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM bills")
                    .executeAndFetch(Users.class);
        }
    }
    @Override
    public List<Users>getUsersForBill(){
        try(Connection con = sql2o.open()){
            String joinQuery = "SELECT users_id FROM users_bills WHERE bills_id = :bills_id";
            List<Integer> usersIds = con.createQuery(joinQuery)
                    .addParameter("bills_id", this.getId()) //can change to user.getId
                    .executeAndFetch(Integer.class);

            List<Users> users = new ArrayList<Users>();

            for (Integer usersId : usersIds) {
                String usersQuery = "SELECT * FROM users WHERE id = :usersId";
                Users user = con.createQuery(userQuery)
                        .addParameter("usersId", usersId)
                        .executeAndFetchFirst(Users.class);
                users.add(user);
            }
            return users;
        }
    }

    @Override
    public void update(String billName, int amount, date dueDate){
        String sql = "UPDATE users SET (billName, amount, dueDate) = (:billName, :amount, :dueDate) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)//check later for possible error
                    .addParameter("billName", billName)
                    .addParameter("amount", amount)
                    .addParameter("dueDate", dueDate)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

        @Override
        public void deleteById(int id) {
            String sql = "DELETE from bills WHERE id=:id";
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
            String sql = "DELETE from bills";
            try (Connection con = sql2o.open()) {
                con.createQuery(sql).executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }
    }
}