
Установка openjdk-java8, Ubuntu:

`sudo apt update`

`sudo apt install openjdk-17-jdk`

`sudo update-alternatives --config java`

Донастроить локали:

`apt-get install --reinstall locales && sudo dpkg-reconfigure locales`

`dpkg-reconfigure locales`

Команда строится по принципу: 

`./gradlew` запускает тесты, вытягивая актуальные изменения из репозитория. Дополнительные команды выполняются на текущей версии.

`./gradlew clean test --tests api*` запускает тесты в пакете api

`./gradlew clean test --tests api.CashTest` запускает все тесты в классе CashTest пакета api.

`./gradlew clean test --tests api.Errors.createCashTransferDeclineByBankTest` запускает конкретный тест из пакета api и класса CashTest

`./gradlew clean test --tests *.Errors` запускает все тесты Errors из всех пакетов.

`./gradlew -Dconf=dev4` - запускает все тесты на окружении dev4 при наличии соответствующего конфига

`./gradlew gatlingRun` - запускает нагрузочные тесты из `src/test/java/load/first/ProbeSimulation.java`

`./gradlew.bat gatlingRun -Dusers=100` - запускает нагрузочные тесты 100 пользователей. Windows

`./gradlew gatlingRun -Dusers=100` - запускает нагрузочные тесты 100 пользователей. Linux/Mac

Конфиги окружений лежат в папке conf в корне проекта.

После прогона тестов сгенерируется отчет:`/build/reports/tests/test/index.html`
Если установить Allure CommandLine то можно генерировать более подробный отчет следующим набором команд:
`cd ./build; allure serve`

Структура: 

`api`               - Апи тесты.

`core`              - Всякие настройки и утилиты, генерация данных, обработка конфигураций.

`helper`            - Здесь описаны все "ручки" с которыми умеют работать тесты. 1 класс = 1 блок.

`model`             - Здесь описана структура данных для запросов.

`resources\schemas` - json-схемы по которым идет проверка структура данных ответов сервера. 

`load\`             - зачатки нагрузочных тестов