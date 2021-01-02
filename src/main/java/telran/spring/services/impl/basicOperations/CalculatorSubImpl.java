package telran.spring.services.impl.basicOperations;

import org.springframework.stereotype.Service;
import telran.spring.services.interfaces.Calculator;

import static telran.spring.api.ApiConstants.SUB;

@Service(SUB)
public class CalculatorSubImpl implements Calculator {
    @Override
    public int calculate(int op1 , int op2, String operation) {
        if (!operation.equals(SUB)) {
            throw new IllegalStateException("CalculatorAddImpl implies method only operation SUB");
        }

        return op1 - op2;
    }
}
