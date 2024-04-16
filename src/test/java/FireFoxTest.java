import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FireFoxTest {
    static WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new  FirefoxDriver();
        driver.get("https://otvet.mail.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
    }

    @AfterEach
    void pause() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Test
    @Order(1)
    void testLogin() throws InterruptedException {
        driver.findElement(By.xpath("//button[contains(.,'Войти')]")).click();
        driver.switchTo().frame(0);
        Thread.sleep(200);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("mozgovayalara23@mail.ru");
        driver.findElement(By.xpath("//span[contains(.,'Ввести пароль')]")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("lab3$777");
        driver.findElement(By.xpath("//span[text()='Войти']")).click();
    }

    @Test
    @Order(2)
    void testSearchNoResult(){
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector(".M4n26 #search")).click();
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("TEST MESSAGE");
        driver.findElement(By.xpath("//a/div/div")).click();
    }

    @Test
    @Order(3)
    void testSendQuestion() {
        driver.findElement(By.xpath("//a[contains(text(),'Спросить')]")).click();
        driver.findElement(By.xpath("//textarea[@id='question_text']")).sendKeys("TEST MESSAGE");
        driver.findElement(By.xpath("//div[4]/a/div")).click();
        driver.findElement(By.cssSelector(".Im7BZ > .Wjnkw")).click();
        driver.findElement(By.cssSelector(".HnCxJ > .Wjnkw")).click();
    }


    @Test
    @Order(5)
    void testEditProfile(){
        driver.findElement(By.xpath("//div[8]/a/span")).click();
        driver.findElement(By.xpath("//a[contains(.,'Редактировать профиль')]")).click();
        driver.findElement(By.xpath("//textarea[@id='desc']")).sendKeys("Text");
        driver.findElement(By.xpath("//div[3]/div/div/a/div")).click();
    }

    @Test
    @Order(6)
    void testLeaders(){
        driver.findElement(By.xpath("//a[contains(.,'Лидеры')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'По активности')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'По количеству ответов')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'Все категории')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'Города и Страны')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'За неделю')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'За месяц')]")).click();
    }

    @Test
    @Order(7)
    void testLike(){
        driver.findElement(By.xpath("//div[8]/a/span")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Как сдать сессию')]")).click();
        driver.findElement(By.xpath("//div[3]/div/div/div[2]/div[2]/div/div/a/div/div[2]")).click();
    }

}
