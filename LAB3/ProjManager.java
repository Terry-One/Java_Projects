import java.util.Date;

public class ProjManager extends SwEngineer{
    private Date projDeadline;

    public ProjManager(String name, double baseSalary, String projName, Date projDeadline)
    {
        super(name,baseSalary,projName);
        this.projDeadline = projDeadline;
    }

    public Date getProjDeadline()
    {
        return this.projDeadline;
    }

    public double raiseSalary()
    {
        return getBaseSalary() * 1.24;
    }

    public String PrintInfo() {
		return String.format("ProjManagerInfo --- name: %s , proj_name: %s , final_Salary: %.2f , project_deadline: %s",
							  getName(),getProjName(),raiseSalary(), this.projDeadline);
    }
    

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ProjManager))
			return false;

		ProjManager pmanag = (ProjManager)obj;
		return getName().equals(pmanag.getName()) &&
               getBaseSalary()==pmanag.getBaseSalary() &&
               getProjName().equals(pmanag.getProjName()) &&
               getProjDeadline().equals(pmanag.projDeadline);
	}

    @Override
    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((Double.valueOf(getBaseSalary()) == null) ? 0 : Double.valueOf(getBaseSalary()).hashCode());
        result = prime * result + ((getProjName() == null) ? 0 : getProjName().hashCode());
        result = prime * result + ((this.projDeadline == null) ? 0 : this.projDeadline.hashCode());
        return result;
    }
}
