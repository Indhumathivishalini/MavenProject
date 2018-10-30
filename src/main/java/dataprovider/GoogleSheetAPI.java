package dataprovider;

import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;



import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheetAPI {
    // Application name
    private static String APPLICATION_NAME = "Google Sheets API Java Quickstart";

   // Directory to store user credentials for this application
    
    // scope -> https://www.googleapis.com give access to read the file content
    // user.home gets the current working directory
    
    // java.io.File -> File class in java which represent file or directory path.
    // Here a file object is created by passing the name of file.
    
    private static java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");

    // Global instance of the link FileDataStoreFactory
    
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    // Global instance of the JSON factory
    // used to parse the response 
    // Get the default Session object. 
       // If a default has not yet been setup, a new Session object is created and installed as the default.
    private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    //  Global instance of the HTTP transport
    // directly connect to server to authenticate
    private static HttpTransport HTTP_TRANSPORT;

   // grant access to read the spreadsheet 
    // sheetscopes is a class which have a method (SPREADSHEETS_READONLY) to view your spreadsheet
    // Aslist-> you cannot add or remove elements from the list, you can only read or overwrite the elements.
    // it just returns List reference to existing Array object
    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            System.out.println(t);
            System.exit(1);
        }
    }

   
    // google sheet requires authentication to allow the user to access the sheet 
    public static Credential authorize() throws IOException {
        // Load client secrets
        InputStream in = GoogleSheetAPI.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

//to interact with sheet    
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    public static void main(String args[]) throws IOException {
// create sheet object to interact with sheet
		Sheets service = getSheetsService();
		// spreadsheet id 
		String spreadsheetId = "1n3tkJVI_JZKeiEkzbpo5WVdenXWbexF08N1D_t5ObNM";
		String range = "Sheet1!A1:B5";
		// valueRange is a class
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        
        //getValues method in ValueRange Class-> The data that was read or to be written.
        List<List<Object>> values = response.getValues();
        if (values != null && values.size() != 0) {
        	System.out.println(values);
        	return;
        } else {
            System.out.println("No data found.");
            
            return;
        }
	}
}