package telran.spring.services.impl.otherOperations;

import org.springframework.stereotype.Service;
import telran.spring.services.interfaces.Calculator;

import static telran.spring.api.ApiConstants.*;

@Service(POW)
public class CalculatorPowImpl implements Calculator {
    @Override
    public int calculate(int op1, int op2, String operation) {
        if (!operation.equals(POW)) {
            throw new IllegalStateException("CalculatorAddImpl implies method only operation POW");
        }

        return (int) Math.pow(op1, op2);
    }
}
