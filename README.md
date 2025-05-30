# Library Management System

Система управления библиотекой на Spring Boot, предоставляющая API для управления книгами, пользователями и выдачами книг.

## Проверяющему

Актуальная версия проекта находится в ветке `deadline`, в ней реализованы все темы кроме брокеров сообщений. 

Из-за большой загруженности в этом семестре у меня не получилось найти времени для реализации всего задуманного функционала, но я буду продолжать работать над проектом летом.

## Локальный запуск

1. Клонируйте репозиторий:
```bash
git clone git@github.com:weykko/library-system.git
```

2. Настройте базу данных в `application.yaml`:
```yaml
spring:
    datasource:
        url: jdbc:postgresql://localhost:5432/library-system
        username: postgres
        password: password
```

3. Запустите приложение
```bash
./mvnw spring-boot:run
```

## Документация API
После запуска приложения документация доступна по адресу:  
http://localhost:8080/swagger-ui.html