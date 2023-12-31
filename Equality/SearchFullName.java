class FullName
{
  private String firstName;
  private String lastName;

  public FullName(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }


  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof FullName)) {
        return false;
    }
    FullName fullName = (FullName) obj;
    return firstName.equals(fullName.firstName) && lastName.equals(fullName.lastName);
  }

  
  public String toString() {
    return firstName+" "+lastName;
  }
}


public class SearchFullName
{
  public static void report(FullName name, boolean invited) {
    if (invited) {
      System.out.println(name + " is in the list!");
    }
    else {
      System.out.println(name + " was not invited!");        
    }
  }

  public static void main(String[] args) {
    
    FullName[] names = {new FullName("John", "Smith"),
                        new FullName("Dennis", "Ritchie"),             
                        new FullName("Ada", "Lovelace"),
                        new FullName("Charles", "Babbage")};
 
    // FullName p = new FullName("Scott", "Tiger");
    FullName p = new FullName("Ada", "Lovelace");

    boolean invited = false;
    
    //doesn't work as p doesn't have the same reference with names
    for (FullName name : names) {
      if (p==name) {
        invited = true;
      }
    }
    
    report(p, invited);

    // -----------------------

    invited = false;

    //only compares value
    for (FullName name : names) {
      if (p.equals(name)) {
        invited = true;
      }
    }

    report(p, invited);

  }
}