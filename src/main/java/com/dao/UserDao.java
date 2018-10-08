/**
 * 
 */
package com.dao;

import com.model.User;

/**
 * @author virens
 *
 */
public interface UserDao {

	User findByUserName(String username);

}
