import dao.BillsDao;
import dao.UsersDao;
import org.eclipse.jetty.server.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static spark.Spark.*;

public class App {
    private static BillsDao billsDao;
    private static UsersDao usersdao;
    private static Sql2o sql2o;
    private static URI dbUri;
    private static Logger logger = LoggerFactory.getLogger(App.class);
    private static Gson gson = new Gson();
    private  static Connection con;

    public static void main(String[] args) {


        ProcessBuilder process = new ProcessBuilder();

        Integer port = (process.environment().get("PORT") != null) ?
                Integer.parseInt(process.environment().get("PORT")):7654;
        port(port);

        String connectionStr="jdbc:postgresql://localhost:5432/billsapp";

        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("");
                sql2o = new Sql2o(connectionStr,"japhethnyaranga","34120648");

            } else {

                dbUri = new URI(System.getenv("DATABASE_URL"));
                int dbport = dbUri.getPort();
                String host = dbUri.getHost();
                String path = dbUri.getPath();
                String username = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
                String password = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[1];
                sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + dbport + path, username, password);
            }

        } catch (URISyntaxException e ) {
            logger.error("Unable to connect to database.");
        }

        con = sql2o.open();


        billsDao = new BillsDao(sql2o);
        UsersDao = new UsersDao(sql2o);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            return new ModelAndView(new HashMap(), "login.hbs");
        }, new HandlebarsTemplateEngine());

        get("/register", (request, response) -> {
            return new ModelAndView(new HashMap(), "register.hbs");
        }, new HandlebarsTemplateEngine());
        get("/home", (request, response) -> {
            return new ModelAndView(new HashMap(), "home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Notifications", (request, response) -> {
            return new ModelAndView(new HashMap(), "Notifications.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
