package ece325;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for GenericTrie
 */
public class GenericTrieDoubleTest 
{

    static String[] words = {"ece", "lab", "java", "jar", "car", "cat", "care", "laboratory", "ebook"};
    static Double[] values = {10.5, 2.0, 3.33, 0.35, -4.0, 42.001, 6.0, -1.02, 45.9}; 
    static double epsilon = 0.000001;

    static GenericTrie<String, Double> trie = new GenericTrie<String, Double>();

    @BeforeClass
    public static void initTrie() 
    {
        for (int i = 0; i < words.length ; i++) 
			trie.insert(words[i], values[i]); 
    }

    @Test
    public void searchLab()
    {   
    	Double res = trie.search("lab");
        assertNotNull("'lab' should be in trie", res);  
        assertEquals(2.0, res, epsilon);
    }

    @Test
    public void searchJava()
    {   
    	Double res = trie.search("java");
        assertNotNull("'java' should be in trie", res);  
        assertEquals(3.33, res, epsilon);
    }

    @Test
    public void startWidthFalse()
    {   
    	assertFalse(trie.startWith("eced"));
    }

    @Test
    public void startWidthTrue()
    {   
        assertTrue(trie.startWith("ca"));
    }
				
    @Test
    public void searchBook()
    {   
    	Double res = trie.search("book");
        assertNull("'book' is not in trie", res); 
    }

    @Test
    public void removeInTrie()
    {   
        trie.remove("jar");
        Double res = trie.search("jar");
        assertNotNull("'jar' should be in trie", res);
        assertEquals(0.35, res, epsilon); 
    }
    
    @Test
    public void removeNotInTrie()
    {   
    	Double res = trie.remove("capitan");
        assertNull("'capitan' should not be in trie", res);
    }


}
