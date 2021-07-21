package com.bestbuy.productinfo.serviceinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ServiceSteps {


        @Step("Creating services with name : {0} ")

        public ValidatableResponse createNewServices(String name){

            ServicesPojo servicePojo = new ServicesPojo();
            servicePojo.setName(name);


            return SerenityRest.rest().given()
                    .contentType(ContentType.JSON)
                    .log().all()
                    .when()
                    .body(servicePojo)
                    .post(EndPoints.POST_A_SERVICE)
                    .then();
        }
        @Step("Getting the services information with servicesId : {0}")

        public ValidatableResponse getServicesById(long servicesId){
            return SerenityRest.rest()
                    .given()
                    .pathParam("id",servicesId)
                    .contentType(ContentType.JSON)
                    .when()
                    .get(EndPoints.GET_SINGLE_SERVICE_BY_ID )
                    .then();
        }
        @Step("Updating Services information with servicesId : {0} name : {1} ")

        public ValidatableResponse updateServices(long servicesId,String name){

            ServicesPojo servicesPojo = new ServicesPojo();
            servicesPojo.setName(name);


            return  SerenityRest.rest().given()
                    .contentType(ContentType.JSON)
                    .pathParam("id",servicesId)
                    .log().all()
                    .when()
                    .body(servicesPojo)
                    .patch(EndPoints.UPDATE_SERVICE_BY_ID )
                    .then();
        }

        @Step("Deleting the Services information with ServicesId : {0} ")

        public ValidatableResponse deleteServicesById(long servicesId){
            return SerenityRest.rest()
                    .given()
                    .pathParam("id",servicesId)
                    .when()
                    .delete(EndPoints.DELETE_SERVICE_BY_ID)
                    .then();



        }
        @Step("Getting all services Information ")

        public ValidatableResponse getAllServices() {
            return SerenityRest.rest()
                    .given()
                    .when()
                    .get(EndPoints.GET_ALL_SERVICE)
                    .then();

        }





    }

