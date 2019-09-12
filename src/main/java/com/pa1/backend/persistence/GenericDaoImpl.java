package com.pa1.backend.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;


public class GenericDaoImpl<T, ID extends Serializable> implements IGenericDao<T, ID> {

    private Class<T> klass;
    private Session session = null;

    public GenericDaoImpl() {
    }

    public GenericDaoImpl(Class<T> klass) {

        this.klass = klass;
        this.session = HibernateUtil.getSessionFactory().openSession();
    }


    @Override
    public void save(T object) {
        Transaction t = session.beginTransaction();
        session.saveOrUpdate((T) object);
        t.commit();
    }

    @Override
    public void delete(T object) {
        Transaction t = session.beginTransaction();
        session.delete((T) object);
        t.commit();
    }

    @Override
    public T findById(ID id) {
        T object = (T) session.get(klass, id);
        return object;
    }

    @Override
    public List<T> listAll() {
        Transaction t = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(klass);

        Root<T> klassRoot = query.from(klass);



        query.select(klassRoot)
                .where(builder.isNull(klassRoot.get("dataRemocao")));

        List<T> result = session.createQuery(query).getResultList();

        System.out.println(session.isOpen());

        t.commit();

        System.out.println(session.isOpen());
        return result;
    }

    @Override
    public List<T> findAllBy(String property, List<?> values) {
        return findAllBy(property, values, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAllBy(String property, List<?> values, Order order) {
        //this.session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(klass)
                .add(Restrictions.in(property, values));
        if (order != null) {
            criteria.addOrder(order);
        }
        transaction.commit();
        return (List<T>) criteria.list();
    }

    @Override
    public List<T> findAllBy(String property, Object value) {
        return findAllBy(property, new ArrayList<Object>() {
            {
                add(value);
            }
        }, null);
    }

    @Override
    public List<T> findAllBy(String property, Object value, Order order) {
        return findAllBy(property, new ArrayList<Object>() {
            {
                add(value);
            }
        }, order);
    }

    @Override
    public List<T> findAll() {
        return findAll(null);
    }

    @Override
    public List<T> findAll(Order order) {
        //this.session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(klass);
        if (order != null) {
            criteria.addOrder(order);
        }
        transaction.commit();
        return (List<T>) criteria.list();
    }

    @Override
    public List<T> findAllByMulti(Map<String, Object> condition) {
        Criteria criteria = session.createCriteria(klass);
        condition.forEach((k, v) -> {
            if (k != null) {
                criteria.add(Restrictions.eq(k, v));
            }
        });

        return (List<T>) criteria.list();
    }

    @Override
    public T findBy(String property, Object value) {
        List<T> results = findAllBy(property, value);
        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
}
