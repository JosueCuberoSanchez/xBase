package cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.dao.AdministratorDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;

import java.util.Collection;
import java.util.List;

/**
 * Hibernate implementation of the {@link AdministratorDao}.
 *
 * @author Rodrigo A. Bartels
 */

@Repository("administratorDao")
public class HibernateAdministratorDao extends HibernateCrudDao<Administrator, String> implements AdministratorDao {

    @Autowired
    public HibernateAdministratorDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Administrator findUserByUsername(String username){
        String sql = "SELECT * FROM administrators WHERE username = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Administrator.class);
        query.setParameter(0, username);
        Collection administrators = query.list();
        return (Administrator) DataAccessUtils.singleResult(administrators);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Administrator> getAllAdministrators(){
        String sql = "SELECT * FROM administrators";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Administrator.class);
        return (List<Administrator>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Administrator> getAdminByName(String keyword) {
        String sql = "SELECT * FROM administrators WHERE name LIKE ? OR last_name LIKE ? OR second_last_name LIKE ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Administrator.class);
        query.setParameter(0, "%" + keyword + "%");
        query.setParameter(1, "%" + keyword + "%");
        query.setParameter(2, "%" + keyword + "%");
        return (List<Administrator>) query.list();
    }
}
