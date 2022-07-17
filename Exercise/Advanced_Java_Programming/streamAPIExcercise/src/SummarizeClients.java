import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public final class SummarizeClients {
    public static void main(String[] args) {

        int numClients = 0;
        int totalQuarterlySpend = 0;
        UdacisearchClient nextExpiration = null;
        Set<ZoneId> representedZoneIds = new HashSet<>();
        Map<Year, Integer> contractsPerYear = new HashMap<>();

        for (UdacisearchClient client : ClientStore.getClients()) {
            numClients++;
            totalQuarterlySpend += client.getQuarterlyBudget();
            if (nextExpiration == null || client.getContractStart().plus(client.getContractLength())
                    .isAfter(nextExpiration.getContractStart().plus(nextExpiration.getContractLength()))) {
                nextExpiration = client;
            }
            for (ZoneId zone : client.getTimeZones()) {
                representedZoneIds.add(zone);
            }
            contractsPerYear.compute(getContractYear(client), (k, v) -> (v == null) ? 1 : v + 1);
        }

        System.out.println("For loop results:");
        System.out.println("Num clients: " + numClients);
        System.out.println("Total quarterly spend: " + totalQuarterlySpend);
        System.out.println("Average budget: " + (double) totalQuarterlySpend / numClients);
        System.out.println("ID of next expiring contract: " + (nextExpiration == null ? -1 : nextExpiration.getId()));
        System.out.println("Client time zones: " + representedZoneIds);
        System.out.println("Contracts per year: " + contractsPerYear);

        // use stream API
        List<UdacisearchClient> clients = ClientStore.getClients();
        // compute numberOfClients using the stream API
        int numClients2 = clients.stream().mapToInt(c -> 1).sum();
        // compute totalQuarterlySpend using the stream API
        int totalQuarterlySpend2 = clients.stream()
                .mapToInt(UdacisearchClient::getQuarterlyBudget)
                .sum();
        // compute averageBudget using the stream API
        double averageBudget2 = clients.stream()
                .mapToInt(UdacisearchClient::getQuarterlyBudget)
                .average()
                .orElse(0);
        // compute nextExpiration using the stream API
        long nextExpiration2 = clients.stream()
                .min(Comparator.comparing(UdacisearchClient::getContractEnd))
                .map(UdacisearchClient::getId)
                .orElse(-1L);
        // compute representedZoneIds using the stream API
        List<ZoneId> representedZoneIds2 = clients.stream()
                .flatMap(client -> client.getTimeZones().stream()).toList();
        // compute contractsPerYear using the stream API
        Map<Year, Long> contractsPerYear2 = clients.stream()
                .collect(Collectors.groupingBy(SummarizeClients::getContractYear, Collectors.counting()));

        System.out.println("Stream API results:");
        System.out.println("Num clients: " + numClients2);
        System.out.println("Total quarterly spend: " + totalQuarterlySpend2);
        System.out.println("Average budget: " + averageBudget2);
        System.out.println("ID of next expiring contract: " + nextExpiration2);
        System.out.println("Client time zones: " + representedZoneIds2);
        System.out.println("Contracts per year: " + contractsPerYear2);
    }

    private static Year getContractYear(UdacisearchClient client) {
        LocalDate contractDate =
                LocalDate.ofInstant(client.getContractStart(), client.getTimeZones().get(0));
        return Year.of(contractDate.getYear());
    }
}
