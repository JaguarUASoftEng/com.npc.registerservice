package com.npc.test.commands;

import java.util.UUID;

import com.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.TransactionEntry;
import org.npc.testmodel.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntryQuery implements ResultCommandInterface<TransactionEntry> {
	@Override
	public TransactionEntry execute() {
		return new TransactionEntry(
			this.transactionEntryRepository.get(this.recordID)
		);
	}

	//Properties
	private UUID recordID;
	public UUID getRecordID() {
		return this.recordID;
	}
	public TransactionEntryQuery setRecordID(UUID recordID) {
		this.recordID = recordID;
		return this;
	}
	
	private TransactionEntryRepositoryInterface transactionEntryRepository;
	public TransactionEntryRepositoryInterface getTransactionEntryRepository() {
		return this.transactionEntryRepository;
	}
	public TransactionEntryQuery setTransactionEntryRepository(TransactionEntryRepositoryInterface transactionEntryRepository) {
		this.transactionEntryRepository = transactionEntryRepository;
		return this;
	}
}
