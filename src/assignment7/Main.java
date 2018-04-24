package assignment7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int wordPairs =  Integer.parseInt(args[1]);
		
		Map<String , Set<File> > mapOfFiles = new HashMap<String , Set<File>>();
		
		File dir =new File("C:\\Users\\ronbe\\Downloads\\sm_doc_set.zip\\sm_doc_set");
		
		for (File file : dir.listFiles()) {
			
			Scanner S = new Scanner (new BufferedReader (new FileReader(dir)));
			
			
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
	
	public Map<String , Set<File> > docMap(File original, int n){
		Map<String, Set<File> > outputMap = null;
		
		
		
		
		return outputMap;
	}
	
	
	
}
