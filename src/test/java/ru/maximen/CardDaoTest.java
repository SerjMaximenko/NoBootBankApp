package ru.maximen;

import org.junit.Assert;
import org.junit.Test;

public class CardDaoTest {

    @Test
    public void addCardTest(){

        int x = 2;
        int y = 23;

        Assert.assertEquals(46,x * y);
        Assert.assertEquals(25,x + y);
    }
}
