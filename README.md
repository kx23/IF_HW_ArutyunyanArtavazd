# IF_HW_ArutyunyanArtavazd

## Требования

- Java 11 или выше
- Maven 3.6 или выше
- Chrome или Firefox

Основные зависимости (версии из `pom.xml`):

| Библиотека        | Версия  |
|-------------------|---------|
| JUnit Jupiter     | 5.11.4  |
| Selenide          | 7.15.0  |
| Allure            | 2.25.0  |
| AspectJ Weaver    | 1.9.21  |
| Logback           | 1.5.18  |

---

## Конфигурация

Перед запуском убедитесь, что файл `src/test/resources/config.properties` содержит корректные учетные данные:

```properties
user.login=real_login
user.password=real_password
```
 
---
## Запуск тестов

Запуск всех тестов:

```bash
mvn clean test
```

Результаты сохраняются в `target/allure-results`.

---

## Allure-отчет

Сгенерировать статичный HTML-отчет:

```bash
mvn allure:report
```

Отчет будет доступен по пути `target/site/allure-maven-plugin/index.html`.

Запустить отчет на локальном сервере (автоматически открывает браузер):

```bash
mvn allure:serve
```

---

## Запуск через Maven-плагин в IntelliJ IDEA

Запуск тестов: панель Maven -> Lifecycle -> clean, затем test.

Генерация отчета: панель Maven -> Plugins -> allure -> allure:report или allure:serve.

---
