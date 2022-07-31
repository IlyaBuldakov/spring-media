# Зависимости проекта

Spring:
1. `org.springframework.boot:spring-boot-starter-parent` - базовый POM для проектов на Spring.
2. `org.springframework.boot:spring-boot-starter-web` - стартер для создания RESTful API.
3. `org.springframework.boot:spring-boot-starter-data-jpa` - стартер для работы со Spring Data.
4. `org.springdoc:springdoc-openapi-ui` - автоматическая генерация Swagger UI.

Работа с данными:
1. `org.postgresql:postgresql` - драйвер PostgreSQL для Spring Data.


Тестирование:
1. `org.junit.jupiter:junit-jupiter` - модульные тесты.
2. `org.mockito:mockito-core` - объекты-заглушки для внешних зависимостей.
3. `org.assertj:assertj-core` - человекочитаемые проверки.
4. `com.github.javafaker:javafaker` - генерация тестовых данных.

Разное:
1. `org.projectlombok:lombok` - полезные аннотации.
2. `io.vavr:vavr` - примитивы функционального программирования.
3. `commons-validator:commons-validator` - проверка значений.
4. `commons-codec:commons-codec` - алгоритмы кодирования, проверка значений.


Плагины:
1. `maven-checkstyle-plugin` - проверка кода на соответствие [Google Java Code Style](https://google.github.io/styleguide/javaguide.html).

# Инструкции

## Сборка проекта

## Установка проекта
В [application.yaml](src/main/resources/application.yaml) установить следующие параметры:
1. `spring.datasource.url` - ссылка на источник данных.
2. `spring.datasource.username` - имя пользователя СУБД.

## Запуск проекта
