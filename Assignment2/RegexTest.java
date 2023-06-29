import java.util.regex.*;
    
public class RegexTest {
        //define the rules for cookies, those are the patterns tobe inputed into the test function
        public static String set_cookie_header = "Set-Cookie: ";
        public static String cookie_pair = "[a-zA-Z0-9]+=([^;]+)?";
        public static String set_cookie = set_cookie_header + cookie_pair;
        
        public static String weekday = "[Mon|Tue|Wed|Thu|Fri|Sat|Sun]+, ";
        public static String date = "[0-9]+ [Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec]+ [0-9]+ ";
        public static String time = "[0-2][0-9]:[0-5][0-9]:[0-5][0-9] GMT";
        public static String expires ="Expires="+weekday+date+time;

        public static String max_age = "Max-Age=[1-9][0-9]+";

        public static String label = "(\\.[a-zA-Z]|([a-zA-Z0-9\\-][a-zA-Z0-9]))";
        public static String domain = "Domain=("+label+")*";

        public static String path = "Path=[^;]+";
        public static String httponly = "HttpOnly";
 
    /**
     * Verify a cookie and return the verification result
     * @param cookie  The cookie string
     * @return legal  True for a legal cookie; false for an illegal one
     */
    public static boolean verifyCookie(String cookie) {
        boolean legal = false;

        String[] split = cookie.split("; ");

        /* iterate all the string to check vaildation */
        for (String i : split)
        {
            if (i==(split[0]))
            {
                Pattern p = Pattern.compile(set_cookie);
                Matcher m = p.matcher(i);
                legal =  m.matches();
            }
            if(i.contains("Expire"))
            {
                Pattern p = Pattern.compile(expires);
                Matcher m = p.matcher(i);
                legal =  m.matches();
            }
            else if(i.contains("Max-Age"))
            {
                Pattern p = Pattern.compile(max_age);
                Matcher m = p.matcher(i);
                legal =  m.matches();
            }
            else if(i.contains("Domain"))
            {
                Pattern p = Pattern.compile(domain);
                Matcher m = p.matcher(i);
                legal =  m.matches();
            }
            else if(i.contains("Path"))
            {
                Pattern p = Pattern.compile(path);
                Matcher m = p.matcher(i);
                legal =  m.matches();
            }
            else if(i.contains("only"))
            {
                Pattern p = Pattern.compile(httponly);
                Matcher m = p.matcher(i);
                legal =  m.matches();
            }
        }

        return legal;
    }

    /**
     * Main entry
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String [] cookies = {
            // Legal cookies:
            "Set-Cookie: ns1=\"alss/0.foobar^\"",                                           // 01 name=value
            "Set-Cookie: ns1=",                                                             // 02 empty value
            "Set-Cookie: ns1=\"alss/0.foobar^\"; Expires=Tue, 18 Nov 2008 16:35:39 GMT",    // 03 Expires=time_stamp
            "Set-Cookie: ns1=; Domain=",                                                    // 04 empty domain
            "Set-Cookie: ns1=; Domain=.srv.a.com-0",                                        // 05 Domain=host_name
            "Set-Cookie: lu=Rg3v; Expires=Tue, 18 Nov 2008 16:35:39 GMT; Path=/; Domain=.example.com; HttpOnly", // 06
            // Illegal cookies:
            "Set-Cookie:",                                              // 07 empty cookie-pair
            "Set-Cookie: sd",                                           // 08 illegal cookie-pair: no "="
            "Set-Cookie: =alss/0.foobar^",                              // 09 illegal cookie-pair: empty name
            "Set-Cookie: ns@1=alss/0.foobar^",                          // 10 illegal cookie-pair: illegal name
            "Set-Cookie: ns1=alss/0.foobar^;",                          // 11 trailing ";"
            "Set-Cookie: ns1=; Expires=Tue 18 Nov 2008 16:35:39 GMT",   // 12 illegal Expires value
            "Set-Cookie: ns1=alss/0.foobar^; Max-Age=01",               // 13 illegal Max-Age: starting 0
            "Set-Cookie: ns1=alss/0.foobar^; Domain=.0com",             // 14 illegal Domain: starting 0
            "Set-Cookie: ns1=alss/0.foobar^; Domain=.com-",             // 15 illegal Domain: trailing non-letter-digit
            "Set-Cookie: ns1=alss/0.foobar^; Path=",                    // 16 illegal Path: empty
            "Set-Cookie: ns1=alss/0.foobar^; httponly",                 // 17 lower case
        };

        for (int i = 0; i < cookies.length; i++)
            System.out.println(String.format("Cookie %2d: %s", i+1, verifyCookie(cookies[i]) ? "Legal" : "Illegal"));
    }

}
