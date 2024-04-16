import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChromeTest {
    static WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://otvet.mail.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }


    @Test
    void testLogin() {
        auth();
        driver.findElement(By.xpath("//span[contains(.,'mozgovayalara23@mail.ru')]")).click();
        driver.findElement(By.xpath("//div[2]/div/div[2]/div/div[2]/div/div[2]")).click();

    }

    @Test
    void testSearchNoResult() {
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("TEST MESSAGE" + Keys.ENTER);
        driver.findElement(By.xpath("//input[@id='search']")).click();
    }

    @Test
    void testSendQuestion() {
        auth();
        driver.findElement(By.xpath("//a[contains(text(),'Спросить')]")).click();
        driver.findElement(By.xpath("//textarea[@id='question_text']")).sendKeys("TEST MESSAGE" + Keys.ENTER);
        driver.findElement(By.cssSelector(".Im7BZ > .Wjnkw")).click();
    }

    @Test
    void testAddQuestion() {
        auth();
        driver.findElement(By.xpath("//div[8]/a/span")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Помогите решить пример')]")).click();
        driver.findElement(By.cssSelector(".eSyWe svg")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Дополнить')]")).click();
        driver.findElement(By.xpath("//div[@id='question_additional']/div[2]/div/p")).sendKeys("Text");
        driver.findElement(By.xpath("//div[3]/div/div/a/div")).click();
    }

    @Test
    void testEditProfile() {
        auth();
        driver.findElement(By.xpath("//div[8]/a/span")).click();
        driver.findElement(By.xpath("//a[contains(.,'Редактировать профиль')]")).click();
        driver.findElement(By.xpath("//textarea[@id='desc']")).sendKeys("Text");
        driver.findElement(By.xpath("//div[3]/div/div/a/div")).click();
    }

    @Test
    void testAnswerToQuestion(){
        auth();
        driver.findElement(By.xpath("//span[contains(.,'Категории')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Юмор')]")).click();
        driver.findElement(By.cssSelector("div:nth-child(1) > .wjjeg .ZePiW")).click();
        driver.findElement(By.xpath("//div[@id='inputBody']/div[2]/div")).sendKeys("...");
        driver.findElement(By.xpath("//form/div[2]/a/div")).click();
    }

    @Test
    void testLike(){
        auth();
        driver.findElement(By.xpath("//div[8]/a/span")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Как сдать сессию')]")).click();
        driver.findElement(By.xpath("//div[3]/div/div/div[2]/div[2]/div/div/a/div/div[2]")).click();
    }

    @Test
    void testLeaders(){
        driver.findElement(By.xpath("//a[contains(.,'Лидеры')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'По активности')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'По количеству ответов')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'Все категории')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'Города и Страны')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'За неделю')]")).click();
        driver.findElement(By.xpath("//span[contains(.,'За месяц')]")).click();
    }

    void auth(){
        driver.findElement(By.xpath("//button[contains(.,'Войти')]")).click();
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("mozgovayalara23@mail.ru");
        driver.findElement(By.xpath("//span[contains(.,'Ввести пароль')]")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("lab3$777");
        driver.findElement(By.xpath("//span[text()='Войти']")).click();
    }
}
