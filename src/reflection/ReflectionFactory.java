package reflection;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public interface ReflectionFactory {
    default List<Class<?>> getAllImpl(String PackageName, Class<?> interfaceClass) {
        try {
            ArrayList<Class<?>> classes = new ArrayList<>();

            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            URL resource = contextClassLoader.getResource(PackageName);

            for (String file : Objects.requireNonNull(new File(resource.getFile()).list())) {
                if (!file.endsWith(".class")) {
                    continue;
                }
                String className = PackageName.replace("/", ".") + "." + file.substring(0, file.length() - 6);
                Class<?> clazz = Class.forName(className);
                if (interfaceClass.isAssignableFrom(clazz) && !clazz.isInterface() && !clazz.isEnum()) {
                    classes.add(clazz);
                }
            }
            return classes;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
