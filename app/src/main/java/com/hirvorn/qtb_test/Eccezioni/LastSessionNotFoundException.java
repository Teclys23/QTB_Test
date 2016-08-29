package com.hirvorn.qtb_test.Eccezioni;


public class LastSessionNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LastSessionNotFoundException(){
		System.out.println("Last Session Not Found.");
	}
}
