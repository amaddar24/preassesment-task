import java.io.IOException;
import requests.GetRequest;

public class Main {

    public static void main(String[] args) throws IOException {
        GetRequest request = new GetRequest(); // get request for
        request.display();

        /**
         * get request for crime data at specific address
         * postcode must be in captial letters with no spaces
         * dates written in YYYY-MM format
         * no crime data before 2018-01 or after 2021-05
         */
        GetRequest request2 = new GetRequest("2020-03","TF33AY");
        request2.display();
    }
}
