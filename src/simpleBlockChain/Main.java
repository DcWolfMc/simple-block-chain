package simpleBlockChain;

import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Blockchain blockchain = new Blockchain();
		TransactionList list = new TransactionList();
		list.addTransaction( "corretora", "A", 100);
		list.addTransaction( "corretora", "B", 100);
		list.addTransaction( "corretora", "C", 100);
		list.addTransaction( "corretora", "D", 100);
		list.addTransaction( "corretora", "E", 100);
		list.addTransaction( "corretora", "F", 100);
		list.addTransaction( "corretora", "G", 100);
		list.addTransaction( "corretora", "H", 100);
		//System.out.println(list.getTransactionListSize());
		blockchain.createGenesisBlock(list);
		//System.out.println(blockchain.getLatestBlock().getBlockDataSize());
		blockchain.addVerifyTransaction( "A", "C", 5);
		blockchain.addVerifyTransaction( "D", "B", 10);
		blockchain.addVerifyTransaction( "C", "G", 12);
		blockchain.addVerifyTransaction( "G", "D", 8);
		blockchain.addVerifyTransaction( "E", "F", 5);
		blockchain.addVerifyTransaction( "B", "A", 15);
		blockchain.addVerifyTransaction( "D", "E", 30);
		blockchain.addVerifyTransaction( "H", "A", 30);
		blockchain.addVerifyTransaction( "D", "C", 15);
		blockchain.addVerifyTransaction( "E", "B", 20);
		blockchain.addVerifyTransaction( "B", "G", 10);
		blockchain.addVerifyTransaction( "C", "D", 20);
		blockchain.addVerifyTransaction( "F", "F", 15);
		blockchain.addVerifyTransaction( "H", "A", 5);
		blockchain.addVerifyTransaction( "A", "E", 15);
		blockchain.addVerifyTransaction( "G", "A", 10);
		System.out.println("\n\n Resultado da BlockChain: \n\n");
		blockchain.showBlockChain();
		System.out.println("Resultado da Validação da blockchain: "+blockchain.ValidateBlockchain());
	}
}
