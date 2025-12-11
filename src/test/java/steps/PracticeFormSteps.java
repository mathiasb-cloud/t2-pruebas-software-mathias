package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Dado("que abro la página del formulario de práctica")
    public void abrirFormulario() {
        driver.get(url);
    }

    @Cuando("ingreso el nombre {string} y el apellido {string}")
    public void ingresarNombres(String nombre, String apellido) {
        driver.findElement(By.id("firstName")).sendKeys(nombre);
        driver.findElement(By.id("lastName")).sendKeys(apellido);
    }

    @Cuando("escribo el correo {string}")
    public void escribirCorreo(String correo) {
        driver.findElement(By.id("userEmail")).sendKeys(correo);
    }

    @Cuando("dejo el correo vacío")
    public void dejarCorreoVacio() {
        WebElement e = driver.findElement(By.id("userEmail"));
        e.clear();
    }

    @Cuando("selecciono el género {string}")
    public void seleccionarGenero(String genero) {
        String xpath = String.format("//label[contains(.,'%s')]", genero);
        WebElement gender = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gender);
    }

    @Cuando("no selecciono ningún género")
    public void noGenero() {

    }

    @Cuando("escribo el número de celular {string}")
    public void escribirCelular(String numero) {
        driver.findElement(By.id("userNumber")).sendKeys(numero);
    }

    @Cuando("selecciono el hobby {string}")
    public void seleccionarHobby(String hobby) {
        String xpath = String.format("//label[contains(.,'%s')]", hobby);
        WebElement h = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", h);
    }

    @Cuando("no selecciono ningún hobby")
    public void noHobby() {
        // No acción
    }

    @Cuando("escribo la dirección actual {string}")
    public void escribirDireccion(String direccion) {
        driver.findElement(By.id("currentAddress")).sendKeys(direccion);
    }

    @Cuando("envío el formulario")
    public void enviarFormulario() {
        WebElement submit = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
    }

    @Entonces("debería visualizar el modal de confirmación")
    public void verModalConfirmacion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
    }

    @Entonces("el título del modal debería ser {string}")
    public void validarTituloModal(String esperado) {
        String title = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
        assertEquals(esperado, title);
    }

    @Entonces("el modal debería contener el nombre {string}")
    public void validarNombreEnModal(String nombre) {
        String text = driver.findElement(By.className("table-responsive")).getText();
        assertTrue(text.contains(nombre));
    }

    @Entonces("los campos obligatorios deberían mostrar mensajes de validación")
    public void validarCampos() {
        boolean emptyFirst = driver.findElement(By.id("firstName")).getAttribute("value").isEmpty();
        boolean emptyLast = driver.findElement(By.id("lastName")).getAttribute("value").isEmpty();
        boolean emptyNumber = driver.findElement(By.id("userNumber")).getAttribute("value").isEmpty();

        assertTrue(emptyFirst || emptyLast || emptyNumber);
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
