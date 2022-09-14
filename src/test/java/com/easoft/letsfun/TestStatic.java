package com.easoft.letsfun;

import org.apache.catalina.User;

import com.easoft.letsfun.entity.UserDefinition;

public class TestStatic {

	public static void main(String[] args) {
		UserDefinition user = new UserDefinition();
		
	
		
		if(user.getClass().equals(UserDefinition.class)) {
			System.out.println("classlar esit");
		}

	}

}
