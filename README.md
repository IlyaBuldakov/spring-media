# Зависимости проекта

Spring

- **org.springframework.boot:spring-boot-starter-parent** - родитель проекта.
- **org.springframework.boot:spring-boot-starter-web** - стартер для создания веб-приложения.
- **org.springframework.boot:spring-boot-starter-data-jpa** - стартер для Spring Data JPA.
- **org.springframework.boot:spring-boot-starter-security** - стартер для безопасности в проекте.

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
## Сборка проекта
Для сборки проекта необходимо выполнить команду `mvn package`.

## Настройка проекта
В [application.properties](src/main/resources/application.properties) установить следующие параметры:
1. `spring.datasource.url` - ссылка базу данных в PostgreSQL.
2. `spring.datasource.username` - имя пользователя PostgreSQL.
3. `spring.datasource.password` - пароль пользователя PostgreSQL.
4. `authentication.key` - ключ шифрования JWT.

## Установка проекта

## Запуск проекта
Для запуска проекта необходимо выполнить команду `mvn package` и запустить `media/media-{version}.jar`

## Не реализовано (что нужно доделать)

* Работа с уведомлениями
* Preview для контента
* Обработка Unauthorized исключения в фильтре (при обращении с невалидным или просроченным access токеном 
(или вообще без него) возвращать в теле метода представление об ошибке)
* Рефакторинг
  * Переделать некоторые классы в записи
  * Вынести общие элементы для нескольких классов в базовые
* Разобраться с валидацией файлов (создается .tmp файл для валидации при помощи Apache Tika)
* Полностью переписать класс ValuesValidator, созданный для валидации на этапе разработки (разбить валидацию полей)
* Добавить Swagger UI
* При обновлении задачи обнуляется task_id контента и файлов, которые на неё ссылаются. Исправить это
* Написать тесты заново для всего проекта
* Устранить нарушения спецификации (входящий файл в контроллере, определение автора комментария)
* Устранить ошибку с удалением пользователя (удаления не происходит, если на пользователя есть ссылки в БД, PSQLException)