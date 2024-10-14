public class Dvd extends Audiovisual {
    //Instance Attributes
    private final String PLAYBACK_FORMAT;
    private final String RESOLUTION;
    
    //Default Constructor
    public Dvd () {
        super();    //Calls Default Constructor from Superclass
        this.PLAYBACK_FORMAT = null;
        this.RESOLUTION = null;
    }
    
    //2nd Constructor
    public Dvd(int code, String model, int modelYear, String manufacturer, double price, String type, String playbackFormat, String resolution) {
        super(code, model, modelYear, manufacturer, price, type);    //Calls  Constructor from Superclass
        this.PLAYBACK_FORMAT = playbackFormat;  //Initialization PLAYBACK_FORMAT
        this.RESOLUTION = resolution;   //Initialization RESOLUTION
    }
    
    //METHODS
    
    //Getter
    public String getResolution() {
        return RESOLUTION;  //Returns Dvd's RESOLUTION
    }
    
    //Getter
    public String getPlaybackFormat() {
        return PLAYBACK_FORMAT; //Returns Dvd's PLAYBACK_FORMAT
    }
    
    //ToString Method
    @Override
    public String toString() {
        return  "Playback Format: " + PLAYBACK_FORMAT +
                "\nResolution: " + RESOLUTION +
                "\n" + super.toString();
    }
}
