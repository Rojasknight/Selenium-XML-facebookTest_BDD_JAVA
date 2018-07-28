package co.com.accenture.Facebook.gradle.stepdefinition;

//import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;



import co.com.accenture.Facebook.gradle.tasks.LoginFacebook;
import co.com.accenture.Facebook.gradle.tasks.OpenTheBrowser;
import co.com.accenture.Facebook.gradle.tasks.SearchPeople;
import co.com.accenture.Facebook.gradle.user_interfaces.FacebookLogin;
import co.com.accenture.Facebook.gradle.util.LeerXML;
import co.com.accenture.Facebook.gradle.util.TheResult;
import co.com.accenture.Facebook.gradle.util.Usuario;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class FacebookSearchStepDefinitions {

	@Managed(driver = "chrome")
	private WebDriver herBrowser;
	private Actor juanita = Actor.named("Juanita");
	private FacebookLogin facebook;
	//
	ArrayList<String> base = new ArrayList<>();
	public static NodeList lEmpleados;
	String nombreBase;
	

	@Before
	public void setup() {

		juanita.can(BrowseTheWeb.with(herBrowser)); // Se le asigna el driver al actor
		// juanita.wasAbleTo(OpenTheBrowser.at(facebook));
		// herBrowser.manage().
		herBrowser.manage().window().maximize();
	}

	@Given("^El usuario  abre el navegador en facebook.com$")
	public void thatTheUserOpenedTheBrowserAt() throws Exception {
		juanita.wasAbleTo(OpenTheBrowser.at(facebook));
		lEmpleados =  LeerXML.main();

	}

	@When("^El usuario ingresas su correo (.*) y su contrasena (.*)$")
	public void ingresarCredencialesFacebook(String usuario, String passs) throws Exception {

		// Esta clase encapsula mi datos personas, aunque recibe el correo y la
		// contraseña no las utilizo porque las tengo encapsuladas
		Usuario user = new Usuario();

		juanita.attemptsTo(LoginFacebook.ingresar(user.getUsuario(), user.getPass()));
		System.out.println("");
	}

	@When("^El usuario busca las personas de la lista e ingresa a su perfil$")
	public void buscarPersona() throws Exception {

	
		   for (int temp = 0; temp < lEmpleados.getLength(); temp++) {
               Node nodo = lEmpleados.item(temp);
               System.out.println("Elemento:" + nodo.getNodeName());
               if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                   Element element = (Element) nodo;
                   System.out.println("id: " + element.getAttribute("id"));
                   System.out.println("Nombre: " + element.getElementsByTagName("nombre").item(0).getTextContent());
                   
                   nombreBase = element.getElementsByTagName("nombre").item(0).getTextContent();
                   juanita.attemptsTo(SearchPeople.ingresar(nombreBase.toString()));
                   
                   
                 //  System.out.println("username: " + element.getElementsByTagName("username").item(0).getTextContent());
                 // System.out.println("password: " + element.getElementsByTagName("password").item(0).getTextContent());
                   
               	String nombre = TheResult.is().answeredBy(juanita).toString().split(Pattern.quote(" ("))[0];

    			System.out.println("Cadena :"+nombre+nombre.length());
    		    System.out.println("Nombre"+ nombreBase.length());
    	
               }
           }
	}
}
