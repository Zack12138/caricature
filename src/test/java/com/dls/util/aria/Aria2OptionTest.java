package com.dls.util.aria;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Aria2Option Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>8ÔÂ 1, 2022</pre>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Aria2OptionTest {

    private Aria2Option aria2Option;

    @Before
    public void before() throws Exception {
        aria2Option = new Aria2Option();
        aria2Option.setId("123456");
        aria2Option.setMethod(Aria2Json.METHOD_TELL_ACTIVE);
        aria2Option.setJsonrpc("2.0");
        aria2Option.addParam("out","d:/download");
        aria2Option.addParam("url","www.baidu.com");

    }

    @After
    public void after() throws Exception {
        aria2Option = null;
    }

    /**
     * Method: addParam(String key, String value)
     */
    @Test
    public void testAddParam() throws Exception {

    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        String str = aria2Option.toString();
        assert str != null;
        System.out.println(str);

    }
}
