package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Mateusz on 2016-04-07.
 */
public class SimilarityFinderTest {

    private SequenceSearcher searcher;

    @Before
    public void setUp() {
        searcher = null;
    }

    @Test
    public void sequenceLenghtTest() {
        SimilarityFinder finder = new SimilarityFinder(searcher);
        int[] seq1 = {};
        int[] seq2 = {};
        assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(1.0d));
    }

    @Test
    public void differenceSequenceTest() {
        SimilarityFinder finder = new SimilarityFinder(searcher);
        int[] seq1 = {23, 33, 43, 54};
        int[] seq2 = {1, 2, 3, 4};
        assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(0d));
    }

}