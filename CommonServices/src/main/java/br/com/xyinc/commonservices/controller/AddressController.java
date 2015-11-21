package br.com.xyinc.commonservices.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.xyinc.commonservices.core.dto.AddressDto;
import br.com.xyinc.commonservices.core.dto.Calculation;
import br.com.xyinc.commonservices.core.pagecrawler.CorreiosPageCrawler;

@Controller
@RequestMapping("/address")
public class AddressController {
	
	@RequestMapping(value = "/calc/{op}/{left}/{right}", method = RequestMethod.GET)
	@ResponseBody
	public Calculation calculate(@PathVariable("op") String op,
			@PathVariable("left") Integer left,
			@PathVariable("right") Integer right) {
		Assert.notNull(op);
		Assert.notNull(left);
		Assert.notNull(right);
		Calculation result = new Calculation();
		result.setOperation(op);
		result.setLeft(left);
		result.setRight(right);
		return doCalc(result);
	}
	
	@RequestMapping("/greeting")
	@ResponseBody
    public String greeting(Model model) {
        return "merda";
    }
	
	
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

	@RequestMapping(value = "/calc2", method = RequestMethod.POST)
	@ResponseBody
	public Calculation calculate(@RequestBody Calculation calc) {
		Assert.notNull(calc);
		Assert.notNull(calc.getOperation());
		Assert.notNull(calc.getLeft());
		Assert.notNull(calc.getRight());
		return doCalc(calc);
	}

	private Calculation doCalc(Calculation c) {
		String op = c.getOperation();
		int left = c.getLeft();
		int right = c.getRight();
		if (op.equalsIgnoreCase("subtract")) {
			c.setResult(left - right);
		} else if (op.equalsIgnoreCase("multiply")) {
			c.setResult(left * right);
		} else if (op.equalsIgnoreCase("divide")) {
			c.setResult(left / right);
		} else {
			c.setResult(left + right);
		}
		return c;
	}
}