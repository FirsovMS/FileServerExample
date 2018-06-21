package com.company.services.servlets;

import com.company.services.dbService.DBException;
import com.company.services.dbService.DBService;
import com.company.services.dbService.dataSets.ModulesDataSet;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModuleInfoServlet extends HttpServlet {
    private final DBService dbService;

    public ModuleInfoServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String querry = request.getParameter("querry");

         if(querry == null) {
             response.setContentType("text/html;charset=utf-8");
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         }
         // send all info about modules
         if(querry == "give_all_modules") {
             try {
                 List<ModulesDataSet> listOfAllModules = dbService.getModuleInfoAll();
                 Gson gson = new Gson();
                 String json = gson.toJson(listOfAllModules);
                 response.setContentType("text/html;charset=utf-8");
                 response.getWriter().println(json);
                 response.setStatus(HttpServletResponse.SC_OK);
             } catch (DBException e) {
                 e.printStackTrace();
             }
         }
    }
}
