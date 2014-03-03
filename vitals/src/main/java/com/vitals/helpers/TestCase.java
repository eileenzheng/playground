package com.vitals.helpers;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Notation to flag a @Test as a TestRail Test Case
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCase {
    long[] id();
}
