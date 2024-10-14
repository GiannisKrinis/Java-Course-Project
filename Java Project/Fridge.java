public class Fridge extends HomeDevice {
    //Instance Attributes
    private final String TYPE;
    private final double PRESERVATION_STORAGE;
    private final double FREEZER_STORAGE;
    
    //Default Constructor
    public Fridge () {
        super();    //Calls Default Constructor from Superclass
        this.FREEZER_STORAGE = 0;
        this.PRESERVATION_STORAGE = 0;
        this.TYPE = null;
    }
    
    //2nd Constructor
    public Fridge(int code, String model, int modelYear, String manufacturer, double price, String energyClass, String type, double preservationStorage, double freezerStorage) {
        super(code, model, modelYear, manufacturer, price, energyClass);     //Calls Constructor from Superclass
        this.TYPE = type;   //Initialization TYPE
        this.PRESERVATION_STORAGE = preservationStorage;    //Initialization PRESERVATION_STORAGE
        this.FREEZER_STORAGE = freezerStorage;   //Initialization FREEZER_STORAGE
    }
    
    //MEHODS
    
    //Getter
    public String getType() {
        return TYPE;    //Return's fridge's TYPE
    }
    
    //Getter
    public double getPreservationStorage() {
        return PRESERVATION_STORAGE; //Return's fridge's PRESERVATION_STORAGE
    }
    
    //Getter
    public double getFreezerStorage() {
        return FREEZER_STORAGE;  //Return's fridge's FREEZER_STORAGE
    }
    
    //ToString Method
    @Override
    public String toString() {
        return "Type: " + TYPE +
                "\nPreservation Storage: " + PRESERVATION_STORAGE +
                "\nFreezer Storage: " + FREEZER_STORAGE +
                "\n" + super.toString();
    }
}
