package com.company.services.dbService.dao;

import com.company.services.dbService.dataSets.ModulesDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ModulesDAO {
    private Session session;

    public ModulesDAO(Session session){
        this.session = session;
    }

    public ModulesDataSet get(long id) throws HibernateException {
        return (ModulesDataSet) session.get(ModulesDataSet.class, id);
    }

    public ModulesDataSet get(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(ModulesDataSet.class);
        return (ModulesDataSet)(criteria.add(Restrictions.eq("name", name)).uniqueResult());
    }

    public List<ModulesDataSet> getAll() throws HibernateException {
        return session.createQuery("from modules").list();
    }

    public long insert(ModulesDataSet dataSet) throws HibernateException {
        return (long) session.save(dataSet);
    }
}
