package logogin.spring;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @created 2008-03-17
 * @author logogin
 */
public abstract class BaseEntityDAO<T> extends HibernateDaoSupport {
    /**
     * Hibernate callback that wraps session with configured filter
     */
    abstract class FilterHibernateCallback<R> implements HibernateCallback<R> {
        public abstract R doAction(Session session) throws HibernateException, SQLException;

        public R doInHibernate(Session session) throws HibernateException, SQLException {
            EntityFilterConfig entityFilterConfig = EntityFilterConfig.getCurrent();

            if (entityFilterConfig != null) {
                for (EntityFilter entityFilter : entityFilterConfig.getFilters()) {
                    org.hibernate.Filter hibernateFilter = session.enableFilter(entityFilter.getName());

                    for (Entry<String, Object> entry : entityFilter.getParameters().entrySet()) {
                        hibernateFilter.setParameter(entry.getKey(), entry.getValue());
                    }
                }
            }

            return doAction(session);
        }
    }

    protected abstract Class<T> getEntityClass();

    public DetachedCriteria getCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }

    public T fetchById(final Serializable id) {
        return getHibernateTemplate().execute(new FilterHibernateCallback<T>() {
            @SuppressWarnings("unchecked")
            public T doAction(Session session) throws HibernateException, SQLException {
                List<T> list = session.createQuery("from " + getEntityClass().getSimpleName() + " where id = :id").setParameter("id", id).list();
                return list.isEmpty() ? null : list.get(0);
            }
        });
    }

    public Integer getTotalRowsCount() {
        return getRowsCount(getCriteria());
    }

    public Integer getRowsCount(final DetachedCriteria criteria) {
        return getHibernateTemplate().execute(new FilterHibernateCallback<Integer>() {
            public Integer doAction(Session session) throws HibernateException, SQLException {
                Criteria executableCriteria = criteria.getExecutableCriteria(session);
                ClassMetadata metadata = session.getSessionFactory().getClassMetadata(getEntityClass());
                executableCriteria.setProjection(Projections.countDistinct(metadata.getIdentifierPropertyName()));
                return (Integer)executableCriteria.uniqueResult();
            }
        });
    }

    public List<T> fetchByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) {
        return getHibernateTemplate().execute(new FilterHibernateCallback<List<T>>() {
            @SuppressWarnings("unchecked")
            public List<T> doAction(Session session) throws HibernateException, SQLException {
                Criteria executableCriteria = criteria.getExecutableCriteria(session);

                if (firstResult >= 0) {
                    executableCriteria.setFirstResult(firstResult);
                }

                if (maxResults > 0) {
                    executableCriteria.setMaxResults(maxResults);
                }

                return executableCriteria.list();
            }
        });
    }

    public List<T> fetchByCriteria(final DetachedCriteria criteria) {
        return getHibernateTemplate().execute(new FilterHibernateCallback<List<T>>() {
            @SuppressWarnings("unchecked")
            public List<T> doAction(Session session) throws HibernateException, SQLException {
                Criteria executableCriteria = criteria.getExecutableCriteria(session);
                return executableCriteria.list();
            }
        });
    }

    public Object fetchByCriteriaUniqueResult(final DetachedCriteria criteria) {
        return getHibernateTemplate().execute(new FilterHibernateCallback<Object>() {
            public Object doAction(Session session) throws HibernateException, SQLException {
                Criteria executableCriteria = criteria.getExecutableCriteria(session);
                return executableCriteria.uniqueResult();
            }
        });
    }

}
