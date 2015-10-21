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

import com.npc.test.commands.CreateProductCommand;
import com.npc.test.commands.ProductQuery;
import com.npc.test.commands.ProductsQuery;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.api.ProductListing;
import org.npc.testmodel.repositories.ProductRepository;

import com.npc.test.commands.CreateTransactionEntryCommand;
import com.npc.test.commands.TransactionEntryQuery;
import com.npc.test.commands.TransactionEntriesQuery;
import org.npc.testmodel.api.TransactionEntry;
import org.npc.testmodel.api.TransactionEntryListing;
import org.npc.testmodel.repositories.TransactionEntryRepository;

@Path("/registerservice/")
public class RegisterServicesResource {
	@GET
	@Path("apiv0/products")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductListing getProducts() {
		return (new ProductsQuery()).
			setProductRepository(new ProductRepository()).
			execute();
	}
	
	@GET
	@Path("apiv0/product/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(@PathParam("productid") UUID productId) {
		return (new ProductQuery()).
			setProductId(productId).
			setProductRepository(new ProductRepository()).
			execute();
	}
	
	@PUT
	@Path("apiv0")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Product createProduct(JAXBElement<Product> apiProduct) {
		return (new CreateProductCommand()).
			setApiProduct(apiProduct.getValue()).
			setProductRepository(new ProductRepository()).
			execute();
	}
	
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
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Successful test (.../test/)";
	}
}
