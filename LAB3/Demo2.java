import java.util.Date;

public class Demo2 {
    public static void main(String args[]) {
        Date now = new Date();

        SwEngineer swengg1 = new SwEngineer("Khan", 3000, "Equals and HashCode");
        SwEngineer swengg2 = new SwEngineer("Khan", 3000, "Equals and HashCode");
        
        System.out.println("Demo for SwEngineer:");
		System.out.format("Case for 'equals' vs '==': %b vs %b\n", swengg1.equals(swengg2), swengg1==swengg2);
		System.out.format("Compare the two HashCodes: %d and %d\n", swengg1.hashCode(), swengg2.hashCode());
        System.out.println();
		ProjManager pmanag1 = new ProjManager("Bill", 6000, "Equals and HashCode", now);
		ProjManager pmanag2 = new ProjManager("Bill", 6000, "Equals and HashCode", now);

        System.out.println("Demo for ProjManager:");
		System.out.format("Case for 'equals' vs '==': %b vs %b\n", pmanag1.equals(pmanag2), pmanag1==pmanag2);
		System.out.format("Compare the two HashCodes: %d and %d\n", pmanag1.hashCode(), pmanag2.hashCode());
		System.out.println();
    }
}
