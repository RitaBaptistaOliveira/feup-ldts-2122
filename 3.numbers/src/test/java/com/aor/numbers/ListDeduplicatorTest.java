package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListDeduplicatorTest {

    private List<Integer> list;

    @BeforeEach
    public void getTestList(){
        list = Arrays.asList(1,2,4,2);
    }

    @Test
    public void deduplicate() {
        List<Integer> expected = Arrays.asList(1,2,4);
        ListDeduplicator deduplicator = new ListDeduplicator(new GenericListSorter() {
            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1,2,2,4);
            }
        });
        List<Integer> distinct = deduplicator.deduplicate(list);

        Assertions.assertEquals(expected, distinct);
    }
}
