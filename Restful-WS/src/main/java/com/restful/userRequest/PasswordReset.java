package com.restful.userRequest;

public class PasswordReset {
	
      private String email;
      
      
      public PasswordReset() {
    	  
      }

      public PasswordReset(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
      
      

}
