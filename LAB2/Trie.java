
/**
 * Lab 2: Debugging with an IDE and Prefix Tree)
 * @author Terry Wan (1586035)
 */

class Trie {
	
	static final int ALPHABET_SIZE = 26;
	
	static class TrieNode 
	{
	        TrieNode[] child = new TrieNode[ALPHABET_SIZE];
	        boolean isEndOfWord;
	        
			TrieNode()
			{ 
				isEndOfWord = false; 
				for (int i = 0; i < ALPHABET_SIZE; i++) 
					child[i] = null; 
			}
	        
	    }
    /**
     * Root node of the Prefix Tree
     */	 
	static TrieNode root; 

    
    //Insert words into treez
	public static void insert(String word) 
	{
		TrieNode node = root;
		
		//iterate through all characters in the word, get the index and insert to current node
		for (int i = 0; i < word.length(); i++) 
		{
			// a is the first character in the alphabate, index will juet be char - a
			int index = word.charAt(i) - 'a';

			//if the character doesn't exist
			if(node.child[index] == null)
			{
				node.child[index] = new TrieNode();
			}
			//go to next node
			node = node.child[index];
		}
		//the iteration is done
		node.isEndOfWord = true;  
    }
    
	//search the words in the tree
	public static boolean search(String word) 
	{
		TrieNode node = root;

		//iterate through all character and get the indexs
		for (int i = 0; i < word.length(); i++) 
		{
			//get hte index 
			int index = word.charAt(i) - 'a';

			//return false if not found
			if(node.child[index] == null)
			{
				return false;
			}

			//change to next node
			node = node.child[index];
		}
    	return true;
    }
    
    // check whether the tree has hte start of given prefix
	public static boolean startWith(String prefix) 
	{
		TrieNode node = root;

		//iterate through the prefix
		for (int i = 0; i < prefix.length(); i++) 
		{
			//get hte index 
			int index = prefix.charAt(i) - 'a';

			//return false if not found
			if(node.child[index] == null)
			{
				return false;
			}

			//change to next node
			node = node.child[index];
		}
    	return true;
    }

    public static void main(String args[]) 
	{ 
		
		String words[] = {"ece", "lab", "java", "jar", "car", 
						"cat", "care", "laboratory", "ebook"}; 
	
		String output[] = {"is NOT in the prefix tree", "is in the prefix tree"}; 
	
	
		root = new TrieNode(); 
	
		// Construct trie 
		int i; 
		for (i = 0; i < words.length ; i++) 
			insert(words[i]); 
	
		// Search for different keys 
		if(search("lab") == true) 
			System.out.println("lab --- " + output[1]); 
		else System.out.println("lab --- " + output[0]); 
		
		if(search("java") == true) 
			System.out.println("java --- " + output[1]); 
		else System.out.println("java --- " + output[0]); 
		
		if(startWith("eced") == true) 
			System.out.println("eced --- " + output[1]); 
		else System.out.println("eced --- " + output[0]); 
		
		if(startWith("ca") == true) 
			System.out.println("ca --- " + output[1]); 
		else System.out.println("ca --- " + output[0]); 
		
		if(search("book") == true) 
			System.out.println("book --- " + output[1]); 
		else System.out.println("book --- " + output[0]); 
		
	} 


}


