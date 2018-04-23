package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		
		
		/*
		File dir = new File(args[1]);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		  
		    	try {
					Scanner sc = new Scanner(child);
				} 
		    	catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		    }
		  
		  } 
		  
		  else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
		
		*/
	}

}
