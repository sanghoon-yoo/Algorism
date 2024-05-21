package reflection.test;

import reflection.ReflectionFactory;
import reflection.objects.Product;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Objects;

public class sample implements ReflectionFactory {
    public static void main(String[] args) {
        Product product = new sample().findProduct("reflection/objects");
    }
    public Product findProduct(String path) {
        return (Product) getAllImpl(path, Product.class)
                .stream()
                .map(clazz -> {
                    Object instance = null;
                    Method method = null;
                    try {
                        Constructor<?> constructor = clazz.getConstructor();
                        instance = constructor.newInstance();
                        method = clazz.getMethod("getInstance");
                        return (Product) method.invoke(instance);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .peek(product -> System.out.println(product.getName()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
