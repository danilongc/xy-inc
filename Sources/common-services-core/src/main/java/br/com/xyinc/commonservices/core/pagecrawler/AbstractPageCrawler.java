package br.com.xyinc.commonservices.core.pagecrawler;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
public abstract class AbstractPageCrawler {
	
	
	protected String retrievePostString(String targetURL, @SuppressWarnings("rawtypes") Map urlParameters) throws CrawlerException {
		try {
			
			org.springframework.web.client.RestTemplate rest = new RestTemplate();
			String result = rest.postForObject(targetURL, urlParameters, String.class);
			return result;
			
		} catch (Exception e) {
			throw new CrawlerException("Erro ao executar POST", e);
		}
	}

}
