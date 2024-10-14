public class MobileConsole extends Console {
    
    //Default Constructor
    public MobileConsole () {
        super();    //Calls Default Constructor from Superclass
    }
    
    //2nd Constructor
    public MobileConsole(int code, String model, int modelYear, String manufacturer, double price, String type, String processor, String graphics, String sound, int hardDriveSize) {
        super(code, model, modelYear, manufacturer, price, type, processor, graphics, sound, hardDriveSize);     //Calls  Constructor from Superclass
    }
}
