public class StringCaseChanger implements StringTransformer{

    public StringCaseChanger(){}
    @Override
    public void execute(StringDrink drink) {
        String s =  new String();
        for (Integer i = 0; i < drink.getText().length(); i++){
            Character ch = drink.getText().charAt(i);
            if(Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            else {
                ch = Character.toLowerCase(ch);
            }
            s += ch.toString();
        }
        drink.setText(s);
    }
}
