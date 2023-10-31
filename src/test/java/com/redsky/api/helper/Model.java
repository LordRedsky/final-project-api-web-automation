//package com.redsky.api.helper;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
public class Model {
//
//    private static RequestSpecification request;
////    private static Response response;
//    public static void setupHeaders() {
//        String access_token = "653d0b19a38ceded3354529f";
//
//        request = RestAssured.given()
//                .header("Content-Type", "application/json")
//                .header("Accept", "application/json")
//                .header("app-id", access_token);
//    }
//
//    public static Response setupMethodAndEndpoint(String method, String endpoint_name) {
//
//        Response res = null;
//
//        switch (method) {
//            case "GET":
//                if (endpoint_name.equals("user")){
//                   res = request.when().get(Endpoint.user_url);
//
//                }
//                else if (endpoint_name.equals(" user")) {
//                    res = request.when().get(Endpoint.baseURL + " user");
//                }
//                else if (endpoint_name.equals("tag")) {
//                    res = request.when().get(Endpoint.tag_url);
//                }
//                else if (endpoint_name.equals(" tag")) {
//                    res = request.when().get(Endpoint.baseURL + " tag");
//                }
//
//                break;
//
//            case "POST":
//                res = request.when().post(Endpoint.user_create_url);
//                break;
//
//            default:
//                throw new IllegalStateException("Unexpected value: " + method);
//        }
//
//        return res;
//    }
//
////    public static int getStatusCode(int status_code) {
////
////    }
}
