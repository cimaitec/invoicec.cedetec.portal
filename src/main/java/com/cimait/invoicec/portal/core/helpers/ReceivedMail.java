package com.cimait.invoicec.portal.core.helpers;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component

public class ReceivedMail {
	
private String from;


private String date;

private String nameAttach;


public ArrayList<ReceivedMail> lreceivedMail;

/*
public ArrayList<ReceivedMail> getLreceivedMail() {
	return lreceivedMail;
}

public void setLreceivedMail(ArrayList<ReceivedMail> lreceivedMail) {
	this.lreceivedMail = lreceivedMail;
}*/

//constructores
public String getFrom() {
	return from;
}

public void setFrom(String strings) {
	this.from = strings;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getNameAttach() {
	return nameAttach;
}

public void setNameAttach(String nameAttach) {
	this.nameAttach = nameAttach;
}

}
