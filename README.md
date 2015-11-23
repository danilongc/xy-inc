# xy-inc
Teste ZUP

A aplicação foi desenvolvida no Eclipse Luna 64bits, compilada com o JDK 1.8.0_65 e testada utilizando o Jetty (jetty-distribution-9.3.6.v20151106) no Windows 7 64.

### Para rodar aplicação sem a necessidade do build:

* Fazer o download da destribuição do Jetty pelo endereço http://download.eclipse.org/jetty/stable-9/dist/jetty-distribution-9.3.6.v20151106.zip, descompactar e copiar para a pasta webapps (ex: C:\jetty-distribution-9.3.6.v20151106\webapps) o arquivo common-services-web.war que está dentro da pasta bin do repositório.
* Fazer o start do Jetty: Em prompt de comando navegar até a pasta aonde o Jetty foi descompactado (ex: cd c:\jetty-distribution-9.3.6.v20151106) e executar "java -jar start.jar"
* Acessar pelo browser http://localhost:8080/common-services-web/

OBS: A porta padrão do Jetty será a 8080, tenha certeza que está porta não esteja em uso.

### Para fazer build na aplicação:
* No prompt de comando navegue até a pasta (ex: cd C:\Projetos\TesteZUP\Sources\common-services)
* Execute o comando mvn clean install
* O build será gerado em C:\Projetos\TesteZUP\Sources\common-services-web\target\common-services-web.war
* Copie o arquivo para a pasta webapps do Jetty e faça o procedimento de start do servidor.


### Exemplo de testes:

* Busca por cep: http://localhost:8080/common-services-web/rest/address/zipCode/38025490
* Busca por Logradouro: http://localhost:8080/common-services-web/rest/address/name/Visconde do Abaete, Uberaba 
