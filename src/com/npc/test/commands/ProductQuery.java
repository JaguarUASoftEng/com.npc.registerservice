package com.npc.test.commands;

import java.util.UUID;

import com.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.repositories.interfaces.ProductRepositoryInterface;

public class ProductQuery implements ResultCommandInterface<Product> {
	@Override
	public Product execute(){
		return new Product(
				this.productRepository.byLookupCode(this.lookUpCode)
				);
	}
	
	private UUID productId;
	public UUID getProductId(){
		return this.productId;
	}
	
	public ProductQuery setProductId(UUID productId){
		this.productId = productId;
		return this;
	}
	
	private String lookUpCode;
	public String getLookUpCode(){
		return this.lookUpCode;
	}
	
	public ProductQuery setLookUpCode(String lookUpCode){
		this.lookUpCode = lookUpCode;
		return this;
	}
	
	private ProductRepositoryInterface productRepository;
	public ProductRepositoryInterface getProductRepository(){
		return this.productRepository;
	}
	
	public ProductQuery setProductRepository(ProductRepositoryInterface productRepository)
	{
		this.productRepository = productRepository;
		return this;
	}
}
