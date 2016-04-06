package edu.iis.mto.bsearch;


import edu.iis.mto.bsearch.exception.SequenceNotSortedException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * @Author Mateusz Wieczorek, 06.04.16.
 */
public class BinarySearchTest {

    @Test
    public void checkWhetherKeyIsInASequence() throws SequenceNotSortedException {
        int[] sequence = {13};
        int key = 13;
        SearchResult searchResult = BinarySearch.search(key, sequence);
        int position = searchResult.getPosition();
        assertThat(searchResult.isFound(), is(true));
        assertThat(position, is(not(-1)));
        assertThat(sequence[position] == key, is(true));
    }

    @Test
    public void checkWhetherKeyIsNotInASequence() throws SequenceNotSortedException {
        int[] sequence = {3};
        int key = 13;
        SearchResult searchResult = BinarySearch.search(key, sequence);
        int position = searchResult.getPosition();
        assertThat(searchResult.isFound(), is(false));
        assertThat(position, is(-1));

        int[] newSequence = {1, 23, 44, 55};
        searchResult = BinarySearch.search(key, newSequence);
        position = searchResult.getPosition();
        assertThat(searchResult.isFound(), is(false));
        assertThat(position, is(-1));
    }

    @Test
    public void checkWhetherKeyIsOnFirstPositionInASequence() throws SequenceNotSortedException {
        int[] sequence = {3, 5, 17, 33, 99};
        int key = 3;
        SearchResult searchResult = BinarySearch.search(key, sequence);
        assertThat(searchResult.isFound(), is(true));
        assertThat(sequence[0] == key, is(true));
    }

    @Test
    public void checkWhetherKeyIsOnTheLastPositionInASequence() throws SequenceNotSortedException {
        int[] sequence = {3, 5, 17, 33, 99};
        int key = 99;
        SearchResult searchResult = BinarySearch.search(key, sequence);
        assertThat(searchResult.isFound(), is(true));
        assertThat(sequence[sequence.length-1] == key, is(true));
    }

    @Test
    public void checkWhetherKeyIsOnCenterPositionInASequence() throws SequenceNotSortedException {
        int[] sequence = {3, 5, 17, 55, 99};
        int key = 17;
        SearchResult searchResult = BinarySearch.search(key, sequence);
        assertThat(searchResult.isFound(), is(true));
        if (sequence.length % 2 == 1) {
            assertThat(sequence[sequence.length / 2] == key, is(true));
        } else {
            assertThat(sequence[sequence.length / 2] == key || sequence[(sequence.length / 2) - 1] == key, is(true));
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowExceptionIfSequenceLenghtIsZero() throws SequenceNotSortedException {
        int[] sequence = {};
        int key = 17;
        BinarySearch.search(key, sequence);
    }

    @Test(expected=SequenceNotSortedException.class)
    public void checkWhetherASequenceIsNotSorted() throws SequenceNotSortedException {
        int[] sequence = {3, 5, 103, 55, 99};
        int key = 55;
        BinarySearch.search(key, sequence);
    }
}