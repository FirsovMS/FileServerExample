package com.company.dbService.dao;

import com.company.dbService.dataSets.ModulesDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
        ModulesDataSet set =  (ModulesDataSet)(criteria.add(Restrictions.eq("name", name)).uniqueResult());
        return set ;
    }

    public long insert(ModulesDataSet dataSet) throws HibernateException {
        return (long) session.save(dataSet);
    }
}
