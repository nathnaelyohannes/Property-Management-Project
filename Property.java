public class Property {
    
    // Instance variables
    private String propertyName;
    private String city;
    private double rentAmount;
    private String owner;
    private Plot plot;
    
    // Constructors
    public Property() {
        this.propertyName = "";
        this.city = "";
        this.rentAmount = 0.0;
        this.owner = "";
        this.plot = new Plot();
    }
    
    public Property(String propertyName, String city, double rentAmount, String owner, int x, int y, int width, int depth) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot(x, y, width, depth);
    }
    
    public Property(String propertyName, String city, double rentAmount, String owner) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot();
    }
    
    // Getters and setters
    public String getPropertyName() {
        return propertyName;
    }
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public double getRentAmount() {
        return rentAmount;
    }
    
    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public Plot getPlot() {
        return plot;
    }
    
    public void setPlot(int x, int y, int width, int depth) {
        this.plot.setX(x);
        this.plot.setY(y);
        this.plot.setWidth(width);
        this.plot.setDepth(depth);
    }
    
    // toString method
    public String toString() {
        return "Property Name: " + propertyName + "\nLocated in " + city +
            "\nBelonging to: " + owner + "\nRent Amount: " + rentAmount + "\n" + plot.toString();
    }
}


public class Plot {
    private int x;
    private int y;
    private int width;
    private int depth;

    // default constructor
    public Plot() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.depth = 1;
    }

    // parameterized constructor
    public Plot(int x, int y, int width, int depth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.depth = depth;
    }

    // copy constructor
    public Plot(Plot p) {
        this.x = p.x;
        this.y = p.y;
        this.width = p.width;
        this.depth = p.depth;
    }

    // getter and setter methods
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    // method to check if two plots overlap
    public boolean overlaps(Plot p) {
        int x1 = this.x;
        int x2 = p.x;
        int y1 = this.y;
        int y2 = p.y;
        int w1 = this.width;
        int w2 = p.width;
        int d1 = this.depth;
        int d2 = p.depth;

        if ((x1 < x2 + w2) && (x2 < x1 + w1) && (y1 < y2 + d2) && (y2 < y1 + d1)) {
            return true;
        }
        return false;
    }

    // method to check if a plot is encompassed by the current plot
    public boolean encompasses(Plot p) {
        int x1 = this.x;
        int x2 = p.x;
        int y1 = this.y;
        int y2 = p.y;
        int w1 = this.width;
        int w2 = p.width;
        int d1 = this.depth;
        int d2 = p.depth;

        if ((x1 <= x2) && (x1 + w1 >= x2 + w2) && (y1 <= y2) && (y1 + d1 >= y2 + d2)) {
            return true;
        }
        return false;
    }

    // toString method
    public String toString() {
        return "Upper left: (" + x + "," + y + "); Width: " + width + " Depth: " + depth;
    }
}

import javafx.scene.layout.Region;

public class ManagementCompany {
    private final int MAX_PROPERTY = 5;
    private final int MGMT_WIDTH = 10;
    private final int MGMT_DEPTH = 10;
    private String name;
    private String taxID;
    private double mgmFeePer;
    private Property[] properties;
    private Plot plot;

    // Constructors
    public ManagementCompany() {
        this.name = "";
        this.taxID = "";
        this.mgmFeePer = 0;
        this.properties = new Property[MAX_PROPERTY];
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
    }

    public ManagementCompany(String name, String taxID, double mgmFee) {
        this.name = name;
        this.taxID = taxID;
        this.mgmFeePer = mgmFee;
        this.properties = new Property[MAX_PROPERTY];
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
    }

    public ManagementCompany(String name, String taxID, double mgmFee, int x, int y, int width, int depth) {
        this.name = name;
        this.taxID = taxID;
        this.mgmFeePer = mgmFee;
        this.properties = new Property[MAX_PROPERTY];
        this.plot = new Plot(x, y, width, depth);
    }

    // Add Property methods
    public int addProperty(Property p) {
        if (p == null) {
            return -2;
        }
        if (!plot.encompasses(p.getPlot())) {
            return -3;
        }
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] == null) {
                if (i >= MAX_PROPERTY) {
                    return -1;
                }
                properties[i] = new Property();
                return i;
            }
            if (properties[i].getPlot().overlaps(p.getPlot())) {
                return -4;
            }
        }
        return -1;
    }

    public int addProperty(String propertyName, String city, double rent, String ownerName) {
        Property p = new Property(propertyName, city, rent, ownerName);
        return addProperty(p);
    }

    public int addProperty(String propertyName, String city, double rent, String ownerName, int x, int y, int width, int depth) {
        Property p = new Property(propertyName, city, rent, ownerName, x, y, width, depth);
        return addProperty(p);
    }

    // Total Rent method
    public double totalRent() {
        double totalRent = 0;
        for (Property p : properties) {
            if (p != null) {
                totalRent += p.getRentAmount();
            }
        }
        return totalRent;
    }

    // Max Rent Property Index method
    private int maxRentPropertyIndex() {
        int maxIndex = -1;
        double maxRent = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] != null && properties[i].getRentAmount() > maxRent) {
                maxIndex = i;
                maxRent = properties[i].getRentAmount();
            }
        }
        return maxIndex;
    }

    // Max Rent Property method
    public double maxRentProp() {
        int maxIndex = maxRentPropertyIndex();
        if (maxIndex == -1) {
            return 0;
        }
        return properties[maxIndex].getRentAmount();
    }

    // To String method
    public String toString() {
        StringBuilder sb = new StringBuilder();
		return taxID;
}

	public Region getPlot() {
		// TODO Auto-generated method stub
		return null;
	}
}
