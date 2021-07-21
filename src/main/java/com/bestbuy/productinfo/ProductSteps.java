package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class ProductSteps {

    @Step("Creating product with name: {0}, type: {1}, price: {2}, upc: {3}, shipping: {4},description: {5},manufacturer: {6},model:{7},url:{8} and image:{9}")
    public ValidatableResponse createNewProduct(String name, String type,int price, String upc, int shipping, String description,String manufacturer, String model,String Url,String image ) {

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

       return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type","application/json")
                .body(productpojo)
                .when()
                .post(EndPoints.POST_A_PRODUCT)
                .then().log().all()
                .statusCode(201);
    }
    @Step("Getting the Product information with productId : {0}")

    public ValidatableResponse getProductById(long productId){
        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id",productId)
                .header("Content-Type","application/json")
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID )
                .then();
    }

    @Step("Updating Product information with productId : {0} name : {1} , type : {2} , upc : {3} , price : {4} , description : {5} , model : {6} ")

    public ValidatableResponse updateProduct(long productId,String name , String type , String upc , int price , String description , String model){

  ProductPojo productsPojo = new ProductPojo();
//        productsPojo.setName(name);
//        productsPojo.setType(type);
//        productsPojo.setUpc(upc);
//        productsPojo.setPrice(price);pu
//        productsPojo.setDescription(description);
//        productsPojo.setModel(model);

        return  SerenityRest.rest().given()
                .header("Content-Type","application/json")
                .pathParam("id",productId)
                .log().all()
                .when()
                .body(productsPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID )
                .then();
    }
    @Step("Deleting the Product information with productId : {0} ")

    public ValidatableResponse deleteProductById(long productId){
        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();



    }
    @Step("Getting all services Information ")

    public ValidatableResponse getAllProduct() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();

    }


}
