package urbn8.apiengine;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import urbn8.util.ReadProperties;

import java.util.HashMap;

public class BaseAPI {
    private RequestSpecification requestSpecification;
    private HashMap<String, Object> headers;
    private ReadProperties readPro = ReadProperties.getInstance();


    public HashMap<String, Object> SetHeaderDefault() {
        headers = new HashMap<String, Object>();
        headers.put("", "");
        headers.put("", "");
        headers.put("", "");
        return headers;
    }

    protected RequestSpecification requestSpec(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(readPro.readPro("endpoint"));
        return requestSpecification;
    }
}
