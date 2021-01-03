package com.modelgenerated.authentication;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Utility to encrypt passwords.
 * Does and SHA hash and then base64 encodes the results. 
 * 
 * @author kevin
 */
public class PasswordUtil {
    public static String encryptPassword(String password) {
	    MessageDigest md = null;
	    try {
			md = MessageDigest.getInstance("SHA");
	        md.update(password.getBytes("UTF-8")); 

	        byte raw[] = md.digest(); 
		    String hash = Base64.getEncoder().encodeToString(raw); 
		    return hash;    	
	    } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
    }

}
