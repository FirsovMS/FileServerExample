package com.company.Services.DdService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import com.company.Services.DdService.dao.ModulesDAO;
import com.company.Services.DdService.dataSets.ModulesDataSet;

public class DBService {
    private SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getH2Config();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        // apply session
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        // create service context
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    // Setting up a H2 db configuration
    private Configuration getH2Config() {
        Configuration configuration = new Configuration();
        // add entity class
        configuration.addAnnotatedClass(ModulesDataSet.class);
        // set connection params
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "admin");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    public String getConnectionInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            StringBuilder message = new StringBuilder();
            message.append("database name: " + connection.getMetaData().getDatabaseProductName());
            message.append("database version: " + connection.getMetaData().getDatabaseProductVersion());
            message.append("Driver: " + connection.getMetaData().getDriverName());
            message.append("Autocommit: " + connection.getAutoCommit());
            return message.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ModulesDataSet getModuleInfo(Long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            ModulesDAO dao = new ModulesDAO(session);
            ModulesDataSet set = dao.get(id);
            session.close();
            return set;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public ModulesDataSet getModuleInfo(String name) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            ModulesDAO dao = new ModulesDAO(session);
            ModulesDataSet dataSet = dao.get(name);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public List<ModulesDataSet> getModuleInfoAll() throws DBException {
        try {
            Session session = sessionFactory.openSession();
            ModulesDAO dao = new ModulesDAO(session);
            session.close();
            return dao.getAll();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addModuleInfo(ModulesDataSet dataSet) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            ModulesDAO dao = new ModulesDAO(session);
            long serial_id = dao.insert(dataSet);
            transaction.commit();
            session.close();
            return serial_id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
}
