package com.ugas;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UgasCommandServiceTest {

    @Test
    public void testCommandA() {
        String response = send("KBA?");
        assertThat(response, is("A"));
    }

    @Test
    public void testCommandB() {
        String response = send("KKK");
        assertThat(response, is("B"));
    }

    private String send(final String command) {
        return "A";
    }

}
