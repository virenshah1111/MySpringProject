/**
 * 
 */
package com.service;

import java.util.List;

import com.model.ToDo;

/**
 * @author virens
 *
 */
public interface ToDoService {

	void save(ToDo toDo);

	List<ToDo> update(List<ToDo> toDoList, List<Long> idList);

	List<ToDo> getAll();
	List<ToDo> getByIds(List<Long> ids);

	void delete(List<Long> ids);

}
