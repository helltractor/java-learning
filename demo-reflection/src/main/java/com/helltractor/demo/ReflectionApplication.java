package com.helltractor.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.helltractor.demo.annotation.Printable;
import com.helltractor.demo.service.Container;

/**
 * @Author: helltractor
 * @Date: 2024/5/3 17:28
 */
public class ReflectionApplication {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        Address address = new Address("123 Main Street", "12345");
//        Customer customer = new Customer("John Doe", "110@120");
//        Order order = new Order(customer, address);
//        order.getCustomer().printName();
//        order.getAddress().printPostCode();

        Container container = new Container();
        container.init();
        String className = "com.helltractor.demo.entity.Order";
        Class<?> clazz = Class.forName(className);
        Object obj = container.createInstance(clazz);

        String name = "address";
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);

        Object fieldObj = field.get(obj);
        Method[] methods = fieldObj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Printable.class) != null) {
                method.invoke(fieldObj);
            }
        }
    }

}
