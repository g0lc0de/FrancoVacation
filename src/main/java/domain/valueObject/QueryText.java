package domain.valueObject;

import java.util.Objects;

public final class QueryText {

    private final String queryText;

    public QueryText(String queryText) {
        this.queryText = queryText;
    }

    public String getQueryText() {
        return this.queryText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryText queryText1 = (QueryText) o;
        return Objects.equals(queryText, queryText1.queryText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryText);
    }
}
