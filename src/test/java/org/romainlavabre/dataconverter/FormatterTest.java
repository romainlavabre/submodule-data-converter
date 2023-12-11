package org.romainlavabre.dataconverter;

import org.junit.Assert;
import org.junit.Test;

public class FormatterTest {

    @Test
    public void test1() {
        Assert.assertEquals( "+33607103880", Formatter.phone( "+33 6 07 10 38 80" ) );
    }


    @Test
    public void test2() {
        Assert.assertEquals( "+33641825412", Formatter.phone( "+336 418   25412" ) );
    }
}
