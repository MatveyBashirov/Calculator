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
