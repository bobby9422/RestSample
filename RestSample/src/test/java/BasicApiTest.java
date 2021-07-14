import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import p1.BasicApi;

public class BasicApiTest {

	@Test
	public void getRequest() {
		Response response = given()
				.get("https://data.sfgov.org/resource/p4e4-a5a7.json")
				.then().extract().response();
		new BasicApi().writeToExcel(response.asString());
	}
	

}
