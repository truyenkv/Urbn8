package urbn8.apiengine;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import urbn8.util.ReadProperties;

import java.util.HashMap;

public class BaseAPI {
    private RequestSpecification requestSpecification;
    private ReadProperties readPro = ReadProperties.getInstance();

    protected RequestSpecification requestSpec(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(readPro.readPro("endpoint"));
        return requestSpecification;
    }
}
