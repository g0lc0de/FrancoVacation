import plugin.CLI;
import plugin.MockDataProvider;

public class Main {

    public static void main(String[] args) {
        MockDataProvider.INSTANCE.ConnectToDatabase();
        MockDataProvider.INSTANCE.QueryData();

        CLI.INSTANCE.startInteraction();
    }
}


