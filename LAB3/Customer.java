public class Customer extends Person{
    private double projPrice;

    public Customer(String name,double projPrice)
    {
        super(name);
        this.projPrice = projPrice;
    }
    public double getProjPrice()
    {
        return this.projPrice;
    }
    public String PrintInfo() {
		return String.format("CustomerInfo --- name: %s , proj_price: %f",
							  getName(), this.projPrice);
	}
}
