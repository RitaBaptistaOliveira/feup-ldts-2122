public class StringReplacer implements StringTransformer{

    private char oldCh;
    private char newCh;
    public StringReplacer(char oldCh, char newCh) {
        this.oldCh = oldCh;
        this.newCh = newCh;
    }

    @Override
    public void execute(StringDrink drink) {
        drink.setText(drink.getText().replace(oldCh,newCh));
    }
}
