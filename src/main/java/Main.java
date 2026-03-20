import cars.Car;
import cars.Car.BodyType;
import cars.Car.DriveType;
import cars.Dimensions;
import cars.impl.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Car> carList = getCarsList();

        // вывод информации о новых автомобилях
        for(Car car : carList)
        {
            showInfoAboutNewCar(car);
        }


        // выводим название зеленых и красных машин ДО применения функции изменения цвета
        showGreenAndRedCars(carList);

        // проходим по списку, меняем цвет всех зеленых машин на красный
        int i=0;
        while (i<carList.size())
        {
            changeCarColorIfItsGreen(carList.get(i));
            ++i;
        }

        // выводим название зеленых и красных машин ПОСЛЕ применения функции изменения цвета
        showGreenAndRedCars(carList);


        // выводим название самой длинной машины
        showLongestCar(carList);



    }

    public static List<Car> getCarsList()
    {
        ArrayList<Car> carList = new ArrayList<>();

        carList.add(new BMW(
                2023, "Germany", "Black", "BMW", "X5",
                new Dimensions(4922, 2004, 1745),
                250, DriveType.AWD, BodyType.SUV,
                3.0, 340
        ));
        carList.add(new BMW(
                2004, "Germany", "Black", "BMW", "E46",
                new Dimensions(4471, 1739, 1415),
                210, DriveType.RWD, BodyType.SEDAN,
                2.0, 150
        ));

        carList.add(new Opel(
                2003, "Germany", "Silver", "Opel", "Vectra",
                new Dimensions(4560, 1798, 1446),
                195, DriveType.FWD, BodyType.SEDAN,
                1.8, 122
        ));
        carList.add(new Opel(
                2020, "Germany", "Blue", "Opel", "Insignia",
                new Dimensions(4899, 1820, 1495),
                230, DriveType.FWD, BodyType.SEDAN,
                2.0, 165
        ));

        carList.add(new Suzuki(
                2023, "Japan", "Red", "Suzuki", "Vitara",
                new Dimensions(4175, 1775, 1610),
                180, DriveType.AWD, BodyType.SUV,
                1.4, 129
        ));
        carList.add(new Suzuki(
                2022, "Japan", "Silver", "Suzuki", "Swift",
                new Dimensions(3845, 1695, 1500),
                170, DriveType.FWD, BodyType.HATCHBACK,
                1.2, 83
        ));

        carList.add(new Tesla(
                2023, "USA", "Green", "Tesla", "Model 3",
                new Dimensions(4694, 1850, 1443),
                225, DriveType.RWD, BodyType.SEDAN,
                0.0, 283, "Autopilot 4.0"
        ));
        carList.add(new Tesla(
                2023, "USA", "Black", "Tesla", "Model X",
                new Dimensions(5037, 2070, 1684),
                250, DriveType.AWD, BodyType.SUV,
                0.0, 670, "Full Self-Driving"
        ));

        carList.add(new Toyota(
                2023, "Japan", "Silver", "Toyota", "Camry",
                new Dimensions(4885, 1840, 1445),
                210, DriveType.FWD, BodyType.SEDAN,
                2.5, 200
        ));
        carList.add(new Toyota(
                2022, "Japan", "Green", "Toyota", "RAV4",
                new Dimensions(4600, 1855, 1685),
                180, DriveType.AWD, BodyType.SUV,
                2.0, 149
        ));

        return  carList;
    }

    //  метод, который выводит информацию об автомобилях, выпущенных
    //  после 2006 года, иначе вывести надпись «устаревший авто»
    public static void showInfoAboutNewCar(Car car)
    {
        if (car.getManufacturingYear()>2006)
        {
            car.getFullInfo();
        }
        else
        {
            System.out.println(car.getCarName() +" - устаревшее авто");
        }
        System.out.println();

    }

    // метод, который будет изменять цвет авто на красный, если у авто
    // зеленый цвет
    public static void changeCarColorIfItsGreen(Car car)
    {
        if(car.getColor().equals("Green"))
        {
            car.setColor("Red");
        }
    }

    // метод, который выводит название самой длинной машины в списке
    public static void showLongestCar(List<Car> cars) {

        Car longest = cars.get(0);

        for (Car car : cars) {
            if (car.getDimensions().getLength() > longest.getDimensions().getLength()) {
                longest = car;
            }
        }

        System.out.println("Самый длинный автомобиль");
        System.out.println(longest.getCarName());
        System.out.println();
    }

    // метод, который выводит названия зеленных машин из списка
    public static void showGreenAndRedCars(List<Car> cars) {


        System.out.println("Зеленые машины");
        for (Car car : cars) {
            if (car.getColor().equals("Green")) {
                System.out.println(car.getCarName());
            }
        }

        System.out.println("Красные машины");
        for (Car car : cars) {
            if (car.getColor().equals("Red")) {
                System.out.println(car.getCarName());
            }
        }
        System.out.println();

    }
}