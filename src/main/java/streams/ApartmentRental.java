package streams;

import java.util.ArrayList;
import java.util.List;

public class ApartmentRental {

    private List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Provided parameter is null, can not be added to apartments list.");
        }
        apartments.add(apartment);
    }

    public List<Apartment> findApartmentByLocation(String loc) {
        return apartments.stream()
                .filter(a -> a.getLocation().equals(loc))
                .toList();
    }

    public List<Apartment> findApartmentByExtras(String extra) {
        return apartments.stream()
                .filter(a -> a.getExtras().contains(extra))
                .toList();
    }

    public List<Apartment> findApartmentByExtras(String extra, String otherExtra) {
        return apartments.stream()
                .filter(a -> a.getExtras().contains(extra) & a.getExtras().contains(otherExtra))
                .toList();
    }

    public boolean isThereApartmentWithBathroomType(BathRoomType type) {
        return apartments.stream()
                .anyMatch(a -> a.getBathRoomType().equals(type));
    }

    public List<Integer> findApartmentsSize() {
        return apartments.stream()
                .map(a -> a.getSize())
                .toList();
    }
}
