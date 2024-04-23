import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class GitHubTests {

  @BeforeAll
  static void beforeALL() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1024x768";

  }

  @Test
  void findExampleCode() {
    open("https://github.com/selenide/selenide");
    $("#wiki-tab").click();
    //кликаем на "Show 3 more pages…" чтобы развернуть весь список страниц
    $("li.Box-row.wiki-more-pages-link").click();

    //Проверяем, что есть страница SoftAssertions
    $("[data-filterable-for=wiki-pages-filter]").shouldHave(text("SoftAssertions"));
    //sleep(2000);

    $("a[href='/selenide/selenide/wiki/SoftAssertions']").click();
    sleep(2000);

    //проверяем что есть пример кода для JUnit5
    $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
            "class Tests {\n" +
            "  @Test\n" +
            "  void test() {\n" +
            "    Configuration.assertionMode = SOFT;\n" +
            "    open(\"page.html\");\n" +
            "\n" +
            "    $(\"#first\").should(visible).click();\n" +
            "    $(\"#second\").should(visible).click();\n" +
            "  }\n" +
            "}"));



  }
}
