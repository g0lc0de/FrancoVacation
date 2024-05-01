package domain.valueObject;

import java.util.Objects;

public final class ActivityRating {

    private final int numberOfReviews;
    private final int rating; // 1 - 5 "Stars"

    public ActivityRating(int rating, int numberOfReviews) {
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
    }

    public int getRatingAsNumber() {
        return this.rating;
    }

    public RatingType getRatingAsExpression() {

        if (rating == 5) {
            return RatingType.EXCELLENT;
        } else if (rating == 4) {
            return RatingType.GOOD;

        } else if (rating == 3) {
            return RatingType.DECENT;

        } else if (rating == 2) {
            return RatingType.POOR;

        } else {
            return RatingType.AVOID;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityRating that = (ActivityRating) o;
        return rating == that.rating;
    }

    @Override
    public int hashCode() {
        return this.rating;
    }
}
