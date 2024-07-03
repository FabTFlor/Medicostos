package com.generation.medicostos.scraper;

import com.generation.medicostos.dto.MedicamentoDTO;
import com.generation.medicostos.models.Medicamento;
import com.generation.medicostos.repository.MedicamentoRepository;
import com.generation.medicostos.service.MedicamentoService;
import com.generation.medicostos.service.implementation.MedicamentoServiceImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Component
public class FarmaciasAhumadaScraper {

    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private MedicamentoService medicamentoService;

    public void scrapeAndSaveMedications() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir") + "/src/main/resources/chromedriver.log");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.farmaciasahumada.cl/medicamentos");
            Thread.sleep(6000);

            for (int i = 0; i < 1; i++) {
                Thread.sleep(750);

                WebElement npButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Más Resultados')]")));
                npButton.click();
            }

            List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".tile-body")));
            System.out.println("boxes: " + productList.size());
            for (WebElement product : productList) {
                System.out.println("Entra al for");
                String nombre = null;

                try {
                nombre = product.findElement(By.cssSelector("a.link")).getText();
                }catch (Exception e){
                    System.out.println("Nombre no encontrado");
                }
                String complemento = product.findElement(By.cssSelector("span.link")).getText();

                String precioString = product.findElement(By.cssSelector("span.value.d-flex.align-items-center")).getText();
                String precioSinSimbolos = precioString.replace("$", "").replace(".", "");
                BigDecimal precio = new BigDecimal(precioSinSimbolos);
                System.out.println("Antes de imagen");

                String urlImagen = product.findElement(By.cssSelector(".image-container.img")).getAttribute("src");
                System.out.println(urlImagen);

                String urlMedicamento = "https://www.farmaciasahumada.cl/" + product.findElement(By.cssSelector("a.link")).getAttribute("href");

                // ID de la farmacia "Farmacias Ahumada"
                Long farmaciaID = 1L;

                MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
                medicamentoDTO.setNombre(nombre);
                medicamentoDTO.setComplemento(complemento);
                medicamentoDTO.setPrecio(precio);
                medicamentoDTO.setUrlImagen(urlImagen);
                medicamentoDTO.setUrlMedicamento(urlMedicamento);
                medicamentoDTO.setFarmaciaId(farmaciaID);

                System.out.println(medicamentoDTO);

                medicamentoService.saveMedicamento(medicamentoDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}