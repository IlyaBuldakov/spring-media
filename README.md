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

Сборка проекта производится с помощью IntelliJ IDEA.

## Настройка проекта

1. Установите версию JDK – 17 (Preview).
2. Установите параметры подключения к БД в файле [application.yaml](src/main/resources/application.yaml)
   1. `spring.datasource.url` - ссылка базу данных в PostgreSQL.
   2. `spring.datasource.username` - имя пользователя PostgreSQL.
   3. `spring.datasource.password` - пароль пользователя PostgreSQL.

## Запуск проекта

Перед запуском проекта необходимо убедиться, что база данных в PostgreSQL доступна по указанной
ранее ссылке с указанным логином и паролем.

Запуск проекта производится с помощью IntelliJ IDEA.

## Не реализовано
1. Аутентификация пользователя.
2. Проверка доступности функционала для ролей пользователя.
3. Использование в БД связей между таблицами.
4. Проверка существования пользователя и задачи перед созданием задач, файлов, контента, комментариев.
5. Создание превью контента.
6. Более точный механизм определения типа файла.
7. Создание уведомлений, когда создается соответствующая сущность.
8. Переработать DTO в record.
9. Реализация автоматизированных тестов.
10. Добавление в сущность задачи файлов, контента, комментариев.
11. Настройка Docker.
