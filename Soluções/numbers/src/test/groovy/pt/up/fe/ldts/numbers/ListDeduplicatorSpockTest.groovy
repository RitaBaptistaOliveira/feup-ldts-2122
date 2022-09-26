package pt.up.fe.ldts.numbers

import spock.lang.Specification

class ListDeduplicatorSpockTest extends Specification {
    private def list
    private def expected

    def setup() {
        list = Arrays.asList(1,2,4,2,5)
        expected = Arrays.asList(1,2,4,5)
    }

    def 'Testing deduplicate'() {
        given:
            GenericListSorter sorter = Mock(GenericListSorter.class)
            def deduplicator = new ListDeduplicator(sorter)

            sorter.sort(_) >> Arrays.asList(1, 2, 2, 4, 5)

        when:
            def distinct = deduplicator.deduplicate(list)

        then:
            expected == distinct;
    }

    def 'Testing deduplicate bug 8726'() {
        given:
            def sorter = Mock(GenericListSorter.class)
            def deduplicator = new ListDeduplicator(sorter)

            sorter.sort(_) >> Arrays.asList(1, 2, 2, 4)

        when:
            def distinct = deduplicator.deduplicate(Arrays.asList(1, 2, 4, 2))

        then:
            Arrays.asList(1, 2, 4) == distinct
    }
}
