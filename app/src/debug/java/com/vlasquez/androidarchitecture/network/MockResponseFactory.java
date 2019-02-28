package com.vlasquez.androidarchitecture.network;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.Request;

public class MockResponseFactory {

  private final Context context;

  private final int startIndex;

 @Inject
 MockResponseFactory(Context context, @Named("base_url") String baseUrl){
   this.context = context;
   startIndex = baseUrl.length();
 }

 String getMockResponse(Request request){
   String[] endpointParts = getEndpoint(request).split("/");
   return MockResourceLoader.getResponseString(context,request.method(),endpointParts) ;
 }

 private String getEndpoint(Request request){
   String url = request.url().toString();
   int queryParamStart=url.indexOf("?");
   return queryParamStart == -1 ? url.substring(startIndex): url.substring(startIndex,queryParamStart);
 }
}
