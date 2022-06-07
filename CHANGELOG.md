# Список изменений
___
В данном файле поэтапно изложены все изменения проекта

Формат списка изменений - [Keep a Changelog](https://keepachangelog.com/ru/1.0.0/). Проект следует [семантическому версионированию](https://semver.org/lang/ru/)

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



