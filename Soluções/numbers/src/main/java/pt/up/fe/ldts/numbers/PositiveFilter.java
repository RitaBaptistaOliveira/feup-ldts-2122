package pt.up.fe.ldts.numbers;

public class PositiveFilter implements GenericListFilter{
    @Override
    public boolean accept(Integer number) {
        return number > 0;
    }
}
