/**
 * 
 */
package com.dao;

import java.util.List;

import com.model.ToDo;

/**
 * @author virens
 *
 */
public interface ToDoDao {

	void save(ToDo toDo);

	ToDo update(ToDo toDo);
	
	List<ToDo> update(List<ToDo> toDo);

	List<ToDo> getAll();
	List<ToDo> getByIds(List<Long> ids);

	void delete(List<Long> ids);

}
