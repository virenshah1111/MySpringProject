/**
 * 
 */
package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.ToDo;
import com.service.ToDoService;
import com.util.ResponseUtil;
import com.util.Util;

/**
 * @author virens
 *
 */
@RestController
@RequestMapping(path = "/todo")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	@PostMapping
	public ResponseEntity<ResponseUtil<ToDo>> save(@RequestBody ToDo toDo) {
		toDo.setUser(Util.getPrincipal());
		toDoService.save(toDo);
		return new ResponseEntity<>(new ResponseUtil<ToDo>("Data Saved Successfully."), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<ResponseUtil<List<ToDo>>> update(@PathVariable("id") List<Long> idList, @RequestBody List<ToDo> toDoList) {
		ResponseUtil<List<ToDo>> responseUtil = new ResponseUtil<>();
		responseUtil.setData(toDoService.update(toDoList, idList));
		responseUtil.setMessage("Data Updated Successfully");
		return new ResponseEntity<>(responseUtil, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<ResponseUtil<List<ToDo>>> getAll() {
		ResponseUtil<List<ToDo>> responseUtil = new ResponseUtil<>();
		responseUtil.setData(toDoService.getAll());
		return new ResponseEntity<>(responseUtil, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{ids}")
	public ResponseEntity<ResponseUtil<ToDo>> delete(@PathVariable("ids") List<Long> ids) {
		toDoService.delete(ids);
		return new ResponseEntity<>(new ResponseUtil<ToDo>("Data deleted Successfully."), HttpStatus.OK);
	}

}
