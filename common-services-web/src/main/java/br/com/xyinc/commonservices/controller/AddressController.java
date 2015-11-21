package br.com.xyinc.commonservices.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.xyinc.commonservices.core.dto.AddressDto;
import br.com.xyinc.commonservices.core.pagecrawler.CorreiosPageCrawler;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
@Controller
@RequestMapping("/address")
public class AddressController {
	
	
	@RequestMapping(value ="/cep/{zipCodeValue}", method = RequestMethod.GET)
	@ResponseBody
    public List<AddressDto> findAddressByZipCode(@PathVariable("zipCodeValue") String zipCodeValue) {
		CorreiosPageCrawler correiosCrawler = new CorreiosPageCrawler();
		return correiosCrawler.getAddress(zipCodeValue, "ALL", "N");
    }
	
	@RequestMapping(value ="/name/{addressNameValue}", method = RequestMethod.GET)
	@ResponseBody
    public List<AddressDto> findZipCodeByAddressName(@PathVariable("addressNameValue") String addressNameValue) {
		CorreiosPageCrawler correiosCrawler = new CorreiosPageCrawler();
		return correiosCrawler.getAddress(addressNameValue, "ALL", "N");
    }
}