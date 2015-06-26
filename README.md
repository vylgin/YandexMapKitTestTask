# YandexMapKitTestTask

![Screenshot](https://raw.githubusercontent.com/vylgin/YandexMapKitTestTask/master/Screenshots/map1.png)
&nbsp;
![Screenshot](https://raw.githubusercontent.com/vylgin/YandexMapKitTestTask/master/Screenshots/task_info_1.png)

![Screenshot](https://raw.githubusercontent.com/vylgin/YandexMapKitTestTask/master/Screenshots/map2.png)
&nbsp;
![Screenshot](https://raw.githubusercontent.com/vylgin/YandexMapKitTestTask/master/Screenshots/task_info_2.png)

### Тестовое задание:

Необходимо реализовать приложение, которое будет отображать на карте "задания", которые пользователь может выполнять за деньги. Для отображения карт необходимо использовать Yandex Mapkit. Для того, чтобы получить список заданий, необходимо выполнить HTTP GET запрос:

**http://test.boloid.com:9000/tasks**

На карте задания должны отображаться как метки (OverlayItems). При тапе на метку необходимо показывать Balloon, в котором нужно показывать значение поля **title** . При тапе на балун пользователю нужно показывать экран с описанием задания:

* Короткое описание из поля **text**
* Длинное описание из поля **longText**
* Цены из списка prices
* Дату добавления из поля **date**
* Описание местоположения из поля **locationText**

Кроме того, у пользователя должна быть возможность обновить список заданий на экране карты. Никаких прочих требований к UI нет, но чистый, приятный и дружелюбный дизайн будет рассматриваться как плюс.
В качестве сборщика нужно использовать gradle.
В качестве результата мы ожидаем ссылку на репозиторий на github/bitbucket либо архив с кодом и собранным apk файлом.