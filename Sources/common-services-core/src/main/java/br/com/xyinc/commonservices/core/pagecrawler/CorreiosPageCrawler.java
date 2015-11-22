package br.com.xyinc.commonservices.core.pagecrawler;


import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.xyinc.commonservices.dto.AddressDto;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
public class CorreiosPageCrawler extends AbstractPageCrawler {
	
	private static String URL_CORREIOS_ADDRESS_SEARCH = "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm";
	
	public List<AddressDto> getAddress(String relaxation, String type, String similar) throws CrawlerException{
		try {
			
			MultiValueMap<String, String> mapParams = new LinkedMultiValueMap<String, String>();
			mapParams.add("relaxation", relaxation);
			mapParams.add("tipoCEP", type);
			mapParams.add("semelhante", similar);
			
			List<AddressDto> listAddresses = new ArrayList<AddressDto>();
			
			String postStringResponse = retrievePostString(CorreiosPageCrawler.URL_CORREIOS_ADDRESS_SEARCH, mapParams);
			if(postStringResponse == null){
				return listAddresses;
			}
			Source src = new Source(postStringResponse );
	
			Element addressHtmltable = src.getAllElementsByClass("tmptabela").get(0);
			List<Element> allTrAddressTable = addressHtmltable.getAllElements("tr");
			allTrAddressTable.remove(0); //Removendo o elemento do header da tabela
			
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
		}catch(IndexOutOfBoundsException e){
			return new ArrayList<AddressDto>();
		} catch (CrawlerException e){
			throw e;
		}catch (Exception e) {
			throw new CrawlerException(String.format("Erro ao tentar parsear a página solicitada ao endereço %s", CorreiosPageCrawler.URL_CORREIOS_ADDRESS_SEARCH), e);
		}
	}
}
