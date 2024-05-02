package application;

import domain.aggregate.Activity;
import domain.aggregate.Region;
import domain.aggregate.Trip;
import domain.entity.City;
import domain.valueObject.ActivityLevelType;
import domain.valueObject.Query;
import domain.valueObject.QueryText;
import domain.valueObject.Season;
import domain.valueObject.auxilliary.QueryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plugin.MockDataProvider;

import static org.junit.jupiter.api.Assertions.*;

class TripCreatorTest {

    private static TripCreator tripCreator;
    private static QueryDataFinder queryDataFinder;

    @BeforeAll
    public static void setUp() {
        MockDataProvider.INSTANCE.ConnectToDatabase();
        MockDataProvider.INSTANCE.QueryData();

        tripCreator = new TripCreator();
        queryDataFinder = new QueryDataFinder(MockDataProvider.INSTANCE);
    }

    @Test
    void testWordSimilarityCoefficientFullMatch() {
        float similarityCoefficient = tripCreator.compareWords("sommer", "sommer");

        assertEquals(1.0f, similarityCoefficient);
    }

    @Test
    void testWordSimilarityCoefficientPartialMatch() {
        float similarityCoefficient = tripCreator.compareWords("sommer", "sonner");

        assertEquals(4f/6f, similarityCoefficient);
    }

    @Test
    void testWordSimilarityCoefficientDifferentStringLength() {
        float similarityCoefficient = tripCreator.compareWords("sommer", "somme");

        assertEquals(5f/6f, similarityCoefficient);
    }

    @Test
    void testParseQueryText1() {
        QueryText queryText = new QueryText("Sommer Winter Erholung");

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.buildSeason(Season.SUMMER).buildSeason(Season.WINTER);
        queryBuilder.buildActivityLvl(ActivityLevelType.LEISURE);

        assertEquals(queryBuilder.build(), tripCreator.parseQueryText(queryText));
    }

    @Test
    void testParseQueryText2() {
        QueryText queryText = new QueryText("warm und sportlich");

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.buildSeason(Season.SUMMER);
        queryBuilder.buildActivityLvl(ActivityLevelType.SPORT);

        assertEquals(queryBuilder.build(), tripCreator.parseQueryText(queryText));
    }

    @Test
    void testParseQueryText3_TextAndRegion() {
        QueryText queryText = new QueryText("warm und sportlich in den schwarzwald");

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.buildSeason(Season.SUMMER);
        queryBuilder.buildActivityLvl(ActivityLevelType.SPORT);
        queryBuilder.buildRegion(queryDataFinder.getRegionFromName("schwarzwald"));

        assertEquals(queryBuilder.build(), tripCreator.parseQueryText(queryText));
    }

}