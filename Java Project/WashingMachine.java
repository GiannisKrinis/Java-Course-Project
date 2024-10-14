public class WashingMachine extends HomeDevice {
    // Instance Attributes
    private final int RPM;
    private final double CAPACITY;
    
    //Constructor
    public WashingMachine(int code, String model, int modelYear, String manufacturer, double price, String energyClass, int rpm, double capacity) {
        super(code, model, modelYear, manufacturer, price, energyClass); //Calls constructor from Superclass
        this.RPM = rpm;     //Initialization RPM
        this.CAPACITY = capacity;   //Initialization CAPACITY
    }
    //METHODS
    //Getter
    public int getRpm() {
        return RPM;     //returns device's RPM 
    }
    //Getter
    public double getCapacity() {
        return CAPACITY;    //returns device's CAPACITY
    }
    

    // ToString Method
    @Override
    public String toString() {
        return "Capacity: " +CAPACITY +
                "\nRPM: " + RPM +
                "\n" + super.toString();
    }
}
