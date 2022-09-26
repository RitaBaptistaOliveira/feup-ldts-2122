public class StringInverter implements StringTransformer{
    @Override
    public void execute(StringDrink drink) {
        StringBuilder newS = new StringBuilder(drink.getText());
        newS.reverse();
        drink.setText(newS.toString());
    }
}
