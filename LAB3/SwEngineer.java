public class SwEngineer extends Employee{
    private String projName;

    public SwEngineer(String name, double baseSalary, String projName)
    {
        super(name,baseSalary);
        this.projName = projName;
    }
    public String getProjName()
    {
        return this.projName;
    }

    @Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SwEngineer))
			return false;

		SwEngineer swengg = (SwEngineer)obj;
		return getName().equals(swengg.getName()) &&
			   getBaseSalary() == (swengg.getBaseSalary()) &&
			   projName.equals(swengg.projName);
	}

    @Override
    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((Double.valueOf(getBaseSalary()) == null) ? 0 : Double.valueOf(getBaseSalary()).hashCode());
        result = prime * result + ((getProjName() == null) ? 0 : getProjName().hashCode());
        return result;
    }
}
