package ru.specialist.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.specialist.demo.counter.RequestCounterThread;
import ru.specialist.demo.entity.TriangleInfo;
import ru.specialist.demo.entity.TriangleSidesInfo;

import java.util.Map;

@Service
public class TriangleService {
    @Autowired
    private TriangleCache repository;

    public Map<TriangleSidesInfo, TriangleInfo> getCache() {
        return repository.getTriangleMap();
    }

    public TriangleInfo saveTriangleCharacteristic(TriangleSidesInfo number) {
        new RequestCounterThread(Thread.currentThread().getName()).start();
        TriangleInfo existingTriangleInfo = repository.findBySides(number);
        if (existingTriangleInfo != null) {
            return existingTriangleInfo;
        } else {
            return repository.addTriangleInfo(number, calculateSquareAndPerimeter
                    (number.getA(), number.getB(), number.getC()));
        }
    }

    public TriangleInfo calculateSquareAndPerimeter(int a, int b, int c) {
        TriangleInfo triangleInfo = new TriangleInfo();
        int p = a + b + c;
        triangleInfo.setPerimeter(p);
        float P = p/2f;
        triangleInfo.setSquare((int)Math.sqrt(P*(P - a) * (P - b) * (P - c)));
        return triangleInfo;
    }
}