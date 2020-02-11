package com.company.Services.servlets;

import com.company.Services.DdService.DBException;
import com.company.Services.DdService.DBService;
import com.company.Services.DdService.dataSets.ModulesDataSet;
import com.google.gson.Gson;

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
                          HttpServletResponse response) throws IOException {
        String query = request.getParameter("query");

         if(query == null) {
             response.setContentType("text/html;charset=utf-8");
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         }
         // send all info about modules
         if(query == "give_all_modules") {
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
