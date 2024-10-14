public class Audiovisual extends ElectronicDevice {
    //Instance Attributes
    private final String TYPE;
    
    //Default Constructor
    public Audiovisual () {
        super();    //Calls Default Constructor from Superclass
        this.TYPE = null;
    }
    //2nd Constructor
    public Audiovisual(int code, String model, int modelYear, String manufacturer, double price, String type) {
        super(code, model, modelYear, manufacturer, price);      //Calls Constructor from Superclass
        this.setDiscount(25);   //Initialization Setter from Superclass
        this.TYPE = type;   //Initialization TYPE
    }
    
    //METHODS
    
    //Getter
    public String getType() {
        return TYPE;    //returns Audiovisual's TYPE
    }
    
    //ToString Method
    @Override
    public String toString() {
        return "Type: " + TYPE + '\n'
                + super.toString();
    }
}
