# Список изменений
Все изменения в этом проекте будут задокументированы в этом файле.

Формат основан на [Keep a Changelog](https://keepachangelog.com/ru/1.0.0/),
и этот проект соблюдает [семантическое версионирование](https://semver.org/lang/ru/).

## [0.5.0] - 2022-05-21
### Добавлено
- интерфейс сценария использования принимающий параметры
- в слое `domain` сценарий использования `GetAllUsers` получение всех пользователей
- модульные тесты для сценария `GetAllUsers`

### Изменено
- интерфейс `UseCase`
- сценарий использования `GetUserById`
- репозиторий для сущности `user`
- модульные тесты для сценария `GetUserById`

## [0.4.0] - 2022-05-03
### Добавлено
- зависимость `spring-boot-starter-web`
- зависимость `lombok`
- зависимость `io.vavr:vavr`
- зависимость `org.assertj:assertj-core`
- плагин `maven-compiler-plugin`
- в слое `domain` сущность `user`
- в слое `domain` сущность `failures`
- в слое `domain` репозиторий для сущности `user`
- в слое `domain` сценарий использования `GetUserById`
- модульные тесты для сценария `GetUserById`

### Удалено
- зависимость `spring-boot-starter`

## [0.3.0] - 2022-04-14
### Добавлено
- checkstyle с правилами от google на этапе сборки
- зависимость `maven-checkstyle-plugin`

## [0.2.2] - 2022-04-13
### Изменено
- ссылки tags изменены на ссылки compare

## [0.2.1] - 2022-04-13
### Исправлено
- последняя версия должна идти в начале файла

## [0.2.0] - 2022-04-13
### Добавлено
- файлы idea

## [0.1.0] - 2022-04-13
### Добавлено
- файлы проекта

## [0.Y.Z] - YYYY-MM-DD
### Добавлено
### Изменено
### Удалено
### Исправлено

[0.5.0]: https://gitlab.study.htc-cs.com/root/java/java-22-1/-/compare/petr.klyukin-v0.4.0...petr.klyukin-v0.5.0
[0.4.0]: https://gitlab.study.htc-cs.com/root/java/java-22-1/-/compare/petr.klyukin-v0.3.0...petr.klyukin-v0.4.0
[0.3.0]: https://gitlab.study.htc-cs.com/root/java/java-22-1/-/compare/petr.klyukin-v0.2.2...petr.klyukin-v0.3.0
[0.2.2]: https://gitlab.study.htc-cs.com/root/java/java-22-1/-/compare/petr.klyukin-v0.2.1...petr.klyukin-v0.2.2
[0.2.1]: https://gitlab.study.htc-cs.com/root/java/java-22-1/-/compare/petr.klyukin-v0.2.0...petr.klyukin-v0.2.1
[0.2.0]: https://gitlab.study.htc-cs.com/root/java/java-22-1/-/compare/petr.klyukin-v0.1.0...petr.klyukin-v0.2.0
[0.1.0]: https://gitlab.study.htc-cs.com/root/java/java-22-1/-/compare/petr.klyukin-v0.1.0...klyukin.petr-project

