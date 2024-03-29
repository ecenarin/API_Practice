package httpBin.anything;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01_DeleteAnything extends BaseHttpBin {
    /*
    Curl
curl -X DELETE "http://httpbin.org/anything" -H "accept: application/json"
Request URL
http://httpbin.org/anything
Server response
Code	Details
200
Response body
Download
{
  "args": {},
  "data": "",
  "files": {},
  "form": {},
  "headers": {
    "Accept": "application/json",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Host": "httpbin.org",
    "Origin": "http://httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.47",
    "X-Amzn-Trace-Id": "Root=1-62f2c848-69bfdc053e2c8d8203f44de4"
  },
  "json": null,
  "method": "DELETE",
  "origin": "176.42.164.88",
  "url": "http://httpbin.org/anything"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: http://httpbin.org
 connection: keep-alive
 content-length: 660
 content-type: application/json
 date: Tue, 09 Aug 2022 20:49:12 GMT
 server: gunicorn/19.9.0
     */
    @Test
    public void httpDelete() throws IOException {
        URL url = new URL("http://httpbin.org/anything");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/anything")
                .setAccept("application/json").build();
        Response response=given()
                .spec(req)
                .when().delete();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);
     /*
     {
    "args": {

    },
    "data": "",
    "files": {

    },
    "form": {

    },
    "headers": {
        "Accept": "application/json",
        "Accept-Encoding": "gz1p,deflate",
        "Host": "httpbin.org",
        "User-Agent": "Apache-HttpClient/4.5.3 (Java/18.0.1.1)",
        "X-Amzn-Trace-Id": "Root=1-62f2ca40-7958b8b61df8cad173190dba"
    },
    "json": null,
    "method": "GET",
    "origin": "176.42.164.88",
    "url": "http://httpbin.org/anything"
}
      */
        //expected body
        JSONObject headers=new JSONObject();
        JSONObject expected=new JSONObject();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        expected.put("origin","176.42.164.88");
        expected.put("url", "http://httpbin.org/anything");
        expected.put("headers",headers);
        expected.put("json", "null");
        expected.put("method", "DELETE");
        // save response
        JsonPath actual=response.jsonPath();
        //assertions
        // assertEquals(expected.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(expected.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(expected.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(expected.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        // assertEquals(expected.get("origin"),actual.get("origin"));
        assertEquals(expected.get("url"),actual.get("url"));
        assertEquals(expected.get("method"),actual.get("method"));
        assertEquals(expected.get("json"),actual.get("json"));


    }

    @Test
    public void res() {
        //http://httpbin.org/anything
        specHttpbin.pathParam("pp1","anything");
        Response response=given()
                .spec(specHttpbin)
                .accept("application/json")
                .when()
                .delete("/{pp1}");
        response.prettyPrint();
        //expected body
        JSONObject headers=new JSONObject();
        JSONObject expected=new JSONObject();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        expected.put("origin","176.42.164.88");
        expected.put("url", "http://httpbin.org/anything");
        expected.put("headers",headers);
        expected.put("json", "null");
        expected.put("method", "DELETE");
        // save response
        JsonPath actual=response.jsonPath();
        //assertions
        assertEquals(expected.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(expected.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(expected.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(expected.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(expected.get("origin"),actual.get("origin"));
        assertEquals(expected.get("url"),actual.get("url"));
        assertEquals(expected.get("method"),actual.get("method"));
        assertEquals(expected.get("json"),actual.get("json"));


    }
}
