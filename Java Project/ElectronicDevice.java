public class ElectronicDevice {
    //Instance Attributes
    private final int CODE;
    private final String MODEL;
    private final int MODEL_YEAR;
    private final String MANUFACTURER;
    private double price;
    private int discount;
    
    //Default Constructor
    public  ElectronicDevice () {
        this.CODE = 0;
        this.MODEL = null;
        this.MODEL_YEAR = 0;
        this.MANUFACTURER = null;
        this.price = 0;
    }
    
    //2nd Constructor
    public ElectronicDevice (int code, String model, int modelYear, String manufacturer, double price) {
        this.CODE = code;   //Initialization CODE
        this.MODEL = model; //Initialization MODEL
        this.MODEL_YEAR = modelYear;    //Initialization MODEL_YEAR
        this.MANUFACTURER = manufacturer;   //Initialization MANUFACTURER
        this.price = price; //Initialization price
    }
    
    //METHODS
    
    //Getter
    public int getCode() {
        return CODE;    //Returns Electronic Device's CODE
    }
    
    //Getter
    public String getModel() {
        return MODEL;   //Returns Electronic Device's MODEL
    }
    
    //Getter
    public int getModelYear() {
        return MODEL_YEAR;   //Returns Electronic Device'sMODEL_YEAR
    }
    
    //Getter
    public String getManufacturer() {
        return MANUFACTURER;     //Returns Electronic Device's MANUFACTURER
    }
    
    //Getter
    public double getPrice() {
        return price;    //Returns Electronic Device's PRICE
    }
    
    //Setter
    public void setPrice(double price) {
        this.price = price;     //Sets Electronic Device's price
    }
    
    //Getter
    public int getDiscount() {
        return discount;    //Returns Electronic Device's discount
    }
   
    //Setter
    public void setDiscount(int discount) {
        this.discount = discount;   //Sets Electronic Device's discount
    }
    
    //ToString Method
    @Override
    public String toString() {
        return  "Code: " + CODE +
                "\nModel: " + MODEL +
                "\nModel year: " + MODEL_YEAR +
                "\nManufacturer: " + MANUFACTURER +
                "\nPrice: " + price;
    }
}
