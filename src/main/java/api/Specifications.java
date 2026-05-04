package api;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification baseRequestSpec(String url)
    {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                //.log(LogDetail.BODY)
                .build();
    }

    public static ResponseSpecification baseResponseSpec()
    {
        return new ResponseSpecBuilder()
                //.log(LogDetail.BODY)
                .build();
    }
}
