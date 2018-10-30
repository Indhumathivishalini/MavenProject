package practtice;



	
import java.io.File;
import java.io.IOException;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.security.GeneralSecurityException;
	import java.util.Arrays;
	import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.ServiceException;
	public class OAuthIntegration {
	    public static void main(String[] args) throws MalformedURLException, GeneralSecurityException, IOException, ServiceException {
	        URL SPREADSHEET_FEED_URL;
	        SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/1tpKVNz0CeByk2bHuiLMIV3MtJvTj0jNerl5W6MtRSe0");

	       File p12 = new File("My Project1-b231dbe8b7a4.p12");

	        HttpTransport httpTransport = new NetHttpTransport();
	        JacksonFactory jsonFactory = new JacksonFactory();
	        String[] SCOPESArray = {"https://spreadsheets.google.com/feeds", "https://spreadsheets.google.com/feeds/spreadsheets/1tpKVNz0CeByk2bHuiLMIV3MtJvTj0jNerl5W6MtRSe0", "https://docs.google.com/feeds"};
	        final List SCOPES = Arrays.asList(SCOPESArray);
	        GoogleCredential credential = new GoogleCredential.Builder()
	                .setTransport(httpTransport)
	                .setJsonFactory(jsonFactory)
	                .setServiceAccountId("cliend_ID")
	                .setServiceAccountScopes(SCOPES)
	                .setServiceAccountPrivateKeyFromP12File(p12)
	                .build();

	        SpreadsheetService service = new SpreadsheetService("Test");

	        service.setOAuth2Credentials(credential);
	        SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
	        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

	        if (spreadsheets.size() == 0) {
	            System.out.println("No spreadsheets found.");
	        }

	         SpreadsheetEntry spreadsheet = null;
	        for (int i = 0; i < spreadsheets.size(); i++) {
	            if (spreadsheets.get(i).getTitle().getPlainText().startsWith("ListOfSandboxes")) {
	                spreadsheet = spreadsheets.get(i);
	                System.out.println("Name of editing spreadsheet: " + spreadsheets.get(i).getTitle().getPlainText());
	                System.out.println("ID of SpreadSheet: " + i);
	            }
	        }

	    }

	}

