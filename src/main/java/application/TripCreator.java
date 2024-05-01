package application;

import domain.aggregate.Activity;
import domain.aggregate.Region;
import domain.aggregate.Trip;
import domain.entity.City;
import domain.entity.auxilliary.PartOfQuery;
import domain.valueObject.*;
import domain.valueObject.auxilliary.QueryBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TripCreator {

    public Map<String, PartOfQuery> seasonConverterMap = new HashMap<>();
    public Map<String, PartOfQuery> activityLevelTypeConverterMap= new HashMap<>();

    public TripCreator() {
        createSeasonConverterMap();
        createActivityLevelTypeConverterMap();
    }

    private void createSeasonConverterMap() {
        seasonConverterMap.put("warm", Season.SUMMER);
        seasonConverterMap.put("sommer", Season.SUMMER);

        seasonConverterMap.put("kalt", Season.WINTER);
        seasonConverterMap.put("winter", Season.WINTER);
    }

    private void createActivityLevelTypeConverterMap() {
        activityLevelTypeConverterMap.put("sportlich", ActivityLevelType.SPORT);
        activityLevelTypeConverterMap.put("sport", ActivityLevelType.SPORT);

        activityLevelTypeConverterMap.put("erholsam", ActivityLevelType.LEISURE);
        activityLevelTypeConverterMap.put("erholung", ActivityLevelType.LEISURE);
        activityLevelTypeConverterMap.put("entspannen", ActivityLevelType.LEISURE);
        activityLevelTypeConverterMap.put("entspannung", ActivityLevelType.LEISURE);
        activityLevelTypeConverterMap.put("entspannen", ActivityLevelType.LEISURE);
    }

    public Query parseQueryText(QueryText queryText) {

        String[] queryWords = queryText.getQueryText().toLowerCase(Locale.ROOT).split(" ");
        QueryBuilder queryBuilder = new QueryBuilder();

        for (String queryWord : queryWords) {

            // Check if Word is a Season

            String keyOfSeasonMap = getMatchStringToKeyFromKVMap(seasonConverterMap.keySet(), queryWord);

            if (keyOfSeasonMap.length() > 0) {
                queryBuilder.buildSeason((Season) seasonConverterMap.get(keyOfSeasonMap));
                continue;
            }

            String keyOfActivityLevelTypeMap = getMatchStringToKeyFromKVMap(activityLevelTypeConverterMap.keySet(), queryWord);

            if (keyOfActivityLevelTypeMap.length() > 0) {
                queryBuilder.buildActivityLvl((ActivityLevelType) activityLevelTypeConverterMap.get(keyOfActivityLevelTypeMap));
                continue;
            }

            QueryDataFinder queryDataFinder = new QueryDataFinder();
            List<Region> regions = queryDataFinder.getAllRegionsAsList();
            Set<String> regionUIDs = regions.stream()
                    .map(Region::getName)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            String regionName = getMatchStringToKeyFromKVMap(regionUIDs, queryWord);

            if (regionName.length() > 0) {
                queryBuilder.buildRegion(queryDataFinder.getRegionFromName(regionName));
            }
//            System.out.printf("Season found: %s Similarity: %f\n", seasonConverterMap.get(key), wordSimilarityIndex);
//            System.out.printf("Word: %s\n", word);
        }

        return queryBuilder.build();
    }

    private String getMatchStringToKeyFromKVMap(Set<String> keySet, String queryWord) {

        float biggestMatchCoefficient = 0;
        String biggestMatchKey = "";

        for (String key : keySet) {
            float wordSimilarityIndex = compareWords(key, queryWord);

            if (wordSimilarityIndex >= 0.7f) {

                if (wordSimilarityIndex > biggestMatchCoefficient) {
                    biggestMatchCoefficient = wordSimilarityIndex;
                    biggestMatchKey = key;
                }
            }
        }

        return biggestMatchKey;
    }

    public float compareWords(String correctBaseString, String stringToCheck) {

        float sameChars = 0;

        for (int i = 0; i < correctBaseString.length(); i++) {
            if (stringToCheck.length() == i) {
                break;
            }

            char a = correctBaseString.charAt(i);
            char b = stringToCheck.charAt(i);

            if (a == b) {
                sameChars++;
            }

        }

        return sameChars / correctBaseString.length();

    }

    public Trip findTripBasedOnQuery(String queryText) {
        QueryDataFinder queryDataFinder = new QueryDataFinder();

        Query query = parseQueryText(new QueryText(queryText));

        // LOGIC
        List<Region> regions = query.getRegions();

        if (regions.size() == 0) {
            regions = queryDataFinder.getAllRegionsAsList();
        }

        Region regionWithMaxMatchingActivities = new Region("No matching activities", new ArrayList<>());
        int matchingActivities = 0;
        Map<String, List<Activity>> matchingActivitiesPerRegionMap = new HashMap<>();

        for (Region region : regions) {

            List<Activity> activityList = region.getRegionActivities();

            // Collect city activities
            for (City c : region.getCities()) {
                activityList = Stream.concat(c.getCityActivities().stream(), activityList.stream()).collect(Collectors.toList());
            }

            for (Activity a : activityList) {

                List<Season> seasonList = query.getSeasons();

                if (seasonList.isEmpty()) {
                    seasonList.add(Season.WINTER);
                    seasonList.add(Season.SUMMER);
                    seasonList.add(Season.SPRING);
                    seasonList.add(Season.FALL);
                }

                Set<Season> matchingSeasonSet = query.getSeasons().stream()
                        .filter(a.getSeasons()::contains)
                        .collect(Collectors.toSet());

                if (matchingSeasonSet.isEmpty()) {
                    continue;
                }

                List<ActivityLevelType> validActivityLevels = query.getActivityLevel();

                if (validActivityLevels.isEmpty()) {
                    validActivityLevels.add(ActivityLevelType.LEISURE);
                    validActivityLevels.add(ActivityLevelType.EASY_ACTIVITY);
                    validActivityLevels.add(ActivityLevelType.SPORT);
                }

                if (!validActivityLevels.contains(a.getActivityLevel().getActivityLevelType())) {
                    continue;
                }

                List<Activity> matchingActivitiesPerRegionList = new ArrayList<>();
                if (matchingActivitiesPerRegionMap.containsKey(region.getName())) {
                    matchingActivitiesPerRegionList = matchingActivitiesPerRegionMap.get(region.getName().toLowerCase());
                }
                matchingActivitiesPerRegionList.add(a);

                matchingActivitiesPerRegionMap.put(region.getName().toLowerCase(), matchingActivitiesPerRegionList);
            }

            if (!matchingActivitiesPerRegionMap.containsKey(region.getName().toLowerCase())) {
                continue;
            }

            int matchingActivitiesOfCurrentRegion = matchingActivitiesPerRegionMap.get(region.getName().toLowerCase()).size();
            if (matchingActivitiesOfCurrentRegion > matchingActivities) {
                matchingActivities = matchingActivitiesOfCurrentRegion;
                regionWithMaxMatchingActivities = region;
            }

        }

        // Create Trip
        Trip trip = new Trip(regionWithMaxMatchingActivities, matchingActivitiesPerRegionMap.get(regionWithMaxMatchingActivities.getName().toLowerCase()), query, regionWithMaxMatchingActivities.getCities());

        return trip;
    }
}
