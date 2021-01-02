package telran.spring.services.impl.otherOperations;

import org.springframework.stereotype.Service;
import telran.spring.services.interfaces.Calculator;

import static telran.spring.api.ApiConstants.*;

@Service(PER)
public class CalculatorPercentImpl implements Calculator {
    @Override
    public int calculate(int op1, int op2, String operation) {
        if (!operation.equals(PER)) {
            throw new IllegalStateException("CalculatorAddImpl implies method only operation PER");
        }

        return op1 * 100 / op2;
    }
}
