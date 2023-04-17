# Дипломный проект по профессии «Тестировщик»

Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### Документация

[План автоматизации](https://github.com/zilyabayram/Diploma/blob/cf13a99f3c1ae557004e7b9aaec979ae774d5621/docs/Plan.md)

### Начало работы
* Склонировать данный GitHub репозиторий на свой локальный компьютер;
* Запустить IntelliJ IDEA в папке скаченного проекта;
* Запустить Docker Desktop. 

### Запуск тестов

#### Запуск приложениям с подключением к SQL, запуск тестов и выгрузка отчета
1. Ввести в терминале IntelliJ IDEA команду для запуска контейнера `docker-compose up`
2. Ввести в новом терминале IntelliJ IDEA команду для запуска приложения `java -jar aqa-shop.jar` 
3. Ввести в новом терминале IntelliJ IDEA команду для запуска автотестов `./gradlew clean test` 
4. Ввести терминале далее команду для построения отчета Allure `./gradlew allureServe`

#### Запуск приложениям с подключением к Postresql, запуск тестов и выгрузка отчета
1. Ввести в терминале IntelliJ IDEA команду для запуска контейнера `docker-compose up`
2. Ввести в новом терминале IntelliJ IDEA команду для запуска приложения `-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app"  "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar aqa-shop.jar`
3. Ввести в новом терминале IntelliJ IDEA команду для запуска автотестов `./gradlew clean test -Durl=jdbc:postgresql://localhost:5432/app`
4. Ввести терминале далее команду для построения отчета Allure `./gradlew allureServe`
