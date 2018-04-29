/* EE422C Project 7 submission by
 * Donald Maze-England
 * dsm2588
 * Jennifer Sin
 * js45246
 * Slip days used: 0
 * Spring 2018
 */

package assignment7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int wordPairs =  Integer.parseInt(args[1]);
		
		LinkedHashMap<String , Set<File> > mapOfFiles = new LinkedHashMap<String , Set<File>>();
		
		File dir =new File(args[0]);
		
		for (File file : dir.listFiles()) {
			
			String tempTest = new String();
			Scanner scan = new Scanner (new BufferedReader (new FileReader(file)));
			scan.useDelimiter("\\s+");
			LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
			
			//add the first word chunck in the file to the queue
			for(int i = 0; i < wordPairs; i++) {
				if(scan.hasNext()) {
					queue.add(removePunc(scan.next()));
				}
			}
			
			if (mapOfFiles.containsKey(queToString(queue))) { //if the map already has this word chunk then add the file to the file set in the map
				mapOfFiles.get(queToString(queue)).add(file);
			}
			else{
				HashSet<File> tempSet = new HashSet <File>(); //if this word chunk is not in the map then add it
				tempSet.add(file);
				mapOfFiles.put(queToString(queue), tempSet);
			}
			
			
			//add all the word chunks in the file to the map
			while(scan.hasNext()) {
				queue.remove();
				queue.add(removePunc(scan.next()));
				if (mapOfFiles.containsKey(queToString(queue))) {
					mapOfFiles.get(queToString(queue)).add(file);
				}
				else{
					HashSet<File> tempSet = new HashSet <File>();
					tempSet.add(file);
					mapOfFiles.put(queToString(queue), tempSet);
				}
				
			}
			
			scan.close();
		}
		
		//printMap(mapOfFiles);
		//System.out.println(mapOfFiles.size());

		ArrayList<File> files = new ArrayList<File>();
		for (File file : dir.listFiles()) {
			files.add(file);
		}
		
		int [][] similarities = fileOccurance(mapOfFiles, files, files.size());
		//print2D(similarities);
		
		printCheaters(similarities, files, Integer.parseInt(args[2]));
		System.out.println("Size of the map of word chunks: " + mapOfFiles.size());
	}
	
	
	
	
	/**
	 * This function prints a 2d matrix to the console
	 * @param mat: the matri to be printed
	 */
	 public static void print2D(int mat[][])
	    {
	        // Loop through all rows
	        for (int i = 0; i < mat.length; i++) {
	 
	            // Loop through columns
	            for (int j = 0; j < mat[i].length; j++) {
	                System.out.print(mat[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
	
	
	
	

	/**
	 *This is a helper function that will take the queue containing the words
	 *and will convert it to a string
	 * @param wordSet: is a queue of words from the document to process into a string
	 * @return String: of the queue of words 
	 */
	
	public static String queToString(Queue<String> wordSet) {
		String nWordString = "";
		
		for (String str : wordSet) {
			nWordString += str;
		}
		
		return nWordString;
		
	}
	
	
	
	
	
	
	/**
	 * This method will remove all characters that are not letters or numbers from a given string
	 * @param nWordString: is the string of words with possible punctuation 
	 * @return a string with no other characters besides alpha numeric 
	 */
	public static String removePunc(String nWordString) {
		String outputString="";
		for(int i =0; i < nWordString.length(); i++) {
			int asciiVal =(int)nWordString.charAt(i);
			if(!((asciiVal >= 33 && asciiVal <= 47) || (asciiVal >=58 && asciiVal <= 64)||( asciiVal >=91 && asciiVal <=96)||(asciiVal>= 123 && asciiVal <= 127))) {
				outputString+= nWordString.charAt(i);
			}
		}
		return outputString;	
	}
	
	
	
	
	/**
	 * prints out the map keys and the set contents associated with that key on the console 
	 * @param mapper: is a hashmap of the nWord keys and the set of files associated with that key
	 */
	public static void printMap(HashMap<String, Set<File>> mapper) {
		Set<String> keys = mapper.keySet();
		for (String key : keys) {
			System.out.print(key + ": ");
			printSet(mapper.get(key));
			System.out.println();
		}
	}
	
	
	/**
	 * this method is called by printMap to help print the files of the set
	 * @param files is the directory of file 
	 */
	public static void printSet(Set<File> files) {
		for (File file : files) {
			System.out.print(file.getName() + " ::: ");
		}
	}
	
	
	
	
	/**
	 * This method takes a map containing word chunks as keys and sets of files that were found to contain those word chunks as values. It also
	 * takes an ArrayList of files and uses the indexes of the files in the ArrayList as the indexes of a 2d matrix. The matrix represents
	 * the number of similar word chunks between files.
	 * @param map:	the map containing word chunks and sets of files
	 * @param files:	the ArrayList containing files
	 * @param numFiles:	the number of files being compared
	 * @return	A 2d matrix representing the number of similar word chunks between files
	 */
	public static int[][] fileOccurance(HashMap<String, Set<File>> map, ArrayList<File> files, int numFiles) {
		Set <String> keys = map.keySet();
		int [][] same = new int[numFiles][numFiles];
		
		for (String key : keys) {
			for (File mapFile1 : map.get(key)) {
				int row = files.indexOf(mapFile1);
				for (File mapFile2 : map.get(key)) {
					if (!mapFile1.equals(mapFile2)){
						int col = files.indexOf(mapFile2);
						same[row][col] += 1;
					}
				}
			}
		}
		
		return same; 
	}
	
	
	
	
	/**
	 * prints the file names of the files with similar word chunks above the limit
	 * @param similarities:	A 2d matrix representing which files had similarities with other files
	 * @param files:	An ordered list of files used to map the file objects to corresponding locations in the 2d matrix
	 * @param limit:	files with numbers of similar word chunks below this limit will not be printed.
	 */
	public static void printCheaters(int[][] similarities, ArrayList<File> files, int limit) {
		System.out.println("Cheaters above " + limit + ":");
		int filesSize = files.size();
		for (int i = 1; i < filesSize; i++) {
			for (int j = 0; j < i; j++) {
				if (similarities[j][i] > limit) {
					System.out.println("The following files cheated::::  " + files.get(j).getName() + "  " + files.get(i).getName() + " ::::with hits:::: " + similarities[j][i]);
				}
			}
		}
	}
	
	
	
}
