package br.com.xyinc.commonservices.core.pagecrawler;


import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.xyinc.commonservices.core.dto.AddressDto;

public class CorreiosPageCrawler extends AbstractPageCrawler {
	
	private static String URL_CORREIOS_ADDRESS_SEARCH = "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm";
	
	public List<AddressDto> getAddress(String relaxation, String type, String similar){
		
		String postStringResponse =  retrievePostString(CorreiosPageCrawler.URL_CORREIOS_ADDRESS_SEARCH, String.format("relaxation=%s&tipoCEP=%s&semelhante=%s", relaxation, type, similar) );
		Source src = new Source(postStringResponse );
	
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
		return listAddresses;
	}
}
