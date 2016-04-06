package edu.iis.mto.bsearch;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * @Author Mateusz Wieczorek, 06.04.16.
 */
public class BinarySearchTest {

    @Test
    public void checkWhetherKeyIsInASequence() {
        int[] sequence = {13};
        int key = 13;
        SearchResult searchResult = BinarySearch.search(key, sequence);
        int position = searchResult.getPosition();
        assertThat(searchResult.isFound(), is(true));
        assertThat(position, is(not(-1)));
        assertThat(sequence[position] == key, is(true));
    }

    @Test
    public void checkWhetherKeyIsNotInASequence() {
        int[] sequence = {3};
        int key = 13;
        SearchResult searchResult = BinarySearch.search(key, sequence);
        int position = searchResult.getPosition();
        assertThat(searchResult.isFound(), is(false));
        assertThat(position, is(-1));
    }

}