package cars.impl;

import cars.Car;
import cars.Dimensions;

public class Tesla extends Car {

    public Tesla(int manufacturingYear, String countryOfOrigin, String color, String brand, String model, Dimensions dimensions, int maxSpeed, DriveType driveType, BodyType bodyType, double engineDisplacement, int horsepower, String aiModel) {
        super(manufacturingYear, countryOfOrigin, color, brand, model, dimensions, maxSpeed, driveType, bodyType, engineDisplacement, horsepower);
        this.aiModel=aiModel;
    }

    public void setAiModel(String aiModel) {
        this.aiModel = aiModel;
    }

    private String aiModel;


    @Override
    public void getFullInfo() {
        super.getFullInfo();
        System.out.println("Модель ИИ: " + aiModel); // добавил уникальное для марки тесла поле
    }


}
