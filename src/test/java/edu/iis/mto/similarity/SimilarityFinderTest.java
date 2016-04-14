package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author Mateusz Wieczorek
 */
public class SimilarityFinderTest {

    private SequenceSearcher searcher;

    private class MySearchResult implements SearchResult {

        public void setPosition(int position) {
            this.position = position;
        }

        private int position;

        public boolean isFound() {
            return position != -1;
        }

        public int getPosition() {
            return position;
        }
    }

    private class MySequenceSearcher implements SequenceSearcher {

        private MySearchResult searchResult;

        public MySequenceSearcher() {
            searchResult = new MySearchResult();
            searchResult.setPosition(-1);
        }

        public SearchResult search(int i, int[] ints) {
            for (int j = 0; j < ints.length; ++j) {
                if (ints[j] == i) {
                    searchResult.setPosition(j);
                    break;
                }
            }
            return searchResult;
        }
    }

    @Before
    public void setUp() {
        searcher = new MySequenceSearcher();
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


    @Test
    public void differenceSequence2Test() {
        SimilarityFinder finder = new SimilarityFinder(searcher);
        int[] seq1 = {23, 33, 43};
        int[] seq2 = {1, 222, 33};
        assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(1.0 / 5.0));
    }

    @Test
    public void differenceSequence3Test() {
        SimilarityFinder finder = new SimilarityFinder(searcher);
        int[] seq1 = {23, 33, 44, 55};
        int[] seq2 = {55, 44, 33, 23};
        assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(1d));
    }
}