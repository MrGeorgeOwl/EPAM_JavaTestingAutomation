import planes.ExperimentalPlane;
import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return planes
                .stream()
                .filter(plane -> (plane instanceof PassengerPlane))
                .map(plane -> (PassengerPlane) plane)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return planes
                .stream()
                .filter(plane -> (plane instanceof MilitaryPlane))
                .map(plane -> (MilitaryPlane) plane)
                .collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlanes()
                .stream()
                .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity))
                .get();
    }

    public List<MilitaryPlane> getSpecifiedTypeOfMilitaryPlanes(MilitaryType type) {
        return getMilitaryPlanes()
                .stream()
                .filter(plane -> plane.getType() == type)
                .collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return planes.
                stream()
                .filter(plane -> plane instanceof ExperimentalPlane)
                .map(plane -> (ExperimentalPlane) plane)
                .collect(Collectors.toList());
    }

    public void sortByMaxDistance() {
        planes.sort((Comparator<Plane>) (p1, p2) -> p1.getMaxFlightDistance() - p2.getMaxFlightDistance());
    }

    public void sortByMaxSpeed() {
        planes.sort((Comparator<Plane>) (p1, p2) -> p1.getMaxSpeed() - p2.getMaxSpeed());
    }

    public void sortByMaxLoadCapacity() {
        planes.sort((Comparator<Plane>) (p1, p2) -> p1.getMaxLoadCapacity() - p2.getMaxLoadCapacity());
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    private void printPlanes() {
        for(Plane plane: planes) {
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }


}
