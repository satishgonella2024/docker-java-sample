package org.example;

public class App {
    public static void main(String[] args) {
        ApiService apiService = new ApiService();
        DatabaseService databaseService = new DatabaseService();

        try {
            // Fetch data from API
            System.out.println("Fetching data from API...");
            String apiResponse = apiService.getDataFromApi();
            System.out.println("API Response: " + apiResponse);

            // Query database
            System.out.println("\nQuerying database...");
            databaseService.queryDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}