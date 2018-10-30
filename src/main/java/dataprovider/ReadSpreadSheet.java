package dataprovider;


import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
//import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
public class ReadSpreadSheet {

	// Fill in google account username
	public static final String GOOGLE_ACCOUNT_USERNAME = "indhumathi.rajavel@anywhere.co";

	// Fill in google account password
	public static final String GOOGLE_ACCOUNT_PASSWORD = "Indhu@123Indhu@123";

	//Fill in google spreadsheet URI
	public static final String SPREADSHEET_URL = "https://spreadsheets.google.com/feeds/spreadsheets/1tpKVNz0CeByk2bHuiLMIV3MtJvTj0jNerl5W6MtRSe0"; 


	public static void main(String[] args) throws IOException, ServiceException, AuthenticationException {
	{
		/** Our view of Google Spreadsheets as an authenticated Google user. */
		SpreadsheetService service = new SpreadsheetService("Print Google Spreadsheet ");

		// Login and prompt the user to pick a sheet to use.
		/*if (GOOGLE_ACCOUNT_USERNAME != null && GOOGLE_ACCOUNT_PASSWORD != null) {
		    try {
		      service.setUserCredentials(GOOGLE_ACCOUNT_USERNAME, GOOGLE_ACCOUNT_PASSWORD);
		    } catch (AuthenticationException e) {
		      throw new IllegalArgumentException(
		          "Illegal username/password combination.");
		    }
		  }*/
		service.setUserCredentials(GOOGLE_ACCOUNT_USERNAME, GOOGLE_ACCOUNT_PASSWORD);

		// Load sheet
		URL metafeedUrl = new URL(SPREADSHEET_URL);
		SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl, SpreadsheetEntry.class);
		URL listFeedUrl = ((WorksheetEntry) spreadsheet.getWorksheets().get(0)).getListFeedUrl();

		// Print entries
		ListFeed feed = (ListFeed) service.getFeed(listFeedUrl, ListFeed.class);
		for(ListEntry entry : feed.getEntries())
		{
			System.out.println("new row");
			for(String tag : entry.getCustomElements().getTags())
			{
				System.out.println("     "+tag + ": " + entry.getCustomElements().getValue(tag));
			}
		}
	}
	}}


