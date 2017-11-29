import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;


public class CodeGatherer {

    public String getCode(String str)
    {
        return refractorCode(getGovnocodePageSourceCode(str));
    }


    public String getGovnocodePageSourceCode(String postCode)
    {
        String outString;

        try {
            URL url = new URL("http://www.govnokod.ru/" + postCode);

            try {
                LineNumberReader reader = new LineNumberReader
                        (new InputStreamReader(url.openStream()));
                StringBuilder stringBuilder = new StringBuilder(reader.readLine());
                stringBuilder.append("\n");
                String sting = reader.readLine();
                while (sting  != null) {
                    stringBuilder.append(sting);
                    stringBuilder.append("\n");
                    sting = reader.readLine();

                }
                outString = stringBuilder.toString();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                outString = "IOException";
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            outString = "MalformedURLException";
        }
        return outString;
    }

    public String refractorCode(String inString)
    {
        String label="";
        int ticks=0;

        for (int i = 5000; i < inString.length()-6; i++) {
            if(inString.substring(i, i+5).equals("<code"))
            {
                inString = inString.substring(i+5);
                break;
            }
        }
        for(int i = 0; i < inString.length()-8; i++)
        {
            if(inString.substring(i, i+7).equals("</code>"))
            {
                inString = inString.substring(0,i);
            }
        }

        inString = inString.replaceAll("&lt;", "<");
        inString = inString.replaceAll("&gt;", ">");
        inString = inString.replaceAll("&quot;", "\"" );
        inString = inString.replaceAll("&#039;", "'");

        inString = inString.trim();

        if(inString.indexOf('>') == 8)
        {
            inString = "Каша" + inString;
        }
        else
        {
            for (int i = 0; i < inString.length(); i++)
            {
                if(inString.charAt(i) == '\"')
                {
                    if(ticks==1)
                    {
                        label = inString.substring(7, i);
                        inString = inString.substring(i+2);
                        break;
                    }
                    else
                        ticks++;
                }
            }


        }



        return label + "\n\n" + inString;
    }


}