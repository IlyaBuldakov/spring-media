# Зависимости проекта

Spring:
1. `org.springframework.boot:spring-boot-starter-parent` – базовый POM для проектов на Spring.
2. `org.springframework.boot:spring-boot-starter-web` – стартер для создания RESTful API.
3. `org.springframework.boot:spring-boot-starter-data-jpa` – стартер для работы со Spring Data.

Swagger
1. `org.springdoc:springdoc-openapi-ui` – автоматическая генерация Swagger UI.

Тестирование:
1. `org.junit.jupiter:junit-jupiter` – модульные тесты.
2. `org.mockito:mockito-core` – объекты-заглушки для внешних зависимостей.
3. `org.assertj:assertj-core` – человекочитаемые проверки.
4. `com.github.javafaker:javafaker` – генерация тестовых данных.

Работа с данными:
1. `org.postgresql:postgresql` – драйвер PostgreSQL для Spring Data.
2. `org.apache.tika:tika-core` – библиотека для работы с файлами Apache Tika.
3. `org.apache.tika:tika-parsers` – модуль парсинга для Apache Tika.

Разное:
1. `org.projectlombok:lombok` – полезные аннотации.
2. `io.vavr:vavr` – примитивы функционального программирования.
3. `commons-validator:commons-validator` – проверка значений.
4. `commons-codec:commons-codec` – алгоритмы кодирования, проверка значений.

Плагины:
1. `maven-checkstyle-plugin` - проверка кода на соответствие [Google Java Code Style ](https://google.github.io/styleguide/javaguide.html).
2. `maven-compiler-plugin`, `maven-surefire-plugin` и `maven-failsafe-plugin` – запуск тестов на JUnit 5.

# Инструкции

## Сборка проекта
Установите параметры подключения к БД в файле [application.yaml](https://gitlab.study.htc-cs.com/root/java/java-22-1/-/blob/yaroslav.tarakanov-project/src/main/resources/application.yaml)
1. url – ссылка на БД.
2. username – имя пользователя для подключения к БД.
3. password – пароль пользователя для подключения к БД.

## Установка проекта
1. Провести синхронизацию файла pom.xml.

## Запуск проекта
1. Выполнить команду запуска для Application.main().