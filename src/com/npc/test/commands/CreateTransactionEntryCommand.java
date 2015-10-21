package com.npc.test.commands;

import java.util.UUID;

import com.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.TransactionEntry;
import org.npc.testmodel.enums.TransactionEntryApiRequestStatus;
import org.npc.testmodel.repositories.interfaces.TransactionEntryRepositoryInterface;

public class CreateTransactionEntryCommand implements ResultCommandInterface<TransactionEntry> {
	@Override
	public TransactionEntry execute() {
		
		if (this.apiTransactionEntry.getRecordID() == -1) {
			return (new TransactionEntry()).setApiRequestStatus(TransactionEntryApiRequestStatus.INVALID_INPUT);
		}
		
		org.npc.testmodel.models.TransactionEntry modelTransactionEntry = this.transactionEntryRepository.byRecordID(this.apiTransactionEntry.getRecordID());
		if (modelTransactionEntry != null) {
			return (new TransactionEntry()).setApiRequestStatus(TransactionEntryApiRequestStatus.RECORD_ID_ALREADY_EXISTS);
		}
		
		this.apiTransactionEntry.setId(UUID.randomUUID());
		modelTransactionEntry = new org.npc.testmodel.models.TransactionEntry(this.apiTransactionEntry);
		modelTransactionEntry.save();

		return this.apiTransactionEntry;
	}

	//Properties
	private TransactionEntry apiTransactionEntry;
	public TransactionEntry getApiTransactionEntry() {
		return this.apiTransactionEntry;
	}
	public CreateTransactionEntryCommand setApiTransactionEntry(TransactionEntry apiTransactionEntry) {
		this.apiTransactionEntry = apiTransactionEntry;
		return this;
	}
	
	private TransactionEntryRepositoryInterface transactionEntryRepository;
	public TransactionEntryRepositoryInterface getTransactionEntryRepository() {
		return this.transactionEntryRepository;
	}
	public CreateTransactionEntryCommand setTransactionEntryRepository(TransactionEntryRepositoryInterface transactionEntryRepository) {
		this.transactionEntryRepository = transactionEntryRepository;
		return this;
	}
}
