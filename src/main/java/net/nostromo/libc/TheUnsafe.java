package net.nostromo.libc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TheUnsafe {

    public static final Unsafe UNSAFE;

    static {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
