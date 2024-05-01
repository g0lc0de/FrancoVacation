package plugin;

import adapter.RegionsAdapter;
import adapter.TripAdapter;
import application.QueryDataFinder;
import application.TripCreator;
import domain.aggregate.Region;
import domain.aggregate.Activity;
import domain.entity.City;
import domain.entity.Country;
import domain.aggregate.Trip;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public enum CLI {

    INSTANCE;

    QueryDataFinder queryDataFinder = new QueryDataFinder(MockDataProvider.INSTANCE);
    RegionsAdapter allRegionsAdapter;

    public void startInteraction() {
        allRegionsAdapter = new RegionsAdapter(queryDataFinder.getAllRegionsAsList(), MockDataProvider.INSTANCE);

        displayMenu();
    }

    private void displayMenu() {
        System.out.println("WILLKOMMEN BEIM REISEPLANER!");
        System.out.println("Wählen Sie eine der folgenden Optionen: ");
        System.out.println("-- 1: Regionen ausgeben");
        System.out.println("-- 2: Trip erstellen");
        System.out.println("-".repeat(20));
        String response = getInput();
        parseMenuResponse(response);
    }

    public void parseMenuResponse(String response) {

        int responseNumber;

        try {
            responseNumber = Integer.parseInt(response);
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe");
            displayMenu();
            return;
        }

        if (responseNumber == 1) {
            displayRegions();
        } else if (responseNumber == 2) {
            promptTripCreation();
        }

    }

    private void displayRegions() {
        List<String> regionNameList = allRegionsAdapter.getRegionsNameList();

        System.out.println("Die folgenden Regionen befinden sich im Reiseplaner!");
        for (String s : regionNameList) {
            System.out.printf("- %s\n", s);
        }

        promptRegionDetails();
    }

    private void promptRegionDetails() {

        System.out.println("Wollen Sie die Details zu einer Region sehen? (y,n)");
        String response = getInput();

        if ("y".equals(response)) {

            System.out.println("Zu welcher Region wollen Sie Details sehen?");
            String regionResponse = getInput().toLowerCase();
            showRegionDetails(regionResponse);

        } else {
            continueToMainMenuPrompt();
        }

    }

    private void showRegionDetails(String regionName) {

        Region region = allRegionsAdapter.getRegionDetails(regionName);
        List<String> countryNames = region.getCountries().stream().map(Country::getName).collect(Collectors.toList());
        List<String> cityNames = region.getCities().stream().map(City::getName).collect(Collectors.toList());
        List<String> regionActivityNames = region.getRegionActivities().stream().map(Activity::getName).collect(Collectors.toList());

        System.out.printf("Region: %s\n", region.getName());
        System.out.printf("Land: %s\n", countryNames.toString());
        System.out.printf("Städte: %s\n", cityNames.toString());
        System.out.printf("Aktivitäten: %s\n", regionActivityNames.toString());

        continueToMainMenuPrompt();
    }

    private void continueToMainMenuPrompt() {

        System.out.println("Zum Hauptmenü zurückkehren?");
        getInput();
        displayMenu();
    }

    private void promptTripCreation() {

        System.out.println("Gebe deine Wünsche für einen Trip an! ");
        System.out.println("Beispiel: \"sportlicher urlaub im winter\"");
        String tripQuery = getInput();

        TripCreator tripCreator = new TripCreator();
        TripAdapter tripAdapter = new TripAdapter(tripCreator.findTripBasedOnQuery(tripQuery));


        System.out.println("Trip:");
        System.out.printf("Region Name: %s\n",tripAdapter.getRegionDetails().getName());
        System.out.println("Aktivitäten:");

        for (Activity a : tripAdapter.getTrip().getActivitiesThatMatchQuery()) {
            System.out.printf("- %s | Saison: %s | Level: %s\n",a.getName(), a.getSeasons().toString(), a.getActivityLevel().getActivityLevelType());
        }

        System.out.println("Regionale Städte: ");
        for (City c : tripAdapter.getTrip().getRegion().getCities()) {
            System.out.printf("- %s\n",c.getName());
        }

        continueToMainMenuPrompt();
    }

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("::");
        return sc.nextLine();
    }

}
