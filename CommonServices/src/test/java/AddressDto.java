import java.io.Serializable;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
public class AddressDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854020004875218042L;
	
	private String logradouro;
	private String bairro;
	private String localidade;
	private String CEP;
	
	public AddressDto(){
		
	}
	
	public AddressDto(String logradouro, String bairro, String localidade, String CEP){
		this.logradouro =  logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.CEP = CEP;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	
	@Override
	public String toString() {
		return "AddressDto [logradouro=" + logradouro + ", bairro=" + bairro
				+ ", localidade=" + localidade + ", CEP=" + CEP + "]";
	}
	
	
	
}
