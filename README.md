# Онлайн калькулятор на микросервисной архитектуре
### 1. Создал файл "CalcController.java" и добавил код для сложения и вычитания заданых чисел  
```java
package ru.neoflex.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    @GetMapping("/plus/{a}/{b}")
    public Integer Sum (@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a+b;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer Diff(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a-b;
    }
}
```
Результат тестирования контроллера:  
![Тестирование суммы](https://github.com/MatveyBashirov/Calculator/blob/main/images/test1.png?raw=true, "Тестирование суммы")
![Тестирование разности](https://github.com/MatveyBashirov/Calculator/blob/main/images/test2.png?raw=true, "Тестирование разности")
### 2. Подключил Swagger, добавив в pom.xml необходимую зависимость
```java
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.4</version>
</dependency>
```
Интерфейс Swagger:  
![Контроллер суммы Рис.1](https://github.com/MatveyBashirov/Calculator/blob/main/images/swagger1.png?raw=true, "Контроллер суммы Рис.1")
![Контроллер суммы Рис.2](https://github.com/MatveyBashirov/Calculator/blob/main/images/swagger2.png?raw=true, "Контроллер суммы Рис.2")
![Контроллер разности Рис.1](https://github.com/MatveyBashirov/Calculator/blob/main/images/swagger3.png?raw=true, "Контроллер разности Рис.1")
![Контроллер разности Рис.2](https://github.com/MatveyBashirov/Calculator/blob/main/images/swagger4.png?raw=true, "Контроллер разности Рис.2")  
### 3. Добавил необходимые тесты на методы контроллера в новом классе "CalcControllerTest"  
```java
package ru.neoflex.practice.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CalcController.class)
class CalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalcController calcController;

    @Test
    public void sum() throws Exception {

        int a = 2;
        int b = 2;
        int expectedSum = a+b;

        BDDMockito.given(calcController.Sum(a,b))
                .willReturn(a+b);

        mockMvc.perform(MockMvcRequestBuilders.get("/plus/{a}/{b}",a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedSum)));
    }

    @Test
    public void diff() throws Exception {

        int a = 5;
        int b = 2;
        int expectedDiff = a-b;

        BDDMockito.given(calcController.Diff(a,b))
                .willReturn(a-b);

        mockMvc.perform(MockMvcRequestBuilders.get("/minus/{a}/{b}", a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedDiff)));
    }
}
```
Результаты выполнения тестов:  
![Выполнения тестов](https://github.com/MatveyBashirov/Calculator/blob/main/images/test3.png?raw=true, "Выполнения тестов")  
### 4. Добавил соответствующую зависимость для подключения H2 Database  
```java
<dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
</dependency>     
```
После добавления зависимости, добавил новые директории для базы данных(database) и репозитория(repository)  
### 4. Во вновь созданной директории "database" создал Java-класс "resultTable" хранящий в себе таблицу БД  
```java
package ru.neoflex.practice.database;

import jakarta.persistence.*;

@Entity
@Table(name = "RESULTS")
public class resultTable {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long ID;

    @Column(name = "first_number")
    private int firstNum;

    @Column(name = "action")
    private String action;

    @Column(name = "second_number")
    private int secondNum;

    @Column(name = "result")
    private int result;

    /*Комментарий*/
    public resultTable(){

    }

    public resultTable(Integer a, String s, Integer b, int result) {
        this.firstNum = a;
        this.secondNum = b;
        this.action = s;
        this.result = result;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}     
```
### 5. Во вновь созданной директории "repository" создал интерфейс "resultRep"  
```java
package ru.neoflex.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import ru.neoflex.practice.database.resultTable;

import java.util.List;

@Repository
public interface resultRep extends JpaRepository<resultTable, Long> {
    @Query("SELECT r.result FROM resultTable r")
    List<Integer> findAllResultsOnly();
}
```
В репозитории также добавлен пользовательский метод, выполняющий запрос для получения из таблицы БД только столбца "result"  
### 5. В файле application.properties настроил конфигурацию базы данных  
```java
spring.h2.console.enabled=true

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=bashirov
spring.datasource.password=practice
```
### 6. Добавил необходимый код в класс контроллера, чтобы результаты запросов заносились в базу данных  
```java
package ru.neoflex.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.database.resultTable;
import ru.neoflex.practice.repository.resultRep;

import java.util.List;

@RestController
public class CalcController {

    @Autowired
    private resultRep resultRepository;

    @GetMapping("/plus/{a}/{b}")
    public Integer Sum (@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        int result = a+b;
        resultRepository.save(new resultTable(a,"+",b,result));
        return result;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer Diff(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        int result = a-b;
        resultRepository.save(new resultTable(a,"-",b,result));
        return result;
    }

    @GetMapping("/allResults")
    public List<resultTable> getAllResults() {
        return resultRepository.findAll();
    }

    @GetMapping("/allResultsOnly")
    public List<Integer> getAllResultsOnly() {
        return resultRepository.findAllResultsOnly();
    }
}
```
### 6. После выполнения GET запросов на сумму и разность через консоль базы данных получил следующую таблицу  
![Таблица базы данных](https://github.com/MatveyBashirov/Calculator/blob/main/images/database.png?raw=true, "Таблица базы данных")  

