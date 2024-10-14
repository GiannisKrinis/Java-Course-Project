public class Gaming extends ElectronicDevice {
    //Instance Attributes
    private final String TYPE;
    
    //Default Constructor
    public Gaming () {
        super();    //Calls Default Constructor from Superclass
        this.TYPE = null;
    }
    
    //2nd Constructor
    public Gaming(int code, String model, int modelYear, String manufacturer, double price, String type) {
        super(code, model, modelYear, manufacturer, price); //Calls Constructor from Superclass
        this.setDiscount(10);   //Initialization Setter απο την Superclass
        this.TYPE = type;   //Initialization  TYPE
    }
    
    //METHODS
    
    //Getter
    public String getType() {
        return TYPE;    //Returns Gaming's TYPE
    }
    
    //ToString Method
    @Override
    public String toString() {
        return "Type: " + TYPE + '\n'
                + super.toString();
    }
}
