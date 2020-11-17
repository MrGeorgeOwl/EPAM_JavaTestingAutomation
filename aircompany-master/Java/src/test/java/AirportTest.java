import planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalType;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static Airport getAirport() {
        return new Airport(planes);
    }

    @Test
    public void testGetTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = getAirport()
                .getSpecifiedTypeOfMilitaryPlanes(MilitaryType.TRANSPORT);
        Assert.assertEquals(transportMilitaryPlanes.get(0).getType(), MilitaryType.TRANSPORT);
        Assert.assertEquals(transportMilitaryPlanes.size(), 1);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        PassengerPlane planeWithMaxPassengerCapacity = (PassengerPlane) planes.get(2);
        Assert.assertEquals(planeWithMaxPassengerCapacity, getAirport().getPassengerPlaneWithMaxPassengersCapacity());
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        Airport airport = getAirport();
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planes = airport.getPlanes();
        Assert.assertTrue(planes.get(0).getMaxLoadCapacity() < planes.get(planes.size() - 1).getMaxLoadCapacity());
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = getAirport().getSpecifiedTypeOfMilitaryPlanes(MilitaryType.BOMBER);
        Assert.assertTrue(bomberMilitaryPlanes.size() >= 1);
        Assert.assertEquals(bomberMilitaryPlanes.get(0).getType(), MilitaryType.BOMBER);
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
        List<ExperimentalPlane> experimentalPlanes = getAirport().getExperimentalPlanes();
        int amountOfUnclassifiedPlanes = experimentalPlanes
                .stream()
                .filter(plane -> plane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED)
                .toArray().length;
        Assert.assertEquals(amountOfUnclassifiedPlanes, 0);
    }
}
