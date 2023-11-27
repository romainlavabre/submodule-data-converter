package org.romainlavabre.dataconverter;


import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public class PriceTest {

    @Test
    public void test_to_long() {
        for ( Map.Entry< Double, Long > entry : providerToLong().entrySet() ) {
            Assert.assertEquals( entry.getValue(), Price.toLong( entry.getKey() ) );
        }
    }


    @Test
    public void test_round_micro_cents() {
        for ( Map.Entry< Double, Long > entry : providerRoundMicro().entrySet() ) {
            Assert.assertEquals( ( Object ) entry.getValue(), Price.roundMicroCents( entry.getKey() ) );
        }
    }


    @Test
    public void test_to_taxed() {
        for ( Map.Entry< Long, Double > entry : providerToTaxed1().entrySet() ) {
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toTaxed( entry.getKey(), ( short ) 10 ) );
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toTaxed( entry.getKey(), 1.1d ) );
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toTaxed( entry.getKey(), 1.1f ) );
        }

        for ( Map.Entry< Double, Double > entry : providerToTaxed2().entrySet() ) {
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toTaxed( entry.getKey(), ( short ) 10 ) );
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toTaxed( entry.getKey(), 1.1d ) );
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toTaxed( entry.getKey(), 1.1f ) );
        }
    }


    @Test
    public void test_sequence_1() {
        for ( Map.Entry< Long, Long > entry : providerSequence1().entrySet() ) {
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toLong( Price.toTaxed( Price.toLong( Price.toDouble( entry.getKey() ) * 1d ), ( short ) 10 ) ) );
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toLong( Price.toTaxed( Price.toLong( Price.toDouble( entry.getKey() ) * 1d ), 1.1d ) ) );
            Assert.assertEquals( ( Object ) entry.getValue(), Price.toLong( Price.toTaxed( Price.toLong( Price.toDouble( entry.getKey() ) * 1d ), 1.1f ) ) );
        }
    }


    private Map< Double, Long > providerToLong() {
        return Map.of(
                10.33d, 1033L,
                10d, 1000L,
                333.2345d, 33323L,
                140.9d, 14090L,
                140.92d, 14092L,
                140.99856d, 14100L,
                3353453.99d, 335345399L,
                9999999.99d, 999999999L,
                33534533456.99d, 3353453345699L,
                953.9999999999999, 95400L
        );
    }


    private Map< Double, Long > providerRoundMicro() {
        return Map.of(
                10.33d, 10L,
                10d, 10L,
                333.2345d, 333L,
                140.9d, 141L,
                140.2d, 140L,
                140.99856d, 141L,
                3353453.99d, 3353454L,
                9999999.99d, 10000000L,
                33534533456.99d, 33534533457L,
                953.9999999999999, 954L
        );
    }


    private Map< Long, Double > providerToTaxed1() {
        return Map.of(
                27272L, 299.99d,
                27200L, 299.2d,
                13636L, 150d,
                90909L, 1000d
        );
    }


    private Map< Double, Double > providerToTaxed2() {
        return Map.of(
                272.727272727d, 300d,
                272d, 299.2d,
                136.363636364d, 150d,
                136.36d, 150d,
                909.090909091d, 1000d,
                909.091d, 1000d,
                99d, 108.9d
        );
    }


    private Map< Long, Long > providerSequence1() {
        return Map.of(
                15714L, 17285L,
                15715L, 17287L,
                15716L, 17288L
        );
    }
}
