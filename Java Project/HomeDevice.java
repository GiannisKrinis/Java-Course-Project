public class HomeDevice extends ElectronicDevice {
    //Instance Attribute
    private final String ENERGY_CLASS;
    
    //Default Constructor
    public HomeDevice () {
        super();    //Calls Default Constructor from Superclass
        this.ENERGY_CLASS = null;
    }
    
    //2nd Constructor
    public HomeDevice (int code, String model, int modelYear, String manufacturer, double price, String energyClass) {
        super(code, model, modelYear, manufacturer, price);     //Calls Constructor from Superclass
        this.setDiscount(20);   //Initialization SuperClass's Setter
        this.ENERGY_CLASS = energyClass;    //Initialization ENERGY_CLASS
    }
    
    //METHODS
    
    //Getter
    public String getEnergyClass() {
        return ENERGY_CLASS;     //Returns Home Device's ENERGY_CLASS
    }
    
    //ToString Method
    @Override
    public String toString() {
        return "Type: " + ENERGY_CLASS + '\n'
                + super.toString();
    }
}
