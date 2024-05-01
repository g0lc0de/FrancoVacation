package domain.valueObject;

import java.util.Objects;

public final class ActivityLevel {

    private final ActivityLevelType activityLevelType;

    public ActivityLevel(ActivityLevelType activityLevelType) {
        super();
        this.activityLevelType = activityLevelType;
    }

    public int getActivityLevelAsNumber() throws Exception {
        switch (activityLevelType) {
            case LEISURE:
                return 0;
            case EASY_ACTIVITY:
                return 1;
            case SPORT:
                return 2;
        }

        throw new Exception();
    }

    public ActivityLevelType getActivityLevelType() {
        return activityLevelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityLevel that = (ActivityLevel) o;
        return activityLevelType == that.activityLevelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityLevelType);
    }
}
