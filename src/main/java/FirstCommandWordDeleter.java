public class FirstCommandWordDeleter {

    public String deleteFirstWord(String string)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        System.out.println(string);
        while (stringBuilder.toString().charAt(0) != ' ')
        {
            stringBuilder.deleteCharAt(0);
            System.out.println(stringBuilder.toString());
        }
        stringBuilder.deleteCharAt(0);
        return stringBuilder.toString();
    }
}
