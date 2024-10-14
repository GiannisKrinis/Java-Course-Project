public class Camera extends Audiovisual {
    //Instance Attributes
    private final int MEGAPIXELS;
    private final double ZOOM;
    private final double DIGITAL_ZOOM;
    private final double SCREEN_SIZE;
    
    //Defaut Constructor
    public Camera () {
        super();    //Calls Default Constructor from Superclass
        this.DIGITAL_ZOOM = 0;
        this.ZOOM = 0;
        this.MEGAPIXELS = 0;
        this.SCREEN_SIZE = 0;
    }
    //2nd Constructor
    public Camera(int code, String model, int modelYear, String manufacturer, double price, String type, int megapixels, double zoom, double digitalZoom, double screenSize) {
        super(code, model, modelYear, manufacturer, price, type);    //Calls Constructor from Superclass
        this.MEGAPIXELS = megapixels;   //Initialization MEGAPIXELS
        this.ZOOM = zoom;   //Initialization ZOOM
        this.DIGITAL_ZOOM = digitalZoom;    //Initialization DIGITAL_ZOOM
        this.SCREEN_SIZE = screenSize;  //Initialization SCREEN_SIZE
    }
    
    //METHODS
    
    //Getter
    public int getMegapixels() {
        return MEGAPIXELS;  //returns Camera's MEGAPIXELS
    }
    
    //Getter
    public double getZoom() {
        return ZOOM;    //return's Camera's ZOOM
    }
    
    //Getter
    public double getDigitalZoom() {
        return DIGITAL_ZOOM;    //return's Camera'sDIGITAL_ZOOM
    }
    
    //Getter
    public double getScreenSize() {
        return SCREEN_SIZE;     //return's Camera's SCREEN_SIZE
    }
    
    //ToString Method
    @Override
    public String toString() {
        return  "Megapixels: " + MEGAPIXELS +
                "\nZoom: " + ZOOM +
                "\nDigital Zoom: " + DIGITAL_ZOOM +
                "\nScreen Size: " + SCREEN_SIZE +
                "\n" + super.toString();
    }
}
