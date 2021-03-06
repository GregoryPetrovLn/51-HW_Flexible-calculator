package telran.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import telran.spring.api.dto.CalculateData;
import telran.spring.services.interfaces.Calculator;
import javax.annotation.PostConstruct;
import static telran.spring.api.ApiConstants.*;
import java.util.Map;

@RestController
public class FlexibleCalculatorControllers {
    @Autowired
    Map<String, Calculator> calculators;

    @PostMapping(CALCULATOR)
    ResponseEntity<?> calculate(@RequestBody CalculateData calculateData) {
        Calculator calculator = calculators.get(calculateData.operation);
        if (calculator == null) {
            return ResponseEntity.badRequest().body("unknown operation");
        }

        try {
            return ResponseEntity.ok(calculator.calculate(calculateData.op1, calculateData.op2, calculateData.operation));
        }catch (IllegalStateException e){
            return ResponseEntity.status(501).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostConstruct
    void displaySupportedOperations(){
        System.out.println("Supported operations : " + calculators.keySet());
    }


}
