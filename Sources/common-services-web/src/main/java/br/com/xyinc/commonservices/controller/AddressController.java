package br.com.xyinc.commonservices.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.xyinc.commonservices.core.pagecrawler.CorreiosPageCrawler;
import br.com.xyinc.commonservices.core.pagecrawler.CrawlerException;
import br.com.xyinc.commonservices.dto.AddressDto;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
@Controller
@RequestMapping("/address")
public class AddressController {

	@RequestMapping(value = "/zipCode/{zipCodeValue}", method = RequestMethod.GET)
	@ResponseBody
	public List<AddressDto> findAddressByZipCode(@PathVariable("zipCodeValue") String zipCodeValue) {
		CorreiosPageCrawler correiosCrawler = new CorreiosPageCrawler();
		try {
			List<AddressDto> address = correiosCrawler.getAddress(zipCodeValue, "ALL", "N");
			return address;
		} catch (CrawlerException e) {
			throw new AddressException("Erro ao tentar buscar Endere�o pelo CEP");
		}
	}

	@RequestMapping(value = "/name/{addressNameValue}", method = RequestMethod.GET)
	@ResponseBody
	public List<AddressDto> findZipCodeByAddressName(@PathVariable("addressNameValue") String addressNameValue) {
		CorreiosPageCrawler correiosCrawler = new CorreiosPageCrawler();
		try {
			List<AddressDto> address = correiosCrawler.getAddress(addressNameValue, "ALL", "N");
			return address;
		} catch (CrawlerException e) {
			throw new AddressException("Erro ao tentar buscar Endere�o pelo Nome");
		}
	}
}