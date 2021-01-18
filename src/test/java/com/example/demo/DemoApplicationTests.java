package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.ZeroCopyConsumer;
import org.apache.http.nio.client.methods.ZeroCopyPost;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

//	@Test
//	void contextLoads() throws InterruptedException, ExecutionException, IOException {
//        String filePath= "C:\\filetest/1234.txt";
//		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
////		File file = new File(filePath);
////		byte[] fileContent = Files.readAllBytes(file.toPath());
//        HttpEntity entity = MultipartEntityBuilder
//        		  .create()
//        		  .addTextBody("name", "5555555555")
//        		  .addBinaryBody("file", new File(filePath), ContentType.create("application/octet-stream"), "1234.txt")
////        		  .addPart("file", new ByteArrayBody(fileContent, ContentType.create("application/octet-stream"), "1234.txt" ))
//        		  .build();
//        try {
//            httpclient.start();
//            HttpPost request = new HttpPost("http://localhost:8080/attachment");
//            request.setEntity(entity);
//            Future<HttpResponse> future = httpclient.execute(request, null);
//            HttpResponse response = future.get();
//            System.out.println("Response: " + response.getStatusLine());
//            System.out.println("Shutting down");
//        } catch (Exception e){
//        	e.printStackTrace();
//        } finally {
//            httpclient.close();
//        }
//        System.out.println("Done");
//	}
//	@Test
//	void contextLoads() throws InterruptedException, ExecutionException, IOException {
//		  String filePath= "C:\\filetest\\1234.txt";//C:\filetest
//	      //Creating CloseableHttpClient object
//	      CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
//
//	      //Creating the MultipartEntityBuilder
//	      MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();
//
//	      //Setting the mode
//	      entitybuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//
//	      //Adding text
//	      entitybuilder.addTextBody("name", "This is the text part of our file");
//
//	      //Adding a file
//	      entitybuilder.addBinaryBody("file", new File(filePath),  ContentType.create("application/octet-stream"), "1234.txt");
//
//	      //Building a single entity using the parts
//	      HttpEntity mutiPartHttpEntity = entitybuilder.build();
//
//	      //Building the RequestBuilder request object
//	      RequestBuilder reqbuilder = RequestBuilder.post("http://localhost:8080/attachment");
//
//	      //Set the entity object to the RequestBuilder
//	      reqbuilder.setEntity(mutiPartHttpEntity);
//
//	      //Building the request
//	      HttpUriRequest multipartRequest = reqbuilder.build();
//
//	      //Executing the request
//	      Future<HttpResponse> future = httpclient.execute(multipartRequest, null);
//	      HttpResponse response = future.get();
//	}
	@Test
	void contextLoads() throws InterruptedException, ExecutionException, IOException, HttpException {
		String filePath= "C:\\filetest/1234.txt";//C:\filetest

		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
		    httpclient.start();
		    File upload = new File(filePath);
		    File download = new File("C:\\filetest\\newfile.txt");
		    ZeroCopyPost httpost = new ZeroCopyPost("http://localhost:8080/attachment", upload,
		            ContentType.create("application/octet-stream"));
		    ZeroCopyConsumer<File> consumer = new ZeroCopyConsumer<File>(download) {
		        @Override
		        protected File process(
		                final HttpResponse response,
		                final File file,
		                final ContentType contentType) throws Exception {
		            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
		                throw new ClientProtocolException("Upload failed: " + response.getStatusLine());
		            }
		            return file;
		        }
		
		    };
		    Future<File> future = httpclient.execute(httpost, consumer, null);
		    File result = future.get();
		    //System.out.println(result.toString());
		    System.out.println("Response file length: " + result.length());
		    System.out.println("Shutting down");
		} finally {
		    httpclient.close();
		}
		System.out.println("Done");
	}

}
