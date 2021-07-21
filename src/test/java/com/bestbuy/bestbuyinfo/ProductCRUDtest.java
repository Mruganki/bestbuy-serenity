package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

//@RunWith(SerenityRunner.class)
public class ProductCRUDtest extends TestBase {

    static String name = "SoloerCell"+ TestUtils.getRandomValue();
    static String type = "batteries"+ TestUtils.getRandomValue();
    static int  price= Integer.parseInt("90"+ TestUtils.getRandomValue());
    static String upc = "Nextday"+ TestUtils.getRandomValue();
    static int  shipping = Integer.parseInt("80" + TestUtils.getRandomValue());
    static String description = "power99"+ TestUtils.getRandomValue();
    static String manufacturer = "UKpowerltd"+ TestUtils.getRandomValue();
    static String model = "new"+ TestUtils.getRandomValue();
    static String Url = "fkju200@gmail.com"+ TestUtils.getRandomValue();
    static String image = "007"+ TestUtils.getRandomValue();
    static long productid;

    @Title("This will create a new Product")
    @Test
    public void test01(){

        ProductPojo productpojo= new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(price);
        productpojo.setUpc(upc);
        productpojo.setShipping(shipping);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setUrl(Url);
        productpojo.setImage(image);


        SerenityRest.rest()
                .given().log().all()
                        .header("Content-Type","application/json")
                        .body(productpojo)
                        .when()
                        .post()
                        .then().log().all()
                .statusCode(201);

    }
    @Title("Verify if the Product was added to the application")
    @Test
    public void test02() {


        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value =

                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get()
                        .then().log().all()
                        .statusCode(200)
                        .extract()
                        .path(p1 +name+ p2);
        assertThat(value, hasValue(name));
        System.out.println(value);
        productid = (int) value.get("id");
    }

}
