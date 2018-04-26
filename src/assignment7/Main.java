package assignment7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int wordPairs =  Integer.parseInt(args[1]);
		
		HashMap<String , Set<File> > mapOfFiles = new HashMap<String , Set<File>>();
		
		File dir =new File(args[0]);
		
		for (File file : dir.listFiles()) {
			
			Scanner scan = new Scanner (new BufferedReader (new FileReader(file)));
			PriorityQueue<String> queue = new PriorityQueue<String>();
			for(int i = 0; i < wordPairs; i++) {
				if(scan.hasNext()) {
					queue.add(removePunc(scan.next()));
				}
			}
			
			if (mapOfFiles.containsKey(queToString(queue))) {
				mapOfFiles.get(queToString(queue)).add(file);
			}
			else{
				HashSet<File> tempSet = new HashSet <File>();
				tempSet.add(file);
				mapOfFiles.put(queToString(queue), tempSet);
			}
			
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
		printMap(mapOfFiles);
	}
	

	/**
	 *This is a helper function that will take the queue containing the words
	 *and will convert it to a string
	 * @param wordSet is a queue of words from the document to process into a string
	 * @return String of the queue of words 
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
	 * @param nWordString- is the string of words with possible punctuation 
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
	 * @param mapper- is a hashmap of the nWord keys and the set of files associated with that key
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
	 * this method is called by printMapt to help print the files of the set
	 * @param files is the directory of file 
	 */
	public static void printSet(Set<File> files) {
		for (File file : files) {
			System.out.print(file.getPath() + " ::: ");
		}
	}
	/**
	 * This method is passed the map containing the nWord keys and set of files  and the file that is to be checked for number 
	 * of occurrences 
	 * @param map
	 * @param file
	 * @return the number of times that the passed file is occurring in the map 
	 */
	public static int fileOccurance(HashMap<String, Set<File>> map, File file) {
		int same = 0;
		Set <String> keys = map.keySet();
		for(String key : keys) {
			for (File check : map.get(key)) {
				if(check.equals(file)) {
					same++;
				}
			}
			
		}
		
		return same; 
	}
}
