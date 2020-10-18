package com.codecool.krk;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Guestbook implements HttpHandler {


    public ConnectionToDB connectionToDB = new ConnectionToDB();
    public List<Visitor> visitors;
    public VisitorsDAO visitorsDAO = new VisitorsDAO(connectionToDB);


    public Guestbook() throws SQLException {
        this.visitors = visitorsDAO.selectAllVisit();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/gbook.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("visitors", visitors);

        String response = "";

        if (method.equals("GET")) {
            response = template.render(model);
        }


        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            long millis = System.currentTimeMillis();
            Date date = new Date(millis);

            String name = formData.split("&")[0].split("=")[1];
            String surname = formData.split("&")[1].split("=")[1];
            String message = formData.split("&")[2].split("=")[1].replace("+", " ");
            Visitor visitor = new Visitor(name, surname, message, date);

            visitorsDAO.addNewVisitor(visitor);
            visitors.add(visitor);
            System.out.println("visitor added");


            System.out.println(name);
            response = template.render(model);


        }


        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
