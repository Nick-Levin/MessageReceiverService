package org.messageReceiverService.validator;

import org.messageReceiverService.parser.BigDecimalParser;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ValidationMap {

    private final Map<String, String> map;

    /**
     * The ValidationMap is built in runtime through reflection by scanning the parser package
     * This is built in order to allow easy scalability, if we want to add new parser it just
     * needs to inherit the <code>BigDecimalParser</code> interface and be under the same package.
     * The map is used by the <code>InputRestValidator</code> to validate the input using the
     * regex located in each parser class and returns the parser full qualification name.
     * This architecture allows the rest controller that calls the validate method to know
     * which parser to use in order to parse the given input
     */
    public ValidationMap() {
        map = new HashMap<>();

        Reflections reflections = new Reflections(BigDecimalParser.class.getPackageName());
        Set<Class<? extends BigDecimalParser>> set = reflections.getSubTypesOf(BigDecimalParser.class);

        set.forEach(className -> {
            try {
                final Field field = className.getDeclaredField("REGEX_PATTERN");
                map.put(className.getCanonicalName(), field.get(null).toString());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    public String get(String key) {
        return map.get(key);
    }

    public Set<String> getKeySet() {
        return map.keySet();
    }

}
