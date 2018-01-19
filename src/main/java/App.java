import java.util.List;
import dao.Sql2oComicDao;
import dao.Sql2oSpecialDao;
import models.Comic;
import models.Special;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        String connectionString = "jdbc:h2:~/standup3.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        Sql2oSpecialDao specialDao = new Sql2oSpecialDao(sql2o);
        Sql2oComicDao comicDao = new Sql2oComicDao(sql2o);


        //initial data:
//        Comic comic1 = new Comic("Doug Stanhope");
//        Comic comic2 = new Comic("Demetri Martin");
//        Comic comic3 = new Comic("Mike Birbiglia");
//        comic1.setDateOfBirth("01.01.1981");
//        comic2.setDateOfBirth("01.01.1981");
//        comic3.setDateOfBirth("01.01.1981");
//        comicDao.add(comic1);
//        comicDao.add(comic2);
//        comicDao.add(comic3);
//
//        Special special1 = new Special("Before turning a gun on himself", 2012, 1, "USA", "eng");
//        Special special2 = new Special("If I", 2004, 2, "USA", "eng");
//        Special special3 = new Special("What I Should Have Said Was Nothing", 2008, 3, "USA", "eng");
//        Special special4 = new Special("No place like home", 2016, 1, "USA", "eng");
//        specialDao.add(special1);
//        specialDao.add(special2);
//        specialDao.add(special3);
//        specialDao.add(special4);


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/specials", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("specials", specialDao.getAll());
//            model.put("comics", comicDao.getAll());

            return new ModelAndView(model, "specials.hbs");
        }, new HandlebarsTemplateEngine());

        get("/comedians", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("comedians", comicDao.getAll());

            return new ModelAndView(model, "comedians.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new_special", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "new_special.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new_special", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");
            int year = Integer.parseInt(req.queryParams("year"));
            int comicId = Integer.parseInt(req.queryParams("comicId"));
            String country = req.queryParams("country");
            String language = req.queryParams("language");
            String description = req.queryParams("description");

            Special special = new Special(name, year, comicId, country, language, description);
            specialDao.add(special);
            model.put("special", special);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new_comic", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "new_comedian.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new_comic", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");
            String dateOfBirth = req.queryParams("dateOfBirth");


            Comic comic = new Comic(name);
            comic.setDateOfBirth(dateOfBirth);
            comicDao.add(comic);
            model.put("comic", comic);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/specials/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int id = Integer.parseInt(req.params("id"));
            Special foundSpecial = specialDao.findById(id);

            model.put("special", foundSpecial);

            Comic comic = comicDao.findById(foundSpecial.getComicId());
            model.put("comic", comic);

            return new ModelAndView(model, "special.hbs");
        }, new HandlebarsTemplateEngine());

        get("/comedians/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int id = Integer.parseInt(req.params("id"));
            Comic foundComic = comicDao.findById(id);

            model.put("comic", foundComic);

            List<Special> specials = specialDao.findByComic(id);

            model.put("specials", specials);

            return new ModelAndView(model, "comedian.hbs");
        }, new HandlebarsTemplateEngine());

        get("/delete/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int id = Integer.parseInt(req.params("id"));
//            Special foundSpecial = specialDao.findById(id);
            specialDao.deleteById(id);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/update/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int id = Integer.parseInt(req.params("id"));
            Special foundSpecial = specialDao.findById(id);

            model.put("special", foundSpecial);



            return new ModelAndView(model, "update.hbs");
        }, new HandlebarsTemplateEngine());

        post("/update/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int id = Integer.parseInt(req.params("id"));
//            Special editSpecial = specialDao.findById(id);

            String name = req.queryParams("name");
            int year = Integer.parseInt(req.queryParams("year"));
            int comicId = Integer.parseInt(req.queryParams("comicId"));
            String country = req.queryParams("country");
            String language = req.queryParams("language");
            String description = req.queryParams("description");

            specialDao.update(id, name, year, comicId, country, language, description);
//            model.put("special", special);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
