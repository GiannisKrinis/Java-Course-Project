public class Tv extends Audiovisual {
    //Ιnstance Atributes
    private final String DIMENSIONS;
    private final String PORTS;
    private final String RESOLUTION;
    
    //Default Constructor
    public Tv () {
        super();    //Calls  Default Constructor from SuperClass
        this.DIMENSIONS = null;
        this.PORTS = null;
        this.RESOLUTION = null;
    }
    
    //2nd Constructor
    public Tv(int code, String model, int modelYear, String manufacturer, double price, String type, String dimensions, String ports, String resolution) {
        super(code, model, modelYear, manufacturer, price, type);   //Calls  Constructorfrom Superclass
        this.DIMENSIONS = dimensions;   //Initialization DIMENSIONS
        this.PORTS = ports;  //Initialization PORTS
        this.RESOLUTION = resolution;    //Initialization RESOLUTION
    }
    
    //METHODS
    
    //Getter
    public String getDimensions() {
        return DIMENSIONS;   //Returns TV's DEMENSIONS
    }
    
    //Getter
    public String getPorts() {
        return PORTS;    //Returns TV'sPORTS
    }
    
    //Getter
    public String getResolution() {
        return RESOLUTION;  //Returns TV'sRESOLUTION
    }
    
    //ToString Method
    @Override
    public String toString() {
        return  "Dimensions: " + DIMENSIONS +
                "\nPorts: " + PORTS +
                "\nResolution: " + RESOLUTION +
                "\n" + super.toString();
    }
}
