package practtice;

import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import org.apache.log4j.BasicConfigurator;
//import com.nm.vernacular.services.SpreadSheetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class GoogleSheetsReader {

	
	 private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	  //  private static final String KEY_FILE_LOCATION = "<Name of p12 file>.p12";
	    private static final String KEY_FILE_LOCATION = "My Project1-b231dbe8b7a4.p12";
	    private static final String SERVICE_ACCOUNT_EMAIL = "test-746@my-project1-1540354095028.iam.gserviceaccount.com";
	    private static final String APPLICATION_NAME = "Google Sheets API";

	    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSheetsReader.class);

	   
	    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);

	  
	    private Credential getCredentials() throws URISyntaxException, IOException, GeneralSecurityException {
	        //Reading Key File
	        URL fileURL = GoogleSheetsReader.class.getClassLoader().getResource(KEY_FILE_LOCATION);
	        // Initializes an authorized analytics service object.
	        if(fileURL==null) {
	            fileURL = (new File("/resources/"+ KEY_FILE_LOCATION)).toURI().toURL();
	        }
	        // Construct a GoogleCredential object with the service account email
	        // and p12 file downloaded from the developer console.
	        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	        return new GoogleCredential.Builder()
	                .setTransport(httpTransport)
	                .setJsonFactory(JSON_FACTORY)
	                .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
	               
	              .setServiceAccountPrivateKeyFromP12File(new File(fileURL.toURI()))
	                .setServiceAccountScopes(SCOPES)
	                .build();
	    }

	    public List<Object[]> readSheet(String nameAndRange, String key, int[] returnRange) throws GeneralSecurityException, IOException {
	        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	        final String spreadsheetId = "1tpKVNz0CeByk2bHuiLMIV3MtJvTj0jNerl5W6MtRSe0";
	        final String range = "Sheet1!A1:B2";
	        try {
	            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
	                    .setApplicationName(APPLICATION_NAME)
	                    .build();

	            ValueRange response = service.spreadsheets().values()
	                    .get(spreadsheetId, range)
	                    .execute();
	            List<List<Object>> values = response.getValues();

	            int a = returnRange.length;
	            List<Object[]> result = new LinkedList<>();

	            if (values == null || values.isEmpty()) {
	                return Collections.emptyList();
	            } else {
	                for (List row : values) {
	                    if(row.size() >= a) {
	                        Object[] objArr = new Object[a];
	                        for(int i=0;i<a;i++) {
	                            objArr[i] = row.get(returnRange[i]);
	                        }
	                        result.add(objArr);
	                    }
	                }
	            }
	            return result;
	        } catch(Exception ex) {
	            LOGGER.error("Exception while reading google sheet", ex);
	        } finally {

	        }
	        return null;
	    }

	    public static void main(String[] args) throws GeneralSecurityException, IOException {
	     GoogleSheetsReader reader = new GoogleSheetsReader();
	     reader.readSheet("Sheet1!A1:B2","1tpKVNz0CeByk2bHuiLMIV3MtJvTj0jNerl5W6MtRSe0" , new int[]{0, 1});
	    
	     BasicConfigurator.configure();
	//     reader.readSheet("<Sheet Name>!A2:B", "<sheets key from URL>", new int[]{0, 1});
	    }
	}


