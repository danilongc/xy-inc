package br.com.xyinc.core.test;

import java.util.List;

import org.junit.Test;

import br.com.xyinc.commonservices.core.pagecrawler.CorreiosPageCrawler;
import br.com.xyinc.commonservices.core.pagecrawler.CrawlerException;
import br.com.xyinc.commonservices.dto.AddressDto;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
public class CorreiosPageCrawlerTest {

	@Test
	public void getAddress() throws CrawlerException  {
		CorreiosPageCrawler correiosCrawler = new CorreiosPageCrawler();
		
		System.out.println("Find by ZipCode");
		List<AddressDto> addressZip = correiosCrawler.getAddress("38408188", "ALL", "N");
		System.out.println(addressZip);
		
		System.out.println("Find by ZipCode");
		List<AddressDto> addressZip2 = correiosCrawler.getAddress("38408138", "ALL", "N");
		System.out.println(addressZip2);
		
		System.out.println("Find by ZipCode");
		List<AddressDto> addressZip3 = correiosCrawler.getAddress("", "ALL", "N");
		System.out.println(addressZip3);
		
		
		System.out.println("Find by Name");
		List<AddressDto> addressName = correiosCrawler.getAddress("Rua Visconde do Abaete, Uberaba", "ALL", "N");
		System.out.println(addressName);
		
	}
}


