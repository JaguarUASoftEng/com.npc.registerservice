package com.npc.test.commands;

import com.npc.test.commands.interfaces.ResultCommandInterface;

import java.util.Random;

import org.npc.testmodel.api.Transaction;
import org.npc.testmodel.enums.TransactionApiRequestStatus;
import org.npc.testmodel.repositories.interfaces.TransactionRepositoryInterface;

public class CreateTransactionCommand implements ResultCommandInterface<Transaction>{
	@Override
	public Transaction execute(){
		if (this.apiTransaction.getC_ID() == -1){
			return (new Transaction()).setApiRequestStatus(TransactionApiRequestStatus.INVALID_INPUT);
		}
		
		org.npc.testmodel.models.Transaction modelTransaction = this.transactionRepository.byColumnId(this.apiTransaction.getC_ID());
		if (modelTransaction != null){
			return this.apiTransaction;
		}
		
		Random rn = new Random();
		
		this.apiTransaction.setC_ID(rn.nextInt(4587));
		modelTransaction = new org.npc.testmodel.models.Transaction(this.apiTransaction);
		modelTransaction.save();
		
		return this.apiTransaction;
	}

	private Transaction apiTransaction;
	public Transaction getApiTransaction(){
		return this.apiTransaction;
	}
	
	public CreateTransactionCommand setApiTransaction(Transaction apiTransaction){
		this.apiTransaction = apiTransaction;
		return this;
	}
	
	private TransactionRepositoryInterface transactionRepository;
	public TransactionRepositoryInterface getTrasactionRepository(){
		return this.transactionRepository;
	}
	
	public CreateTransactionCommand setTransactionRepository(TransactionRepositoryInterface transactionRepository){
		this.transactionRepository = transactionRepository;
		return this;
	}
}
