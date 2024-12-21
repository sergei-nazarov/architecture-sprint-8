package practicum.backend.dto;

import java.time.LocalDate;
import java.util.List;

public record Report(
        String username,
        String firstName,
        String lastName,
        String email,
        LocalDate reportDate,
        List<String> movements,
        int totalMovements,
        double averageSignalStrength
) {
}
