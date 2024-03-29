<p>Данный проект создан для получения информации о файлах в заданном каталоге.</p> <p>С последующим получением количества файлов в корневой папке и подкаталогах. 
И общего количества страниц в pdf и docx документах.

<ol><p><b>Запуск программы может быть осуществлен следующими способами:</b></p>
<li>Запуск jar файла в командной строке(Windows) или терминале (Linux) с передачей пути к корневой папке в качестве аргумента.</li>
<p><b>Примеры:</b></p>
<p>java -jar CountDocs.jar "/src/test/resources/testData/",</p>
<p>java -jar CountDocs.jar "C:/Users/cnk-1/IdeaProjects/CountDocs/src/test/resources/testData/",</p>
<p>java -jar CountDocs.jar "/home/grigoriy/IdeaProjects/CountDocs/src/test/resources/testData/"</p>
<li>Через web-интерфейс
<ol>
<li>Запуск jar файла.</li>
<li>Запуск запроса </li>
<p>POST / HTTP/1.1<br/>
Host: localhost:8082<br/>
 Content-Type: text/plain<br/>
Content-Length: 66<br/><br/>
/src/test/resources/testData/
</p>

<p></p>
<p></p>
<p></p>
</ol>

</li>
</ol>











При осуществлении данных действий должен появиться результат в виде:
Documents: 10<br/>
Pages: 13<br/>


Данная программа протестированна в ОS : Linux, Windows.<br/>
Java version: java 17<br/>
стек: Spring boot, Spring web<br/>
библиотеки: itextpdf(обработка pdf файлов),  poi,commons-io,poi-ooxml(обработка docx файлов)<br/>
сборка: maven<br/>
тестирование: Junit<br/>