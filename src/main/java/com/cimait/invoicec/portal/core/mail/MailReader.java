package com.cimait.invoicec.portal.core.mail;

import java.io.*;
import java.util.*;

import javax.mail.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cimait.invoicec.portal.core.helpers.ReceivedMail;

@Component
public class MailReader {

	@Autowired
	protected ReceivedMail receivedMail;

	public MailReader() {

	}

	Folder inbox;

	// Constructor of the calss.
	public List<ReceivedMail> attachReader(String correo) throws IOException {
		/* Set the mail properties */
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			/* Create the session and get the store for read the mail. */
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "anghyduo@gmail.com",
					"lacasarojadepapa");

			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			// Get directory
			Message message[] = folder.getMessages();

			ArrayList<ReceivedMail> lreceivedMail = new ArrayList<ReceivedMail>();

			//System.out.println("longitud del arreglo" + message.length);

			for (int i = 0; i < message.length; i++) {
				//System.out.println(i + ": " + message[i].getSubject());

				//String from [] = new String [message[i].getFrom().length];
				
								
				
				Multipart multipart = (Multipart) message[i].getContent();

				for (int j = 0; j < multipart.getCount(); j++) {

					// System.out.println("multipart " + multipart.getCount());
					Part part = multipart.getBodyPart(j);

					String disposition = part.getDisposition();

					if (disposition != null) {

					
						ReceivedMail tmp = new ReceivedMail();

						tmp.setFrom(message[i].getFrom()[0].toString());
						tmp.setDate(message[i].getReceivedDate().toString());
						tmp.setNameAttach(part.getFileName());
						lreceivedMail.add(tmp);
						

					}

				}

			}

			System.out.println("La lista de elementos es:    "
					+ lreceivedMail.size());

			for (int i = 0; i < lreceivedMail.size(); i++) {

				System.out.println("los elementos son"
						+ lreceivedMail.get(i).getNameAttach() + "---"
						+ lreceivedMail.get(i).getDate() + "----"
						+ lreceivedMail.get(i).getFrom());
			}

			// Close connection
			folder.close(false);
			store.close();

		} catch (javax.mail.MessagingException me) {
			me.printStackTrace();
		}
		return receivedMail.lreceivedMail;
		
		
	
	}
}