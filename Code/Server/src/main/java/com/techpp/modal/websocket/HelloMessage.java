package com.techpp.modal.websocket;


public class HelloMessage {

	 private String name;
	 private String groupcode;

	 public HelloMessage() {
	 }

	 public HelloMessage(String name, String groupcode) {
	   this.name = name;
	   this.groupcode = groupcode;
	 }

	 public String getName() {
	   return name;
	 }

	 public void setName(String name) {
	   this.name = name;
	 }

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	 
	}