package telran.spring.services.impl.basicOperations;

import org.springframework.stereotype.Service;
import telran.spring.services.interfaces.Calculator;

import static telran.spring.api.ApiConstants.*;

@Service(MUL)
public class CalculatorMulImpl implements Calculator {
    @Override
    public int calculate(int op1, int op2, String operation) {
        if (!operation.equals(MUL)) {
            throw new IllegalStateException("CalculatorAddImpl implies method only operation MUL");
        }

        return op1 * op2;
    }
}
