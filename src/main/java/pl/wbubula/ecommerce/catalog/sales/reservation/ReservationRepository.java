package pl.wbubula.ecommerce.catalog.sales.reservation;

import java.util.HashMap;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

public class ReservationRepository {
    HashMap<String, Reservation> reservations;

    public ReservationRepository() {
        this.reservations = new HashMap<>();
    }

    public Optional<Reservation> load(String reservationId) {
        return Optional.of(reservations.get(reservationId));
    }

    public void add(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }
}
