import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

public class JTest {
	public static void main(String[] args) throws Exception {
		
		String htmlResponse =  getPostString("http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm", "relaxation=38408188&tipoCEP=ALL&semelhante=N");
		net.htmlparser.jericho.Source src = new Source(htmlResponse);
		Element addressHtmltable = src.getAllElementsByClass("tmptabela").get(0);
		List<Element> allTrAddressTable = addressHtmltable.getAllElements("tr");
		allTrAddressTable.remove(0); //Removendo o elemento do header da tabela
		
		List<AddressDto> listAddresses = new ArrayList<AddressDto>();
		
		for(Element trElement: allTrAddressTable){
			
			List<Element> allTdAddressTablet = trElement.getAllElements("td");
			AddressDto newAddressItem = new AddressDto();
			
			for(int i = 0; i < allTdAddressTablet.size() ; i++){
				switch (i) {
				case 0:
					newAddressItem.setLogradouro(allTdAddressTablet.get(i).getTextExtractor().toString());
					break;
				case 1:
					newAddressItem.setBairro(allTdAddressTablet.get(i).getTextExtractor().toString());
					break;
				case 2:
					newAddressItem.setLocalidade(allTdAddressTablet.get(i).getTextExtractor().toString());
					break;
				case 3:
					newAddressItem.setCEP(allTdAddressTablet.get(i).getTextExtractor().toString());
					break;

				default:
					break;
				}
			}
			listAddresses.add(newAddressItem);
		}
		
		
	}
	
	static  String getPostString(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;  
		  try {
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
		    String line;
		    while((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if(connection != null) {
		      connection.disconnect(); 
		    }
		  }
		}
	
}
