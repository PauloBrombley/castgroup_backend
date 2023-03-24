package com.castgroup.backend.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.castgroup.backend.model.UserModel;
import com.opengamma.strata.collect.ArgChecker;

public class UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	public UserModel toEntity() {
		UserModel userModel = new UserModel();
        BeanUtils.copyProperties(this, userModel);
        return userModel;
    }

    public static UserDto of(UserModel userModel) {
        ArgChecker.notNull(userModel, "user");
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(userModel, dto);
        return dto;
    }
	
}
