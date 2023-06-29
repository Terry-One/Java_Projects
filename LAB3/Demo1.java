import java.util.Date;

public class Demo1 {
    public static void main(String args[]) {
		Date now = new Date();

		Customer	costom = new Customer("Terry", 300);
		HwEngineer	hwengg = new HwEngineer("Sam", 3000);
		ProjManager pmanag = new ProjManager("Bill", 6000, "Inheritance and Interfaces", now);

        System.out.println("Display base & raised salary for HwEngineer and ProjManager:");
        System.out.format("HwEngineer --- name: %s , base_salary: %.2f , final_salary: %.2f\n", 
                                        hwengg.getName(), hwengg.getBaseSalary(), hwengg.raiseSalary());
        System.out.format("ProjManager --- name: %s , base_salary: %.2f , final_salary: %.2f\n", 
                                        pmanag.getName(), pmanag.getBaseSalary(), pmanag.raiseSalary());                                       
        System.out.println();

        System.out.println("Print Infomation:");
		System.out.println(costom.PrintInfo());
        System.out.println(pmanag.PrintInfo());
        System.out.println();
    }
}
