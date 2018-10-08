/**
 * 
 */
package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ToDoDao;
import com.model.ToDo;
import com.service.ToDoService;

/**
 * @author virens
 *
 */
@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoDao toDoDao;

	@Override
	public void save(ToDo toDo) {
		toDoDao.save(toDo);
	}

	@Override
	public List<ToDo> update(List<ToDo> toDoList, List<Long> idList) {
		return toDoDao.update(updateListFromActualData(toDoList, idList));
	}

	@Override
	public List<ToDo> getAll() {
		return toDoDao.getAll();
	}

	@Override
	public List<ToDo> getByIds(List<Long> ids){
		return toDoDao.getByIds(ids);
	}
	
	@Override
	public void delete(List<Long> ids) {
		toDoDao.delete(ids);
	}
	
	private List<ToDo> updateListFromActualData(List<ToDo> toDoList, List<Long> idList) {
		List<ToDo> actalToDoList = toDoDao.getByIds(idList);
		int i = 0;
		for (ToDo toDo : actalToDoList) {
			ToDo toDoInput = toDoList.get(i++);
			toDo.setCompleted(toDoInput.isCompleted());
			toDo.setName(toDoInput.getName());
		}
		return actalToDoList;
	}
	
}