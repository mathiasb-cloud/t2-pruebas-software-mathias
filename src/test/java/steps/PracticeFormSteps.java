package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.es.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PracticeFormSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String url = "https://demoqa.com/automation-practice-form";

    @Before
    public void setUp() {
        //configura driver con WebDriverManager
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("que abro la página del formulario de práctica")
    public void abrirFormulario() {
        driver.get(url);
        try {
            wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        } catch (Exception ignored) { }
    }

    @Cuando("ingreso el nombre {string} y el apellido {string}")
    public void ingresarNombres(String nombre, String apellido) {
        WebElement fn = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
        fn.clear();
        fn.sendKeys(nombre);
        WebElement ln = driver.findElement(By.id("lastName"));
        ln.clear();
        ln.sendKeys(apellido);
    }

    @Cuando("escribo el correo {string}")
    public void ingresarCorreo(String email) {
        WebElement e = driver.findElement(By.id("userEmail"));
        e.clear();
        e.sendKeys(email);
    }

    @Cuando("selecciono el género {string}")
    public void seleccionarGenero(String genero) {
        String xpath = String.format("//label[contains(.,'%s')]", genero);
        WebElement label = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
    }

    @Cuando("escribo el número de celular {string}")
    public void ingresarCelular(String numero) {
        WebElement m = driver.findElement(By.id("userNumber"));
        m.clear();
        m.sendKeys(numero);
    }

    @Cuando("selecciono el hobby {string}")
    public void seleccionarHobby(String hobby) {
        String xpath = String.format("//label[contains(.,'%s')]", hobby);
        WebElement label = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
    }

    @Cuando("escribo la dirección actual {string}")
    public void ingresarDireccion(String direccion) {
        WebElement addr = driver.findElement(By.id("currentAddress"));
        addr.clear();
        addr.sendKeys(direccion);
    }

    @Cuando("envío el formulario")
    public void enviarFormulario() {
        WebElement submit = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
    }

    @Entonces("debería visualizar el modal de confirmación")
    public void deberiaVerModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
    }

    @Entonces("el título del modal debería ser {string}")
    public void tituloModal(String esperado) {
        String title = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
        assertEquals(esperado, title);
    }

    @Entonces("el modal debería contener el nombre {string}")
    public void modalContieneNombre(String nombreCompleto) {
        String tableText = driver.findElement(By.className("table-responsive")).getText();
        assertTrue(tableText.contains(nombreCompleto), "Se esperaba que el modal contenga: " + nombreCompleto);
    }

    @Entonces("los campos obligatorios deberían mostrar mensajes de validación")
    public void validacionCampos() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean firstEmpty = (Boolean) js.executeScript("return document.getElementById('firstName').value === '';");
        Boolean lastEmpty = (Boolean) js.executeScript("return document.getElementById('lastName').value === '';");
        Boolean mobileEmpty = (Boolean) js.executeScript("return document.getElementById('userNumber').value === '';");
        assertTrue(firstEmpty || lastEmpty || mobileEmpty, "Al menos un campo obligatorio debería estar vacío");
    }

    @After
    public void tearDown() {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            ts.getScreenshotAs(OutputType.BYTES);
        } catch (Exception ignored) {}
        if (driver != null) {
            driver.quit();
        }
    }
}
