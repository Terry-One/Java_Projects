/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Animal} interface
 */
interface Animal {
    /**
     * An animal speaks
     * @return              {@code String} animal speaks
     */
    public String speak ();
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Lion} class
 */
class Lion implements Animal {
    /**
     * The lion speaks
     * @return              {@code String} lion speaks
     */
    public String speak() {
        return "ROAR";
    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Mouse} class
 */
class Mouse implements Animal {
    /**
     * The mouse speaks
     * @return              {@code String} mouse speaks
    */
    public String speak() {
        return "SQUEAK";
    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Bison} class
 */
class Bison implements Animal {
    /**
     * The bison speaks
     * @return              {@code String} bison speaks
     */
    public String speak() {
        return "BELLOW";
    }
}

class Dog implements Animal {
    /**
     * The dog speaks
     * @return              {@code String} dog speaks
     */
    public String speak() {
        return "WOOF";
    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code AnimalType} class
 */

class AnimalType {
    public static  String[] criterias;
    public static  String[] animals;

    public static String[] add_element(String myarray[], String str) { 
        int n = myarray.length;
        String newArray[] = new String[n + 1]; 
        //copy original array into new array
        for (int i = 0; i<n; i++) 
            newArray[i] = myarray[i]; 
 
        //add element to the new array
        newArray[n] = str; 
 
        return newArray; 
    } 
    public static String[] remove_element(int index, String myarray[]) { 
        int n = myarray.length;
        String[] newArray = new String[n - 1]; 
 
        // copy all the elements in the original to proxy array except the one at index 
        for (int i = 0, k = 0; i<n; i++) { 
            // check if index is crossed, continue without copying 
            if (i == index) { 
                continue; 
            } 
            // else copy the element
            newArray[k++] = myarray[i]; 
        }
        return newArray;
    } 
    
    /**
     * Create and return an animal
     * @param criteria      {@code String} how is the animal like
     * @return              {@code Animal} the animal
     */
    public static Animal getAnimal(String criteria) {
        // TODO: Lab 6 Part 2-1 -- Refactor this method
        // find the index of the criteria entered
        int index = java.util.Arrays.asList(criterias).indexOf(criteria);
        if (index == -1) return null;

        Animal animal = null;
        try {
            //no need for an constrcutor
        	Class<?> clazz = Class.forName(animals[index]);
            animal = (Animal) clazz.newInstance();
           
        } 
        catch(ClassNotFoundException e) 
        {
            System.out.println("Class "+animals[index]+" not found");
        }
        catch (Exception e) 
        {
            System.out.println("New instance of "+animals[index]+" was not created");
        }
        return animal;
    }
}
/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code JavaDPExample} class
 */
public class JavaDPExample {
    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    
    public static void main(String[] args) {
        AnimalType.criterias = new String[] {"small", "big", "lazy"};
        AnimalType.animals = new String[] {"Mouse", "Bison", "Lion"};

        Animal small = AnimalType.getAnimal("small");
        System.out.println(small.getClass().getName() + " speaks: " + small.speak());
        Animal big = AnimalType.getAnimal("big");
        System.out.println(big.getClass().getName() + " speaks: " + big.speak());
        Animal lazy = AnimalType.getAnimal("lazy");
        System.out.println(lazy.getClass().getName() + " speaks: " + lazy.speak());

        // TODO: Lab 6 Part 2-2 -- add an animal "Dog" here: criteria="loyal"; speak="woof"
        AnimalType.criterias = AnimalType.add_element(AnimalType.criterias,"loyal");
        AnimalType.animals = AnimalType.add_element(AnimalType.animals,"Dog");

        Animal loyal = AnimalType.getAnimal("loyal");
        System.out.println(loyal.getClass().getName() + " speaks: " + loyal.speak());

        // TODO: Lab 6 Part 2-3 -- remove the "small" animal here: Mouse
        AnimalType.criterias = AnimalType.remove_element(0,AnimalType.criterias);
        AnimalType.animals = AnimalType.remove_element(0,AnimalType.animals);
        try {
            small = AnimalType.getAnimal("small");
            System.out.println(small.getClass().getName() + " speaks: " + small.speak());
        } catch (Exception e) {
            System.out.println("Unkwon animal...");
        }
    }
}
