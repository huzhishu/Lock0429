package com.hmdp.study.Factory;

/**
 * @author admin
 */
public class OperationFactory {

    // 工厂方法，使用反射动态创建操作类实例
    public static Operation getOperation(String operationType) {
        Operation operation = null;
        try {
            Class<?> clazz = Class.forName("com.hmdp.study.Factory." + operationType + "Operation");
            operation = (Operation)clazz.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return operation;
    }


}
