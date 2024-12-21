package practicum.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import practicum.backend.dto.Report;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final CurrentUserService currentUserService;
    private final Random random = new Random();

    @Secured({"prothetic_user"})
    public Report generateReport() {
        var currentUser = currentUserService.getCurrentUser().orElseThrow(() -> new IllegalStateException("User not found"));
        return new Report(
                currentUser.username(),
                currentUser.firstName(),
                currentUser.lastName(),
                currentUser.email(),
                LocalDate.now(),
                generateMovements(),
                generateTotalMovements(),
                generateAverageSignalStrength());
    }

    private List<String> generateMovements() {
        List<String> possibleMovements = List.of("Grip", "Pinch", "Point", "Thumb Up", "Thumb Down", "Fist");
        int movementCount = random.nextInt(3, 6);
        return possibleMovements.subList(0, movementCount);
    }

    private int generateTotalMovements() {
        return random.nextInt(50, 200);
    }

    private double generateAverageSignalStrength() {
        return Math.round((random.nextDouble() * 100) * 100.0) / 100.0;
    }

}
