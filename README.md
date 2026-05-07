# IF_HW_ArutyunyanArtavazd

## Требования

- Java 21 (LTS) или выше
- Maven 3.6 или выше
- Google Chrome

Основные зависимости (версии из `pom.xml`):

| Библиотека     | Версия  |
|----------------|---------|
| JUnit Jupiter  | 5.11.4  |
| Selenide       | 7.15.0  |
| Allure         | 2.25.0  |
| AspectJ Weaver | 1.9.25.1|
| Logback        | 1.5.18  |

---

## Структура проекта

```
src
+---main
|   \---java
|       +---config
|       |       AllureConfig.java
|       |       ConfigProvider.java
|       |       TestDataConfig.java
|       |
|       +---models
|       |   \---enums
|       |           IssueStatus.java
|       |           IssueType.java
|       |
|       +---pages
|       |   |   AllIssuesPage.java
|       |   |   BasePage.java
|       |   |   BrowseProjectsPage.java
|       |   |   IssueDetailsPage.java
|       |   |   IssuesSearchPage.java
|       |   |   LoginPage.java
|       |   |   RapidBoardPage.java
|       |   |
|       |   \---components
|       |           CreateIssueModalComponent.java
|       |           HeaderComponent.java
|       |           SidebarComponent.java
|       |
|       \---steps
|               IssueDetailsSteps.java
|               IssueSteps.java
|               LoginSteps.java
|               ProjectSteps.java
|
\---test
    +---java
    |       HWScenarioTest.java
    |       WebHooks.java
    |
    \---resources
            allure.properties
            config.properties
            junit-platform.properties
            logback.xml
            selenide.properties
```

| Пакет / папка        | Содержимое                                             |
|----------------------|--------------------------------------------------------|
| `config`             | Классы конфигурации: Selenide, Allure, тестовые данные |
| `models/enums`       | Перечисления для статусов и типов задач                |
| `pages`              | Page Object классы для каждой страницы приложения      |
| `pages/components`   | Переиспользуемые компоненты страниц                    |
| `steps`              | Step-классы для группировки бизнес-логики тестов       |
| `test/java`          | Тестовые классы и хуки                                 |
| `test/resources`     | Файлы конфигурации                                     |

---

## Структура конфигурационных файлов

Все файлы конфигурации находятся в `src/test/resources/`.

### `config.properties` — тестовые данные

Учётные данные, название проекта и данные для создания задач.

```properties
# Credentials
user.login=AT1
user.password=Qwerty123

# Project
project.name=Test

# Test task (существующая задача для проверки статуса и версии)
test.task.name=TestSeleniumATHomework
test.task.version=Version 2.0

# Issue creation (данные для создания новых задач в тестах)
issue.summary=Test task summary
issue.description=Test task description
issue.environment=Test task environment
```

| Ключ                | Описание                                     |
|---------------------|----------------------------------------------|
| `user.login`        | Логин пользователя для входа в EduJira       |
| `user.password`     | Пароль пользователя                          |
| `project.name`      | Ключ проекта (используется в URL и поиске)   |
| `test.task.name`    | Имя существующей задачи для проверки деталей |
| `test.task.version` | Ожидаемая версия этой задачи                 |
| `issue.summary`     | Тема создаваемой задачи/бага                 |
| `issue.description` | Описание создаваемой задачи/бага             |
| `issue.environment` | Окружение                                    |

---

### `selenide.properties` — настройки браузера

```properties
selenide.baseUrl=https://edujira.ifellow.ru
selenide.browser=chrome
selenide.timeout=16000
```

| Ключ                | Описание                                   |
|---------------------|--------------------------------------------|
| `selenide.baseUrl`  | Базовый URL тестируемого приложения        |
| `selenide.browser`  | Браузер для запуска                        |
| `selenide.timeout`  | Таймаут ожидания элементов в миллисекундах |

---

### `allure.properties` — настройки отчётности

```properties
allure.results.directory=target/allure-results
allure.screenshots=true
allure.include.selenide.steps=true
allure.save.page.source=false
```

| Ключ                           | Описание                                                        |
|--------------------------------|-----------------------------------------------------------------|
| `allure.results.directory`     | Директория для сохранения результатов Allure                   |
| `allure.screenshots`           | Прикреплять скриншоты к шагам в отчёте (`true`/`false`)       |
| `allure.include.selenide.steps`| Включать низкоуровневые шаги Selenide в отчёт (`true`/`false`) |
| `allure.save.page.source`      | Сохранять HTML-источник страницы при падении (`true`/`false`)  |

---

### `junit-platform.properties` — настройки выполнения тестов

```properties
junit.jupiter.execution.parallel.enabled=false
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.timeout.default=60s
```

| Ключ                                          | Описание                                                     |
|-----------------------------------------------|--------------------------------------------------------------|
| `junit.jupiter.execution.parallel.enabled`    | Включить параллельный запуск тестов (`true`/`false`)        |
| `junit.jupiter.execution.parallel.mode.default` | Режим параллелизма: `concurrent` — одновременно, `same_thread` — последовательно |
| `junit.jupiter.execution.timeout.default`     | Максимальное время выполнения одного теста         |


---

## Запуск тестов

```bash
mvn clean test
```

Результаты сохраняются в `target/allure-results`.

---

## Allure-отчёт

Сгенерировать статичный HTML-отчёт:

```bash
mvn allure:report
```

Отчёт будет доступен по пути `target/site/allure-maven-plugin/index.html`.

Запустить отчёт на локальном сервере (автоматически открывает браузер):

```bash
mvn allure:serve
```

---

## Запуск через Maven-плагин в IntelliJ IDEA

Запуск тестов: панель Maven → Lifecycle → `clean`, затем `test`.

Генерация отчёта: панель Maven → Plugins → allure → `allure:report` или `allure:serve`.