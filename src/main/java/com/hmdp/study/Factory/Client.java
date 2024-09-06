package com.hmdp.study.Factory;

/**
 * @author admin
 */
public class Client {
    public static void main(String[] args) {

        Operation addition = OperationFactory.getOperation("Addition");
        System.out.println(addition.execute(1,3));

    }
}
