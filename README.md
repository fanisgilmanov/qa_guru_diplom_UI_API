<a href="https://github.com/"><img alt="github.com" height="50" src="readme_files/technologies/github.svg"/></a>
# Демо проект по автоматизации тестирования на Java. Сайт shop1.emagazin.info.


## Содержание :bookmark_tabs:
* <a href="#stack">Cтек технологий</a>
* <a href="#objects">Объекты тестирования</a>
* <a href="#console">Запуск тестов из консоли</a>
* <a href="#code">Код</a>
    + <a href="#intelij">InteliJ IDEA, Java, JUnit 5, Selenide, Rest Assured</a>
    + <a href="#gradle">Gradle</a>
* <a href="#screenshot">Скриншоты</a>
  + <a href="#jenkins">Jenkins</a>
  + <a href="#allure">Allure TestOps, Allure Report</a>
  + <a href="#notifications">Telegram</a>



<a id="stack"></a>
## Cтек технологий :hammer_and_wrench:

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="readme_files/technologies/intelij_idea.svg" width="50"/></a>
<a href="https://www.java.com/"><img alt="Java" height="50" src="readme_files/technologies/java.svg" width="50"/></a>
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="readme_files/technologies/junit5.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="readme_files/technologies/selenide.svg" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="Rest Assured" height="50" src="readme_files/technologies/rest_assured.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="readme_files/technologies/selenoid.svg" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="readme_files/technologies/gradle.svg" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="readme_files/technologies/jenkins.svg" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="readme_files/technologies/allure_testops.svg" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure" height="50" src="readme_files/technologies/allure.svg" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="readme_files/technologies/github.svg" width="50"/></a>
</div>



<a id="objects"></a>
## Объекты тестирования :mag:

Разработаны автотесты для проверок:

* UI:

:white_check_mark: проверка title страницы 

:white_check_mark: проверка перехода по элементам  в навигационной панели 

:white_check_mark: проверка количества товаров 

:white_check_mark: проверка открытия карточка товара 

:white_check_mark: проверка добавление товара в корзину 

* API + UI:

:white_check_mark: проверка авторизации пользователя

* API:

:white_check_mark: проверка удачной регистрации пользователя 

:white_check_mark: проверка не удачной регистрации пользователя 







<a id="console"></a>
## Запуск тестов из консоли :computer:

```bash
gradle clean 
-Dbrowser=${BROWSER}
-DbrowserSize=${BROWSER_SIZE}
-Dhost=${HOST}

```

> 
> 
> `${BROWSER}` - браузер [ *chrome* <sub>(default)</sub> , *firefox*, *opera*]
> 
> `${BROWSER_SIZE}` - размер окна браузера  [ *1920x1080* <sub>(default)</sub>]
>
> `${HOST}` - хост прогона тестов [ *remote* <sub>(default)</sub> , *local* ]



<a id="code"></a>
## Код :floppy_disk:

<a id="intelij"></a>
#### <img alt="InteliJ IDEA" height="50" src="readme_files/technologies/intelij_idea.svg" width="50"/>InteliJ IDEA</a><img alt="Java" height="50" src="readme_files/technologies/java.svg" width="50"/>Java</a><img alt="JUnit 5" height="50" src="readme_files/technologies/junit5.svg" width="50"/>JUnit 5</a><img alt="Selenide" height="50" src="readme_files/technologies/selenide.svg" width="50"/>Selenide</a><img alt="Rest Assured" height="50" src="readme_files/technologies/rest_assured.png" width="50"/>Rest Assured</a>

> *Оформление кода автотестов*

```java


public class SauceDemoTests extends TestBase {

    @Test
    @DisplayName("Проверка добавление товара в корзину UI")
    void checkAddBasketTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            sauceDemoPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Открытия карточки товара 'Сопутствующие товары'", () -> {
            sauceDemoPage.openDetailCard(testData.nameTitleCard);
        });

        step("Добавление товара в корзину", () -> {
            sauceDemoPage.addToCardProduct();
        });
    }
}    
```



<a id="gradle"></a>
#### <img alt="Gradle" height="50" src="readme_files/technologies/gradle.svg" width="50"/>Gradle</a>

