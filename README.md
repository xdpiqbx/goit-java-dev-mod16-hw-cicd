# goit-java-dev-mod16-hw-cicd

[Встановив Jenkins](https://www.jenkins.io/doc/book/installing/linux/) у Docker контейнер з Ubuntu на свій NAS [Мій Jenkins](http://178.214.220.16:8808/)

Попередньо:
- apt-get install zip
- apt-get install vim
- apt-get install openssh-server
поправив `vi /etc/ssh/sshd_config`
```text
Port 2222
PasswordAuthentication yes
AllowUsers root artem
```
- apt-get install openjdk-17-jre
- apt-get install git 
- apt-get install sshpass

Була проблемка з `gradlew`
`fix` -> `git update-index --chmod=+x gradlew`

Jenkins:
- Створити `item`
- GitHub project
- Git
- Branch Specifier (blank for 'any')
- GitHub hook trigger for GITScm polling
  - http://178.214.220.16:8808/github-webhook/
- Виконати shell > [Копіювання (див нижче)]
- Виконати shell > ./gradlew build

Тепер треба подружить один контейнер докера з іншим, тобто з контейнера Jenkins-a по ssh зайти на контейнер Ubuntu куди буду копіювати зібраний Jenkins-ом .jar

`ssh user@178.214.220.16 -p2222`

щоб згенерувався fingerprint

Команда для копіювання (чомусь не спрацьовує з Jenkins на Ubuntu)
`sshpass -p 'password' scp -P 2222 /var/lib/jenkins/workspace/project/build/libs/spring-boot-app.jar max@178.214.220.16:/folder`

test
---

# login - `user`
# password - `jdbcDefault`

---

Як основу візьми проєкт з попереднього ДЗ. Поточне ДЗ можна виконувати в цьому ж репозиторії, або створити новий репозиторій - це на твій розсуд.

## Завдання №1 - додай модуль **Spring Security**
Додай до проєкту модуль **Spring Security** (достатньо додати стартер `spring-boot-starter-security`).

Запусти проєкт, і переконайсь, що модуль коректно підключивсь, і в логах запуску програми виводиться згенерований пароль. Переконайсь, що при заході у веб-інтерфейс тебе спочатку перекидає на сторінку авторизації, де тобі необхідно ввести логін та пароль, що виводивсь в консоль.

## Завдання №2 - встанови свій логін та пароль
Використовувати автозгенерований пароль кожного разу незручно.

Задай пароль `default` (наприклад, використовуючи файл `application.properties`).

Як результат, ти маєш мати можливість зайти у власну програму, використовуючи логін `user` та пароль `default`.

## Завдання №2.1 (необов'язкове)
Додай авторизацію у проєкт за допомогою **JDBC**. Додай міграцію, яка б додала користувача user з паролем `jdbcDefault`

## Завдання №3 - залий код на **Github**
Залий код на **Github** репозиторій. Переконайсь, що файл `.gitignore` налаштований коректно, і в репозиторій потрапили лише потрібні файли.

Результат ДЗ - посилання на репозиторій.