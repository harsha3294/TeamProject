package com.mastertheboss.undertow;


import io.undertow.Undertow;
import io.undertow.server.*;
import io.undertow.util.Headers;
import java.util.Deque;
import java.util.*;
 
/**
 * Hello world!
 * 
 */
public class App {
    public static void main(final String[] args) {
        Undertow server = Undertow.builder().addHttpListener(80, "0.0.0.0")
                .setHandler(new HttpHandler() {
 
                    public void handleRequest(final HttpServerExchange exchange)
                            throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,
                                "text/plain");
                        	Map<String, Deque<String>> queryParams = exchange.getQueryParameters();
			Deque<String> dq = queryParams.get("cc");
                        String input = dq.remove();
                        URLSafe newobj = new URLSafe();
			String sendtext = newobj.functionHandler(newobj,input);
 		
                        exchange.getResponseSender().send(sendtext);
                    }
                }).setWorkerThreads( 2000).setIoThreads( 500 ).build();
        server.start();
    }

    public static String generateText(String input){
       
		StringBuilder sb = new StringBuilder();
		sb.append("Hello");
		sb.append(input);
		return  sb.toString();
    }
}
