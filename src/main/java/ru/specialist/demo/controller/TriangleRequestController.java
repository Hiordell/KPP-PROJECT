package ru.specialist.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.specialist.demo.counter.RequestCounter;

@RestController
public class TriangleRequestController {
    Logger logger = LogManager.getLogger(TriangleRequestController.class);

    @GetMapping(value = "/counter")
    public String getCounter() {
        logger.info("Visited RequestCounterController");
        return RequestCounter.getCounter() + " запросов было выполнено";
    }
}

