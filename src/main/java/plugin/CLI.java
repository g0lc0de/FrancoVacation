package plugin;

import adapter.RegionAdapter;
import application.QueryDataFinder;
import domain.aggregate.Region;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public enum CLI {

    INSTANCE;

    public void startInteraction() {
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("WILLKOMMEN BEIM REISEPLANER!");
        System.out.println("Wählen Sie eine der folgenden Optionen: ");
        System.out.println("-- 1: Regionen ausgeben");
        System.out.println("-- 2: KeyWords ausgeben");
        System.out.println("-- 3: Trip erstellen");
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
        }

    }

    public void displayRegions() {
        QueryDataFinder queryDataFinder = new QueryDataFinder();
        RegionAdapter regionAdapter = new RegionAdapter(queryDataFinder.getRegions());
        List<String> regionNameList = regionAdapter.getRegionNameList();

        System.out.println("Die folgenden Regionen befinden sich im Reiseplaner!");
        for (String s : regionNameList) {
            System.out.printf("- %s\n", s);
        }

        continueToMainMenuPrompt();
    }

    public void continueToMainMenuPrompt() {

        System.out.println("Zum Hauptmenü zurückkehren?");
        getInput();
        displayMenu();
    }

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("::");
        return sc.next();
    }

}
