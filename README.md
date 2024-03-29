Данный проект создан для получения информации о файлах в заданном каталоге. С последующим получением количества файлов в корневой папке и подкаталогах. 
И общего количества страниц в pdf и docx документах.
Запуск программы может быть осуществлен следующими способами:

1) Запуск jar файла в командной строке(Windows) или терминале (Linux) с передачей пути к корневой папке в качестве аргумента.
Примеры:
java -jar CountDocs.jar "/src/test/resources/testData/",
java -jar CountDocs.jar "C:/Users/cnk-1/IdeaProjects/CountDocs/src/test/resources/testData/",
java -jar CountDocs.jar "/home/grigoriy/cnk-1/IdeaProjects/CountDocs/src/test/resources/testData/"

2) 
1. Запуск jar файла .
2. Запуск запроса 
  POST / HTTP/1.1
   Host: localhost:8082
   Content-Type: text/plain
   Content-Length: 66

C:/Users/cnk-1/IdeaProjects/CountDocs/src/test/resources/testData/

При осуществлении данных действий должен появиться результат в виде:
Documents: 10
Pages: 13


Данная программа протестированна в ОS : Linux, Windows.
Java version: java 17
стек: Spring boot, Spring web
библиотеки: itextpdf(обработка pdf файлов),  poi,commons-io,poi-ooxml(обработка docx файлов)
сборка: maven
тестирование: Junit