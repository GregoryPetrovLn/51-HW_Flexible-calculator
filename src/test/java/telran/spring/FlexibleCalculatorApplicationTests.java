package telran.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import telran.spring.api.dto.CalculateData;
import telran.spring.services.interfaces.Calculator;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static telran.spring.api.ApiConstants.*;


@AutoConfigureMockMvc
@SpringBootTest
class FlexibleCalculatorApplicationTests {
    @Autowired
    Map<String, Calculator> calculators;
    @Autowired
    MockMvc mock;

    @Test
    void contextLoads() {
        assertNotNull(calculators);
    }

    //======================================================
    //======================================================
    //======================================================
    //======================================================

    @Nested
    class NormalTests{
        HttpStatus httpStatus = HttpStatus.OK;
        @Test
        void add() throws Exception {
            testHttpStatusAndValue(10, 5, "15", ADD, httpStatus);
        }

        @Test
        void sub() throws Exception {
            testHttpStatusAndValue(10, 5, "5", SUB, httpStatus);
        }

        @Test
        void mul() throws Exception {
            testHttpStatusAndValue(10, 10, "100", MUL, httpStatus);
        }

        @Test
        void div() throws Exception {
            testHttpStatusAndValue(10, 5, "2", DIV, httpStatus);
        }

        @Test
        void pow() throws Exception {
            testHttpStatusAndValue(5, 2, "25", POW, httpStatus);
        }

        @Test
        void per() throws Exception {
            testHttpStatusAndValue(20, 100, "20", PER, httpStatus);
        }
    }

    @Nested
    class MissedFirstParameter{
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        @Test
        void add() throws Exception {
            testHttpStatusAndValue(null, 5, "", ADD, httpStatus);
        }

        @Test
        void sub() throws Exception {
            testHttpStatusAndValue(null, 5, "", SUB, httpStatus);
        }

        @Test
        void mul() throws Exception {
            testHttpStatusAndValue(null, 10, "", MUL, httpStatus);
        }

        @Test
        void div() throws Exception {
            testHttpStatusAndValue(null, 5, "", DIV, httpStatus);
        }

        @Test
        void pow() throws Exception {
            testHttpStatusAndValue(null, 2, "", POW, httpStatus);
        }

        @Test
        void per() throws Exception {
            testHttpStatusAndValue(null, 100, "", PER, httpStatus);
        }

    }

    @Nested
    class MissedSecondParameter{
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        @Test
        void add() throws Exception {
            testHttpStatusAndValue(10, null, "", ADD, httpStatus);
        }

        @Test
        void sub() throws Exception {
            testHttpStatusAndValue(10, null, "", SUB, httpStatus);
        }

        @Test
        void mul() throws Exception {
            testHttpStatusAndValue(10, null, "", MUL, httpStatus);
        }

        @Test
        void div() throws Exception {
            testHttpStatusAndValue(10, null, "", DIV, httpStatus);
        }

        @Test
        void pow() throws Exception {
            testHttpStatusAndValue(10, null, "", POW, httpStatus);
        }

        @Test
        void per() throws Exception {
            testHttpStatusAndValue(10, null, "", PER, httpStatus);
        }

    }





    //======================================================
    //======================================================
    //======================================================
    //======================================================

    public void testHttpStatusAndValue(Integer op1, Integer op2, String expectedResponse, String operation,HttpStatus httpStatus) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CalculateData calculateData = new CalculateData(op1, op2, operation);
        String json = mapper.writeValueAsString(calculateData);

        MockHttpServletResponse response = mock.perform(
                post(CALCULATOR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn().getResponse();

        HttpStatus status = HttpStatus.valueOf(response.getStatus());

        //=======Asserts=========
        assertEquals(httpStatus, status);
        if(op1 != null && op2!= null) {
            assertEquals(expectedResponse, response.getContentAsString());
        }
    }


}
