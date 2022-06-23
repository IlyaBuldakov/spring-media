# Список изменений
___
В данном файле поэтапно изложены все изменения проекта

Формат списка изменений - [Keep a Changelog](https://keepachangelog.com/ru/1.0.0/). Проект следует [семантическому версионированию](https://semver.org/lang/ru/)

## [v0.10.0] - 2022-06-23
### Добавлено
- создан конструктор в классе UserDto
- создан конструктор в классе RoleDto
### Изменено
- мелкое исправление в FakeUserRepositories
- изменен тест TaskTest
## [v0.9.0] - 2022-06-22
### Добавлено
- создан фейковый репозиторй для класса User
### Изменено
- изменения связанные с возвращением json TaskController.getAll();
- метод контроллера TaskController.getAll() возвращает ответ полностью соответствующий документации
## [v0.8.0] - 2022-06-21
### Добавленно
- добавлено параметр Status в конструктор класса ru.kiryanovid.domain.entity.task
### Изменено
- изменен класс ru.kiryanovid.application.dto.task.TaskRequestDto
  добавленны геттеры в перечисление ru.kiryanovid.domain.entity.task.Status
- Изменен метод getAll() в TaskController.
- Изменена сущность TaskListDto
## [v0.7.0] - 2022-06-20
### Изменено
- Изменены перечисления ContentType и Status.

## [v0.6.0] - 2022-06-19
### Добавлено
- созданы методы контроллера TaskController, согласно документации
- 
### Изменено
- Добавлены поля в классах File и Comment. Классы File, Comment, CommentDto, CommentRequestDto, FileDto 
перенесены в соответствующие пакеты.
- изменены классы пакета ru.kiryanov.domain.usecases.task для работы с контроллером.
- 
## [v0.5.0] - 2022-06-07
### Добавлено
- добавлены поля в класс Comment
- в класс File добавлены поля
- класс File перенесен в пакет ru.kiryanov.domain.entity.file
-
### Изменено
- класс Comment перенесен в пакет ru.kiryanov.domain.entity.comment.
- Исправлены импорты связанные с классом.
- классы CommentDto и CommentRequestDto  перенесены в пакет ru.kiryanov.application.dto.comment.
- Исправлены импорты связанные с классом.
- классы FileDto и FileRequestDto  перенесены в пакет ru.kiryanov.application.dto.file
## [v0.4.0] - 2022-05-29
### Добавлено
- добавлены классы реализующие интерфейс UseCase
- добавлена зависимость io.vavr:vavr.
- добавлена зависимость org.springframework.boot

### Изменено
- все пакеты перенесены в пакет ru.kiryanov
- мелкие изменения связанные с изменением названий пакетов.

## [v0.3.0] - 2022-05-23  

### Добавлено
- Конструктор @AllArgsConstructor в класс Task
- в класс Content добавлены поля
- создан тест для класса Content
### Изменено
- Класс Content перенесен в пакет ru.kiryanovid.domain.entity.content
- Классы DTO связанные с контентом перенесены в пакет ru.kiryanovid.application.dto.content

## [v0.2.0] - 2022-05-21

### Добавлено
- Созданы все сущности.
- Создан пакет ru.kiryanovid.application.dto.task
- Добавлен тест класса Task
### Изменено
- сущность Task перенесена в пакет ru.kiryanovid.domain.entity.task
- enum класс Type переименован в ContentType
- сущности DTO из пакета ru.kiryanovid.domain.entity.task перенесены в пакет ru.kiryanovid.application.dto.task

## [v0.1.0] - 2022-04-16

### Добавлено
- Создана заготовка проекта.
- Все сущности.
- Написана часть документации Javadoc



