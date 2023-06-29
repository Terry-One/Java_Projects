public class HwEngineer extends Employee{
    public HwEngineer(String name, double baseSalary)
    {
        super(name,baseSalary);
    }
    public double raiseSalary()
    {
        return getBaseSalary() * 1.18;
    }
}
