package cars.impl;

import cars.Car;
import cars.Dimensions;

public class Toyota extends Car {
    public Toyota(int manufacturingYear, String countryOfOrigin, String color, String brand, String model, Dimensions dimensions, int maxSpeed, DriveType driveType, BodyType bodyType, double engineDisplacement, int horsepower) {
        super(manufacturingYear, countryOfOrigin, color, brand, model, dimensions, maxSpeed, driveType, bodyType, engineDisplacement, horsepower);
    }
}
