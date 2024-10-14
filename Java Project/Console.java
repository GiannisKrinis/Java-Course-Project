public class Console extends Gaming {
    //Instanve Attributes
    private final String PROCESSOR;
    private final String GRAPHICS;
    private final String SOUND;
    private final int HARD_DRIVE_SIZE;
    
    //Default Constructor
    public Console () {
        super();     //Calls Default Constructor from Superclass
        this.GRAPHICS = null;
        this.HARD_DRIVE_SIZE = 0;
        this.PROCESSOR = null;
        this.SOUND = null;
    }
    
    //2nd Constructor
    public Console(int code, String model, int modelYear, String manufacturer, double price, String type, String processor, String graphics, String sound, int hardDriveSize) {
        super(code, model, modelYear, manufacturer, price, type);   //Calls Constructor from Superclass
        this.PROCESSOR = processor; //Initialization PROCESSOR
        this.GRAPHICS = graphics;   //Initialization  GRAPFICS
        this.SOUND = sound; //Initialization  SOUND
        this.HARD_DRIVE_SIZE = hardDriveSize;   //Initialization  HARD_DRIVE_SIZE
    }
    
    //METHODS
    
    //Getter
    public int getHardDriveSize() {
        return HARD_DRIVE_SIZE; //Returns Console's HARD_DRIIVE_DISK
    }
    
    //Getter
    public String getSound() {
        return SOUND;   //Returns Console's SOUND
    }
    
    //Getter
    public String getGraphics() {
        return GRAPHICS;    //Returns Console's GRAPHICS
    }
    
    //Getter
    public String getProcessor() {
        return PROCESSOR;   //Returns Console's  PROCESSOR
    }
    
    //ToString Method
    @Override
    public String toString() {
        return "Processor: " + PROCESSOR +
                "\nGraphics: " + GRAPHICS +
                "\nSound: " + SOUND +
                "\nHard Drive Size: " + HARD_DRIVE_SIZE +
                "\n" + super.toString();
    }
}
