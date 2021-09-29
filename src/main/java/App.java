import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

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

        get("/favorite_photos", (request, response) -> {
            return new ModelAndView(new HashMap(), "Notifications.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
