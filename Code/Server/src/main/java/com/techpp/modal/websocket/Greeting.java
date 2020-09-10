package com.techpp.modal.websocket;



public class Greeting {

	 private String content;
	 private String groupcode;

	 public Greeting() {
	 }
	 public Greeting(String content) {
		 this.content = content;
	 }

	 public Greeting(String content, String groupcode) {
	   this.content = content;
	   this.groupcode = groupcode; 
	 }

	 public String getContent() {
	   return content;
	 }

	public String getGroupcode() {
		return groupcode;
	}

	}

