package com.hmdp.study.Factory;

/**
 * @author admin
 */
public class MultiplicationOperation implements Operation {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}
