package ru.specialist.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.specialist.demo.entity.ExceptionInfo;
import ru.specialist.demo.entity.ResultDto;
import ru.specialist.demo.entity.TriangleInfo;
import ru.specialist.demo.entity.TriangleSidesInfo;
import ru.specialist.demo.exceptions.TriangleError;
import ru.specialist.demo.service.TriangleLogic;
import ru.specialist.demo.service.TriangleService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Validated
@RestController
public class TriangleController {
    private static final Logger logger = LogManager.getLogger(TriangleController.class);
    private final TriangleService triangleService;

    @Autowired
    public TriangleController(TriangleService triangleService) {
        this.triangleService = triangleService;
    }

    @GetMapping("/triangle")
    public TriangleInfo calculateSquareAndPerimeter(@RequestParam(value = "a", required = true) @Min(value = 0) int a,
                                                    @RequestParam(value = "b", required = true) @Min(value = 0) int b,
                                                    @RequestParam(value = "c", required = true) @Min(value = 0) int c) {
        if (a + b < c || a + c < b || b + c < a) {
            throw new TriangleError();
        }
        TriangleSidesInfo triangleSidesInfo = new TriangleSidesInfo(a, b, c);
        return triangleService.saveTriangleCharacteristic(triangleSidesInfo);
    }

    @GetMapping("/cache")
    public Map<TriangleSidesInfo, TriangleInfo> getCache() {
        logger.info("Successfully got triangles info from Map");
        return triangleService.getCache();
    }

    @PostMapping("/triangle")
    public ResponseEntity<?> calculateBulkParams(@Valid @RequestBody List<TriangleSidesInfo> bodyList) {
        if (bodyList.isEmpty()) {
            return new ResponseEntity<>(new ResultDto(null), HttpStatus.OK);
        }
        List<TriangleInfo> resultList = new LinkedList<>();
        bodyList.forEach((currentElement) -> {
            try {
                TriangleInfo info = triangleService.saveTriangleCharacteristic(currentElement);
                info.setSumOfSides(TriangleLogic.calculateSumOfSides(currentElement));
                info.setMaxSide(TriangleLogic.findMaxOfSides(currentElement));
                info.setMinSide(TriangleLogic.findMinOfSides(currentElement));
                resultList.add(triangleService.saveTriangleCharacteristic(currentElement));
            } catch (TriangleError e) {
                logger.error("Error in postMapping");
            }
        });

        logger.info("Successfully postMapping");

        ResultDto dto = new ResultDto(resultList);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        ExceptionInfo error = new ExceptionInfo(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.info("error 400");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception e) {
        ExceptionInfo error = new ExceptionInfo(Arrays.toString(e.getStackTrace()), 500);
        logger.info("error 500");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TriangleError.class)
    public ResponseEntity<?> handleTriangleDoesNotExistException(TriangleError e) {
        ExceptionInfo error = new ExceptionInfo(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        logger.info("TriangleError");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
