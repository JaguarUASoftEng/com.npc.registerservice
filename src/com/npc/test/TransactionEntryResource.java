package com.npc.test;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import org.npc.testmodel.api.TransactionEntry;
import org.npc.testmodel.api.TransactionEntryListing;
import org.npc.testmodel.api.Transaction;
import org.npc.testmodel.repositories.TransactionEntryRepository;

import com.npc.test.commands.CreateTransactionCommand;
import com.npc.test.commands.CreateTransactionEntryCommand;
import com.npc.test.commands.TransactionEntriesQuery;
import com.npc.test.commands.TransactionEntryQuery;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.npc.testmodel.api.Product;


@Path("/")
public class TransactionEntryResource {
	@GET
	@Path("apiv0/transactionEntries")
	@Produces(MediaType.APPLICATION_JSON)
	public TransactionEntryListing getTransactionEntries() {
		return (new TransactionEntriesQuery()).
			setTransactionEntryRepository(new TransactionEntryRepository()).
			execute();
	}
	
	@GET
	@Path("apiv0/transactionEntry/{recordid}")
	@Produces(MediaType.APPLICATION_JSON)
	public TransactionEntry getTransactionEntry(@PathParam("recordid") UUID recordID) {
		return (new TransactionEntryQuery()).
			setRecordID(recordID).
			setTransactionEntryRepository(new TransactionEntryRepository()).
			execute();
	}
	
	@GET
	@Path("apiv0/sendsomething")
	public void dosomething() throws IOException
	{
		System.out.println("did something");
		 
		
		Product dummyData = new Product();
		
		dummyData.setDescription("Dummy Data Description");
		dummyData.setLookupCode("DummyLookUpCode");
		dummyData.setPrice(1);
		dummyData.setItemType(2);
		dummyData.setCost(3);
		dummyData.setQuantity(4);
		dummyData.setReorderPoint(5);
		dummyData.setRestockLevel(6);
		dummyData.setExtendedDescription("Extended Description of Dummy Data");
		dummyData.setActive(1);
		dummyData.setMSRP(7);
		dummyData.setApiRequestMessage("Api Request Message of Dummy Data");
		
		String temp = "{"+
						"id "
						+"}";
		
		URL url = new URL("http://localhost:8080/registerservice/apiv0/transactionEntry");
		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		httpcon.setDoOutput(true);
		httpcon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpcon.getOutputStream());
		out.write(" ");
		out.close();
		httpcon.getInputStream();
	}
	
	@PUT
	@Path("apiv0/transactionEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createTransactionEntry(ArrayList<Product> apiTransactionEntry) {
		
		double transactionEntryTotal = 0;
		
		for(int productNumber = 0; productNumber < apiTransactionEntry.size(); productNumber++)
		{
			CreateTransactionEntryCommand writeInstance = new CreateTransactionEntryCommand();
			
			writeInstance.setApiTransactionEntry(new TransactionEntry().setPrice(apiTransactionEntry.get(productNumber).getPrice())
					.setProductID(apiTransactionEntry.get(productNumber).getId())
					.setQuantity(apiTransactionEntry.get(productNumber).getQuantity())
					.setRecordID(productNumber)
					.setTransactionID(productNumber)).execute();
			
			
			transactionEntryTotal = transactionEntryTotal + (apiTransactionEntry.get(productNumber).getPrice() * apiTransactionEntry.get(productNumber).getQuantity());
		
		}
		
		CreateTransactionCommand TransactionInstance = new CreateTransactionCommand();	
		TransactionInstance.setApiTransaction(new Transaction()
			.setAmount((int)transactionEntryTotal)
			.setTransactionType("new transaction")
			.setR_ID(UUID.randomUUID()));
		
	}
	
}
