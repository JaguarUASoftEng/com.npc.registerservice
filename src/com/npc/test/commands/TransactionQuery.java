package com.npc.test.commands;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import com.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.Transaction;
import org.npc.testmodel.enums.TransactionApiRequestStatus;
import org.npc.testmodel.repositories.interfaces.TransactionRepositoryInterface;


public class TransactionQuery implements ResultCommandInterface<Transaction> {
	@Override
	public Transaction execute(){
		return new Transaction(
				this.transactionRepository.byColumnId(this.c_id)
				);
	}
	
	private int c_id;
	public int getColumnId(){
		return this.c_id;
	}
	
	public TransactionQuery setColumnId(int c_id){
		this.c_id = c_id;
		return this;
	}
	
	private TransactionRepositoryInterface transactionRepository;
	public TransactionRepositoryInterface getTransactionRepository(){
		return this.transactionRepository;
	}
	
	public TransactionQuery setTransactionRepository(TransactionRepositoryInterface transactionRepository)
	{
		this.transactionRepository = transactionRepository;
		return this;
	}
}
