package Problema_cantece;

public class Vers {
    private String vers;


    public Vers(String vers)
    {
        this.vers = vers;
    }

    public String getvers()
    {
        return vers;
    }

    public int numarcuvinte()
    {
        return vers.split(" ").length;
    }

    public boolean estevocala(char c)
    {
        String vocal ="aeiouAEIOU";
        return vocal.contains(String.valueOf(c));
    }

    public int numarvocale()
    {
        int nrvoc =0;
        for(char c:vers.toCharArray())
        {
            if(estevocala(c))
                nrvoc++;
        }

        return nrvoc;
    }


    public boolean terminatie(String cuv)
    {
        return vers.endsWith(cuv);
    }

}
