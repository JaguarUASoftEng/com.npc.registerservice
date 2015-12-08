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

@Path("/")
public class ProductResource {
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
	
	@GET
	@Path("apiv0/product/getByLookUpCode/{lookupcode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductByLookUpCode(@PathParam("lookupcode") String lookUpCode){
		Product test = (new ProductQuery()).
				setLookUpCode(lookUpCode).
				setProductRepository(new ProductRepository()).
				execute();
		
		try{
			System.out.println("This is the Id "+ test.getLookupCode());
			System.out.println("This is the UUID "+ test.getId());
		}catch(Exception e){
			System.out.println("this thing maybe null");
		}
		
		if(test.getLookupCode() == null)
		{
			System.out.println("Definitely null");
		}
		
		return test;
	}
	
	@GET
	@Path("apiv0/DummyData/Html")
	@Produces(MediaType.TEXT_HTML)
	public String DummyDataToHtml(){
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
		
		return "<b>Description: </b>"+dummyData.getDescription()+"<br><b>LookUpCode: </b>"+dummyData.getLookupCode()+"<br>";
	}
	
	/*
	@GET
	@Path("apiv0/Product/DatabaseAccess/ByLookUpCode/{lookupcode}")
	@Produces(MediaType.TEXT_HTML)
	public String DabaseAccess(){
		Product
		
		return "";
	}*/
	
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
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Successful test (.../test/)";
	}
}