> *Автоматическая сборка проекта и управление зависимостями*

```groovy
plugins {
    id 'java-library'
    id 'io.qameta.allure' version '2.10.0'
    id 'io.freefair.lombok' version '6.0.0-m2'
}

repositories {
    mavenCentral()
}

def allureVersion = "2.18.1",
    selenideVersion = "6.5.0",
    junitVersion = "5.8.2",
    aspectjweaverVersion = "1.9.6",
    javafakerVersion = "1.0.2",
    restAssuredVersion = "4.3.1",
    ownerVersion = "1.0.12",
    assertjCoreVersion = "3.19.0",
    slf4jSimpleVersion = "1.7.29"
    
dependencies {
    testImplementation(
            "org.aspectj:aspectjweaver:$aspectjweaverVersion",
            "com.github.javafaker:javafaker:$javafakerVersion",
            "com.codeborne:selenide:$selenideVersion",
            "io.qameta.allure:allure-selenide:$allureVersion",
            "io.rest-assured:rest-assured:$restAssuredVersion",
            "io.qameta.allure:allure-rest-assured:$allureVersion",
            "org.aeonbits.owner:owner:$ownerVersion",
            "org.assertj:assertj-core:$assertjCoreVersion",
            "org.slf4j:slf4j-simple:$slf4jSimpleVersion",
            "org.junit.jupiter:junit-jupiter:$junitVersion")
}
```



<a id="screenshot"></a>
## Скриншоты :camera_flash:

<a id="jenkins"></a>
#### <img alt="Jenkins" height="50" src="readme_files/technologies/jenkins.svg" width="50"/>Jenkins</a>

> *Решение комплекса задач по сборке проекта, прогону автотестов, получению отчетов и отправке уведомлений по
результатам сборки*

<a href="https://jenkins.autotests.cloud/job/qa_guru_diplom_UI_API/">
<img src="readme_files/jenkins/JenkinsProject.png" alt="Jenkins">
</a>



<a id="allure"></a>
#### <img alt="Allure" height="50" src="readme_files/technologies/allure_testops.svg" width="50"/>Allure TestOps</a><img alt="Allure" height="50" src="readme_files/technologies/allure.svg" width="50"/>Allure Report</a>

> *Формирование отчетов по результам прогона автотестов*

<table>
     <tr>
        <td>
        <a href="https://allure.autotests.cloud/project/1635/dashboards">
        <img src="readme_files/allure/AllureTestOpsDashboard.png">
        </a>
        </td>
        <td>
        <a href="https://allure.autotests.cloud/project/1635/test-cases?treeId=0">
        <img src="readme_files/allure/AllureTestOpsTestCases.png">
        </a>
        </td>
    </tr>
    <tr>
        <td>
        <a href="https://jenkins.autotests.cloud/job/qa_guru_diplom_UI_API/13/allure/">
        <img src="readme_files/allure/AllureDashboard.png">
        </a>
        </td>
        <td>
        <a href="https://jenkins.autotests.cloud/job/qa_guru_diplom_UI_API/13/allure/#suites/e38dc78043549dd424b12c97d4a1cdb4/ac747a5ba4bbaae5/">
        <img src="readme_files/allure/AllureTestCases.png">
        </a>
        </td>
    </tr>
    <tr>
        <td>
        <a href="https://jenkins.autotests.cloud/job/qa_guru_diplom_UI_API/13/allure/#timeline">
        <img src="readme_files/allure/AllureTimeLine.png">
        </a>
        </td>
        <td>
        <a href="https://jenkins.autotests.cloud/job/qa_guru_diplom_UI_API/13/allure/#graph">
        <img src="readme_files/allure/AllureStatus.png">
        </a>
        </td>
</table>



<a id="notifications"></a>
#### <img alt="Telegram" height="50" src="readme_files/technologies/telegram.svg" width="50"/>Telegram</a>

> *Предоставление оперативной информации о результатах прогона автотестов*

<table>
     <tr>
        <td>
        <img src="readme_files/telegram/Notifications.png" alt="Telegram">
        </td>
    </tr>
 </table>   
