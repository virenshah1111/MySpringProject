/**
 * 
 */
package com.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ToDoDao;
import com.model.ToDo;
import com.util.Util;

/**
 * @author virens
 *
 */
@Repository
@Transactional
public class ToDoDaoImpl implements ToDoDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(ToDo toDo) {
		getSession().save(toDo);
	}

	@Override
	public ToDo update(ToDo toDo) {
		getSession().update(toDo);
		return toDo;
	}

	@Override
	public List<ToDo> update(List<ToDo> toDoList) {
		Session session = getSession();
		for (ToDo toDo : toDoList) {
			session.update(toDo);
		}
		return toDoList;
	}

	@Override
	public List<ToDo> getAll() {
		Criteria criteria = getSession().createCriteria(ToDo.class);
		criteria.add(Restrictions.eq("user.username", Util.getCurrentUsername()));
		criteria.addOrder(Order.asc("completed"));
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}

	@Override
	public List<ToDo> getByIds(List<Long> ids) {
		Criteria criteria = getSession().createCriteria(ToDo.class);
		criteria.add(Restrictions.eq("user.username", Util.getCurrentUsername()));
		criteria.add(Restrictions.in("id", ids));
		return criteria.list();
	}
	
	@Override
	public void delete(List<Long> ids) {
		Query query = getSession().createSQLQuery("delete from todo where id = :id AND username = :username");
		String username = Util.getCurrentUsername();
		for (long id : ids) {
			query.setLong("id", id);
			query.setString("username", username);
			query.executeUpdate();
		}
	}
}
