package com.generation.medicostos.scraper;

import com.generation.medicostos.dto.MedicamentoDTO;
import com.generation.medicostos.repository.MedicamentoRepository;
import com.generation.medicostos.service.MedicamentoService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class FarmaciasChileScraper {

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
            driver.get("https://farmaciaschilespa.cl/index.php/categoria-producto/medicamentos/?orderby=popularity");

            Thread.sleep(3000);

            int pg = 40;

            for (int page = 1; page <= pg; page++) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-numbers.current")));
                    WebElement currentPageElement = driver.findElement(By.cssSelector(".page-numbers.current"));
                    String currentPage = currentPageElement.getText();

                    // Scroll hasta el fondo de la página
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    Thread.sleep(1000);

                    js.executeScript("window.scrollBy(0, document.body.scrollHeight);");
                    Thread.sleep(2000);

                    js.executeScript("window.scrollBy(0, -800);");
                    Thread.sleep(1000);

                    List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".w-grid-item-h")));

                    for (WebElement product : productList) {
                        String nombre = null;
                        String complemento = "";
                        String precioString = null;

                        try {
                            nombre = product.findElement(By.cssSelector(".post_title")).getText();
                        } catch (Exception e) {
                            System.out.println("Nombre no encontrado");
                        }



                        try {
                            precioString = product.findElement(By.cssSelector("span.woocommerce-Price-amount.amount")).getText();
                        } catch (Exception e) {
                            System.out.println("Precio no encontrado");
                        }

                        if (precioString != null) {
                            String precioSinSimbolos = precioString.replace("$", "").replace(".", "");
                            BigDecimal precio = new BigDecimal(precioSinSimbolos);

                            String urlImagen = null;
                            try {
                                WebElement imgElement = product.findElement(By.cssSelector("img.attachment-large.size-large.wp-post-image"));
                                urlImagen = imgElement.getAttribute("src");
                            } catch (Exception e) {
                                System.out.println("Imagen no encontrada");
                            }

                            String urlMedicamento = null;
                            try {
                                urlMedicamento = product.findElement(By.cssSelector("div.w-post-elm.post_image.usg_post_image_1.stretched a")).getAttribute("href");
                            } catch (Exception e) {
                                System.out.println("URL de medicamento no encontrada");
                            }

                            // ID de la farmacia "Salcobrand"
                            Long farmaciaID = 5L;

                            MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
                            medicamentoDTO.setNombre(nombre);
                            medicamentoDTO.setComplemento(complemento);
                            medicamentoDTO.setPrecio(precio);
                            medicamentoDTO.setUrlImagen(urlImagen);
                            medicamentoDTO.setUrlMedicamento(urlMedicamento);
                            medicamentoDTO.setFarmaciaId(farmaciaID);


                            medicamentoService.saveMedicamento(medicamentoDTO);
                        }
                    }

                    // Esperar a que el botón de siguiente página sea clickable y luego hacer clic
                    if (page < pg) {
                        Thread.sleep(100);

                        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".next.page-numbers")));
                        nextButton.click();
                    }
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
