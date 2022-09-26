package pt.up.fe.ldts.numbers


import spock.lang.Specification

class DivisibleByFilterSpockTest extends Specification {

    def 'accept divide by 2'() {
        given:
            def filter = new DivisibleByFilter(2)
        when:
            def divisible = Arrays.asList(0, 2, 4, 8, 240, 1000, 10000)
            def notDivisible = Arrays.asList(1, 3, 5, 15, 351, 10001)

        then:
            divisible.every {it -> filter.accept(it) }
            notDivisible.every {it -> !filter.accept(it) }
    }

    def 'accept divide by 3'() {
        given:
            def filter = new DivisibleByFilter(3)
        when:
            def divisible = Arrays.asList(0, 3, 6, 9, 33, 333, 666)
            def notDivisible = Arrays.asList(1, 2, 4, 5, 100, 1000, 10000)

        then:
            divisible.every {it -> filter.accept(it) }
            notDivisible.every {it -> !filter.accept(it) }
    }
}
