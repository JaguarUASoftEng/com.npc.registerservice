package com.npc.test;

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
import org.npc.testmodel.repositories.TransactionEntryRepository;

import com.npc.test.commands.CreateTransactionEntryCommand;
import com.npc.test.commands.TransactionEntriesQuery;
import com.npc.test.commands.TransactionEntryQuery;

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
	
	@PUT
	@Path("apiv0/transactionEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TransactionEntry createTransactionEntry(JAXBElement<TransactionEntry> apiTransactionEntry) {
		return (new CreateTransactionEntryCommand()).
			setApiTransactionEntry(apiTransactionEntry.getValue()).
			setTransactionEntryRepository(new TransactionEntryRepository()).
			execute();
	}
	
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello()
	{
		return "Hello From the Server";
	}
	
}
