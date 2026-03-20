package cars;

public abstract class Car {

    private int manufacturingYear;
    private String countryOfOrigin;
    private String color;
    private String brand;
    private String model;
    private Dimensions dimensions;
    private int maxSpeed;
    private DriveType driveType;
    private BodyType bodyType;
    private double engineDisplacement;
    private int horsepower;

    public Car(int manufacturingYear, String countryOfOrigin, String color, String brand,
               String model, Dimensions dimensions, int maxSpeed,
               DriveType driveType, BodyType bodyType,
               double engineDisplacement, int horsepower) {
        this.manufacturingYear = manufacturingYear;
        this.countryOfOrigin = countryOfOrigin;
        this.color = color;
        this.brand = brand;
        this.model = model;
        this.dimensions = dimensions;
        this.maxSpeed = maxSpeed;
        this.driveType = driveType;
        this.bodyType = bodyType;
        this.engineDisplacement = engineDisplacement;
        this.horsepower = horsepower;
    }

    // getters adn setters
    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public DriveType getDriveType() {
        return driveType;
    }

    public void setDriveType(DriveType driveType) {
        this.driveType = driveType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public double getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(double engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    // methods
    public void getFullInfo() {
        System.out.println("Информация об автомобиле");
        System.out.println("Марка: " + brand);
        System.out.println("Модель: " + model);
        System.out.println("Год выпуска: " + manufacturingYear);
        System.out.println("Страна: " + countryOfOrigin);
        System.out.println("Цвет: " + color);
        System.out.println("Тип кузова: " + bodyType);
        System.out.println("Привод: " + driveType);
        System.out.println("Макс. скорость: " + maxSpeed + " км/ч");
        System.out.println("Объём двигателя: " + engineDisplacement + " л");
        System.out.println("Мощность: " + horsepower + " л.с.");
        System.out.println("Габариты (мм): " +
                dimensions.getLength() + " x " +
                dimensions.getWidth()  + " x " +
                dimensions.getHeight() + " (Д x Ш x В)");
        System.out.println("Класс: " + getCarClass());

    }

    public String getCarClass() {
        double length = dimensions.getLength();

        if (length < 3600) {
            return "A";
        } else if (length < 4000) {
            return "B";
        } else if (length < 4400) {
            return "C";
        } else if (length < 4700) {
            return "D";
        } else if (length < 5000) {
            return "E";
        } else {
            return "F";
        }
    }

    public String getCarName()
    {
        return brand+" "+model;
    }

    public enum DriveType {
        FWD,  // передний
        RWD,  // задний
        AWD  // полный
    }

    public enum BodyType {
        SEDAN,
        HATCHBACK,
        SUV, // Внедорожник
        COUPE,
        CONVERTIBLE,
        MINIVAN,
        PICKUP
    }
}
