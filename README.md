# IF_HW_ArutyunyanArtavazd

Фреймворк для автоматизированного тестирования REST API на основе **Cucumber + RestAssured + Allure**.

---

## Требования

- Java 21 (LTS) или выше
- Maven 3.6 или выше

Основные зависимости (версии из `pom.xml`):

| Библиотека            | Версия  |
|-----------------------|---------|
| Cucumber              | 7.x     |
| RestAssured           | 5.x     |
| Allure Cucumber7 JVM  | 2.x     |
| Owner                 | 1.0.12  |
| Logback               | 1.x     |

---

## Структура проекта

```
src/
├── main/java/
│   ├── api/                  
│   │   ├── ifellow/          
│   │   └── rickAndMorty/    
│   ├── config/              
│   ├── dto/                  
│   │   ├── ifellow/         
│   │   └── rickAndMorty/    
│   ├── steps/                
│   │   ├── ifellow/         
│   │   └── rickAndMorty/    
│   └── utils/               
│
├── main/resources/
│   ├── Credentials.json      
│   └── logback.xml           
│
└── test/
    ├── java/
    │   ├── cucumber/steps/   
    │   ├── hooks/            
    │   └── CucumberApiRunnerTest.java
    └── resources/
        ├── features/         
        │   ├── Auth.feature
        │   └── RickAndMorty.feature
        ├── config.properties
        ├── allure.properties
        ├── junit-platform.properties
        └── tpl/              
```

---

## Структура конфигурационных файлов

### `config.properties` — базовые URL

```properties
rickandmorty.base_url=https://rickandmortyapi.com/api
ifellow.base_url=http://localhost:8080/api
```

| Ключ                    | Описание                                  |
|-------------------------|-------------------------------------------|
| `rickandmorty.base_url` | Базовый URL публичного Rick and Morty API |
| `ifellow.base_url`      | Базовый URL iFellow Auth API              |

---

### `junit-platform.properties` — настройки Cucumber и плагинов

```properties
cucumber.plugin=pretty,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm
cucumber.glue=cucumber.steps,hooks
```


| Ключ               | Описание                                      |
|--------------------|-----------------------------------------------|
| `cucumber.plugin`  | Подключаемые плагины (pretty, Allure и др.)   |
| `cucumber.glue`    | Пакеты со степ-дефинишнами и хуками           |

---

### `Credentials.json` — учётные данные

```json
{
  "username": "AT1",
  "password": "Qwerty123!"
}
```


---

### `allure.properties` — настройки отчётности

```properties
allure.results.directory=target/allure-results
```

| Ключ                       | Описание                                      |
|----------------------------|-----------------------------------------------|
| `allure.results.directory` | Директория для сохранения результатов Allure  |

---

### `logback.xml` — настройки логирования

---

## Сценарии

### Auth.feature — iFellow Auth API

| Тег                    | Сценарий                                   |
|------------------------|--------------------------------------------|
| `@Register`            | Регистрация пользователя — успешно         |
| `@LoginNotFound`       | Вход с несуществующим пользователем — 401  |
| `@LoginWrongPassword`  | Вход с неверным паролем — 401              |
| `@LoginSuccess`        | Вход с корректными данными — токен получен |
| `@LogoutUnauthorized`  | Выход с невалидным токеном — 401           |
| `@LogoutSuccess`       | Выход с валидным токеном — успешно         |

### RickAndMorty.feature — Rick and Morty API

| Тег                        | Сценарий                                                                        |
|----------------------------|---------------------------------------------------------------------------------|
| `@LastCharacterComparison` | Найти последний эпизод Morty Smith → последний персонаж эпизода → сравнить детали |

---

## Запуск тестов

Запуск всех тестов:

```bash
mvn clean test
```

Запуск по тегу:
```bash
mvn clean test -Dcucumber.filter.tags=@Auth
mvn clean test -Dcucumber.filter.tags=@RickAndMorty
```

Запуск через раннер (из IntelliJ IDEA):

Откройте класс `CucumberApiRunnerTest.java` и нажмите ▶ рядом с объявлением класса.

---

## Allure-отчёт

Сгенерировать статичный HTML-отчёт:

```bash
mvn allure:report
```

Отчёт будет доступен по пути `target/site/allure-maven-plugin/index.html`.

Запустить отчёт на локальном сервере:

```bash
mvn allure:serve
```

