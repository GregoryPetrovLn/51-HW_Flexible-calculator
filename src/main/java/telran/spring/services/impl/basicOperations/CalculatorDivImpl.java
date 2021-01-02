package telran.spring.services.impl.basicOperations;

import org.springframework.stereotype.Service;
import telran.spring.services.interfaces.Calculator;

import static telran.spring.api.ApiConstants.*;


@Service(DIV)
public class CalculatorDivImpl implements Calculator {
    @Override
    public int calculate(int op1, int op2, String operation) {
        if (!operation.equals(DIV)) {
            throw new IllegalStateException("CalculatorAddImpl implies method only operation DIV");
        }
        if(op2 == 0){
            throw new IllegalArgumentException("op2 can't be null");
        }

        return op1 / op2;
    }
}
