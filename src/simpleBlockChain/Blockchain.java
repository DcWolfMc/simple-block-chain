package simpleBlockChain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Blockchain {

	private ArrayList<Block> chain = new ArrayList<Block>();
	public void createGenesisBlock(TransactionList data) throws NoSuchAlgorithmException {
		chain.add(new Block(0,  "",data));
		System.out.println("New Genesis: ");
		getLatestBlock().printData();
		
	}
	
	public Block getLatestBlock() {
		return chain.get(chain.size()-1); 
	}
	
	private void addBlock() throws NoSuchAlgorithmException {
		chain.add(new Block(chain.size(), this.getLatestBlock().getHash()));
	}
	
	public void showBlockChain() {
		for(int n=0; n<chain.size();n++) {
			Block currentBlock = chain.get(n);
			System.out.println("{\n"+"\s"+"index: "+currentBlock.getIndex()+",");
			System.out.println("\s"+"previousHash: "+currentBlock.getPreviousHash()+",");
			System.out.println("\s"+"timestamp: "+currentBlock.getTimestamp()+",");
			
			System.out.println("\s"+"data: {");
			currentBlock.printData();
			System.out.println("},");

			System.out.println("\s"+"hash: "+currentBlock.getHash()+",");
			System.out.println("\s"+"rootHash: "+currentBlock.getRoot()+",");
			System.out.println("\s"+"nunce: "+currentBlock.getNunce()+",");
			System.out.println("\s"+"difficulty: "+currentBlock.getDifficulty()+",\n"+"},");
		}
	}
	public void addVerifyTransaction(String from,String to, double value) throws NoSuchAlgorithmException {
		double accumulatedValue = 0;
		
		if(getLatestBlock().getBlockDataSize() == 8) {
			addBlock();
		}
		System.out.println("chain size: "+chain.size());
		for(int i =chain.size()-2;i>=0;i--) {
			System.out.println("inside chain: "+ i);
			accumulatedValue += chain.get(i).getData().verifyTransactionValue(from);
			System.out.println("accumulatedValue in chainblock: \n From: "+from+" accumulatedValue: "+ accumulatedValue);
			if(accumulatedValue >= value) {
				getLatestBlock().addToBlockTransactionList(from, to, value);
				if(getLatestBlock().getBlockDataSize() == 8) {
					getLatestBlock().setRoot(getLatestBlock().getData().calculateRootHash());
					System.out.println("Block finished");
					getLatestBlock().printData();
				}
				break;
			}
		}
		
	}
	public boolean ValidateBlockchain() throws NoSuchAlgorithmException {
		for(int i = chain.size()-1;i>=0;i--) {
			if(chain.get(i).getRoot().compareTo(chain.get(i).getData().calculateRootHash())!=0) {
				return false;
			}
		}
		return true;
		
	}
}
