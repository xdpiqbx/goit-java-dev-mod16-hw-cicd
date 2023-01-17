# goit-java-dev-mod16-hw-cicd

[Встановив Jenkins](https://www.jenkins.io/doc/book/installing/linux/) у Docker контейнер з Ubuntu на свій NAS [Мій Jenkins](http://178.214.220.16:8808/)

Попередньо:
- apt-get install systemctl
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

Була проблемка з `gradlew`
`fix` -> `git update-index --chmod=+x gradlew`

Jenkins:
- Створити `item`
- GitHub project
- Git
- Branch Specifier (blank for 'any')
- GitHub hook trigger for GITScm polling
  - http://178.214.220.16:4444/github-webhook/
- Виконати shell > [Копіювання (див нижче)]
- Виконати shell > ./gradlew build

Тепер треба подружить один контейнер докера з іншим, тобто обмінятись своїми `/.ssh/id_rsa.pub`

Як мінімум віддати ключ з Jenkins-а на VPS

`ssh user@178.214.220.16 -p2222`

```text
ssh-keygen -o
ssh-copy-id -i /root/.ssh/id_rsa.pub -p2222 artem@178.214.220.16
```

У контейнері є користувач `jenkins` для нього теж треба генерувати ключі і віддати на `user@178.214.220.16 -p2222`

Також `scp` чомусь працює тільки з абсолютними шляхами, тобто:

`scp -P 2222 /var/lib/jenkins/workspace/project/build/libs/spring-boot-app.jar artem@178.214.220.16:/folder`

`scp -v -o StrictHostKeyChecking=no -P 2217 .....` - Так можна дивитись весь лог

В ідеалі команда для копіювання
`scp -P 2222 spring-boot-app.jar artem@178.214.220.16:/folder`

---

## Після того як Jenkins зміг все скопіювати на VPS (сусідній контейнер з Ubuntu)

Тепер на VPS треба створити сервіс з моїм застосунком щоб він автоматично стартував

---
### Create Service on VPS

- `spring-boot-app.service` - реєструємо додаток як сервіс у системі
- `spring-boot-app-watcher.path` - перезапускає `spring-boot-app.service` при оновленні
- `spring-boot-app-watcher.service` - 

#### spring-boot-app.service

`touch /etc/systemd/system/spring-boot-app.service`

```bash
[Unit]
Description=spring-boot-app

[Service]
User=root

WorkingDirectory=/dpiqb

ExecStart=/usr/bin/java -jar spring-boot-app.jar --spring.profiles.active=prod

SuccessExitStatus=143

TimeoutStopSec=10

Restart=always

RestartSec=30

[Install]
WantedBy=multi-user.target
```

#### spring-boot-app-watcher.path

`touch /etc/systemd/system/spring-boot-app-watcher.path`

```bash
[Path]
Unit=spring-boot-app-watcher.service
PathChanged=/dpiqb/spring-boot-app.jar

[Install]
WantedBy=multi-user.target
```

#### spring-boot-app-watcher.service

`touch /etc/systemd/system/spring-boot-app-watcher.service`

```bash
[Unit]
Description=spring-boot-app-watcher
StartLimitIntervalSec=10
StartLimitBurst=5

[Service]
Type=oneshot
ExecStart=/bin/systemctl restart spring-boot-app.service

[Install]
WantedBy=multi-user.target
```

systemctl enable spring-boot-app-watcher.{path,service}
systemctl start spring-boot-app-watcher.{path,service}

systemctl status spring-boot-app

systemctl daemon-reload

ERROR:systemctl: dbus.service: Executable path is not absolute, ignoring:
@/usr/bin/dbus-daemon @dbus-daemon --system --address=systemd: --nofork --nopidfile --systemd-activation --syslog-only

Перевірте свою систему ініціалізації `ps -p1`, як правило це `systemctl` і має PID 1. У Docker Ubuntu це `bash`

`/etc/bash.bashrc`

На VPS
- service ssh start
- systemctl start spring-boot-app-watcher.path
- systemctl start spring-boot-app-watcher.service
- systemctl start spring-boot-app

На Jenkins
- service ssh start
- service jenkins start
---

# login - `user`
# password - `jdbcDefault`