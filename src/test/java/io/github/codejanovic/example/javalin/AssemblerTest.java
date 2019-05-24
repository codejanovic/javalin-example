package io.github.codejanovic.example.javalin;

import org.junit.jupiter.api.Test;
import org.jusecase.UsecaseExecutorTest;


public class AssemblerTest extends UsecaseExecutorTest {

    @Test
    void bootstrap() {
        givenExecutor(Assembler.instance);
    }

    private void givenJvmProperty(String property, String value) {
        System.setProperty(property, value);
    }
}