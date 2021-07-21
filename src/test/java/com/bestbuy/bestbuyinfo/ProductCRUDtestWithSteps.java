package com.bestbuy.bestbuyinfo;

import com.bestbuy.productinfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.oracle.jrockit.jfr.Transition.To;

@RunWith(SerenityRunner.class)
public class ProductCRUDtestWithSteps extends TestBase {

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

    @Steps
    ProductSteps productSteps;

    @Title("This will create a new Product and verify new id")
    @Test
    public void test01(){

        productid = productSteps.createNewProduct(name,type,price,upc,shipping,description,manufacturer,model,Url,image)
                .log().all().
                        statusCode(201)
                .extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("product id is : " + productid);
    }

    @Title("This test will get the product information by ID")
    @Test

    public void test02(){
        productSteps.getProductById(productid).statusCode(200);

    }

    @Title("This test will update the product information and verify the updated information")
    @Test

    public void test03(){
        name = name+"_Changed";
        price = 900;
        upc = upc + "_added";
        productSteps.updateProduct(productid,name,type,upc,price,description,model).statusCode(200);
//        productSteps.getProductById(productid).body("name",equalTO(name));

    }
    @Title("This test will delete the product information and verify the product is deleted ")
    @Test

    public void test04(){
        productSteps.deleteProductById(productid).statusCode(200);
        productSteps.getProductById(productid).statusCode(404);
    }
    @Title("This test will get all product from list")
    @Test
    public void test05(){
        productSteps.getAllProduct().log().all().statusCode(200);
    }


}
