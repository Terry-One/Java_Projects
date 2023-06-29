package ece325;

/**
 * Lab 4: Generics <br />
 * The {@code GenericTrie} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Trie">
 *              https://en.wikipedia.org/wiki/Trie
 *            </a>
 */
public class GenericTrie<K extends CharSequence, V> 
{
		
    /**
     * Root node of the Prefix Tree
     */	 
    TrieNode<V> root; 
    GenericTrie() { root = new TrieNode<V>(); }

    //add the word into the Prefix Tree
    public void insert(K word, V value) {
        //create a new instance of a current node
        TrieNode<V> node = root;
        for(int i=0; i<word.toString().length(); i++)
        {
            //get the index of the word
            int index = word.charAt(i) -'a';
            //if the word we want is not in the array them insert
            if(node.child[index]==null)
            {
                node.child[index] = new TrieNode<V>();
            }
            node = node.child[index];
        }
        node.isEndOfWord=true;
        node.value = value;
    }
    
    //Search for a word
    public V search(K word) {
    	//create a new instance of a current node
        TrieNode<V> node = root;
 
        for (int i=0; i< word.toString().length(); i++)
        {
            //get the index of the word
            int index = word.charAt(i) -'a';
            //if the node with index above is null, return null
            if (node.child[index]==null)
                return null;
            //change to nex node
            node = node.child[index];
        }
        return node.value;
    }
    
   
    public boolean startWith(K prefix) 
    {
        TrieNode<V> node = root;
        
        for (int i=0; i< prefix.toString().length(); i++)
        {
            //get the index of the word
            int index = prefix.charAt(i) -'a';
            //if the node with index above is null, return null
            if (node.child[index]==null)
                return false;
            //if not null, make that child become node
            node = node.child[index];
        }
        return true;
    }

    public V remove(K word) {

        // perform a search on target word
        // but record the path of the this process
		TrieNode<V>[] path = new TrieNode[word.toString().length()];
		TrieNode<V> node = root;

        for (int i = 0; i < word.toString().length(); i++) 
        {
			int index = word.charAt(i) -'a';
			if (node.child[index]==null)
                return null;
			
			path[i] = node;
			node = node.child[index];
        }
        V val = node.value;
        // remove the word form the bottom to top of the word node
		
		for (int i = path.length - 1; i >= 0; --i) {
			// is node of another word?
			if (path[i].isEndOfWord)
				break;
			
			int branches = 0;
			for (int j = 0; j < TrieNode.ALPHABET_SIZE; ++j) {
				if (path[i].child[j] != null)
					++branches;
			}
		
			if (branches > 1)
				break;
			
            for (int j = 0; j < TrieNode.ALPHABET_SIZE; ++j) 
				path[i].child[j] = null;
        }
        return val;
    }
}


