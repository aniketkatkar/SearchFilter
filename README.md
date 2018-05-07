# Search Filer using PHP


Enter your domain name with your php file extension in MainActivity.java

    String HttpURL = "http://example.com/SubjectFullForm.php";

Enter your Database credentials in DatabaseConfig.php
```html
<?php

//Define your host here.
$HostName = "";

//Define your database username here.
$HostUser = "";

//Define your database password here.
$HostPass = "";

//Define your database name here.
$DatabaseName = "";

?>
```
Enter your database table name in SubjectFullForm.php
```html
$sql = "SELECT * FROM dbname";
```
Make necessary changes as per your db in the MainActivity.java file.

# License

    Copyright 2018 Aniket Katkar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
