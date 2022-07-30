# Зависимости проекта
___

Spring

- **org.springframework.boot:spring-boot-starter-parent** - родитель проекта.
- **org.springframework.boot:spring-boot-starter-web** - стартер для создания веб-приложения.
- **org.springframework.boot:spring-boot-starter-data-jpa** - стартер для Spring Data JPA.
- **org.springframework.boot:spring-boot-starter-security** - стартер для безопасности в проекте.
- ****

Тестирование

 - **org.junit.jupiter:junit-jupiter** - модульные тесты.

 - **org.mockito:mockito-junit-jupiter** - объекты-заглушки с поддержкой JUnit.

 - **org.assertj:assertj-core** - человекочитаемые проверки.

Валидаторы

- **commons-validator:commons-validator** - проверка Email.
- **org.apache.tika:tika-core** - определение формата входящих файлов.
- **org.apache.tika:tika-parsers** - вспомогательная зависимость для tika-core.

Разное

- **org.postgresql:postgresql** - драйвер PostgreSQL.

- **org.projectlombok:lombok** - полезные аннотации.

- **io.vavr:vavr** - примитивы функционального программирования.
- **com.auth0:java-jwt** - генерация и валидация JWT токенов.

# Инструкции
___
## Сборка проекта
___
## Настройка проекта

В [application.properties](src/main/resources/application.properties) установить следующие параметры:
1. `spring.datasource.url` - ссылка базу данных в PostgreSQL.
2. `spring.datasource.username` - имя пользователя PostgreSQL.
3. `spring.datasource.password` - пароль пользователя PostgreSQL.
4. `authentication.key` - ключ шифрования JWT.

## Установка проекта
___
## Запуск проекта
___