package br.com.xyinc.commonservices.core.pagecrawler;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
public class CrawlerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2119956633733026535L;

	public CrawlerException(String message, Exception e) {
		super(message, e);
	}
	
	public CrawlerException(Exception e) {
		super(e);
	}

}
