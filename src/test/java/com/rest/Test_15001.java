package com.rest;

import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class Test_15001 implements ITest {

    private ThreadLocal<String> testName = new ThreadLocal<>();
    private int i = 1;

    @BeforeMethod
    public void BeforeMethod(Method method){
        testName.set(method.getName() + "_" + i);
        i++;
    }

    @Override
    public String getTestName() {
        return testName.get();
    }

    @Test(invocationCount = 15001, threadPoolSize = 15001)
    public void testCase(){

    }
}
