package simpleBlockChain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class TransactionList {

	private ArrayList<Transaction> data = new ArrayList<Transaction>();
	
	public void addTransaction(String from, String to,double value) throws NoSuchAlgorithmException {
		if(data.size()<8) {
			data.add(new Transaction(data.size(), from, to, value));
		}else {
			System.out.println("TransactionList já tem : "+ data.size() +" transações.");
		}
	}
//Se a minha lista tiver tamanho que é divisivel por um número 2 elevado a X eu posso:
	
	public String calculateRootHash() throws NoSuchAlgorithmException {
		String[] myArray = new String[(int) Math.ceil((double)data.size() / 2)];	
			if(myArray.length == 1) {
				return calculateHash(data.get(0).getHash() + data.get(1).getHash()); 
			}else {
				for(int i = 1 ; i<data.size() ; i+=2) {
					String string = calculateHash(data.get(i-1).getHash() + data.get(i).getHash());
					myArray[( (int) Math.ceil((double)i/2) ) -1] = string;
				}
			
				if(myArray[myArray.length-1] == null) {
					myArray[myArray.length-1] = calculateHash(data.get(myArray.length-1).getHash() + data.get(myArray.length-1).getHash());
				}
			return calculateRootHash(myArray);
			}
	}
	private String calculateRootHash(String[]myArray) throws NoSuchAlgorithmException {
		//System.out.println("myArray last index value: "+myArray[myArray.length-1]);
		//System.out.println("myArray.length: "+myArray.length);
		//System.out.println("myArray: "+myArray.toString());
		String[] newArray = new String[(int) Math.ceil(myArray.length / 2)];
		//System.out.println("newArray.length: "+newArray.length);
		if(newArray.length == 1) {
			//System.out.println("myArray[0] : "+myArray[0]);
			//System.out.println("myArray[1] : "+myArray[1]);
			return calculateHash(myArray[0] + myArray[1]); 
		}else {
			for(int i =1;i<myArray.length;i+=2) {
				//System.out.println("newArray.length+ "+newArray.length+ " i value: + "+ i);
				String string = calculateHash(myArray[i-1] + myArray[i]);
				newArray[( (int) Math.ceil((double)i/2) ) -1] = string;
			}
		
			if(newArray[newArray.length-1] == null) {
				newArray[newArray.length-1] = calculateHash(myArray[myArray.length-1] + myArray[myArray.length-1]);
			}
		return calculateRootHash(newArray);
		}
	}
	
	private String calculateHash(String string) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashInBytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public void printTransactionList() {
		data.forEach((n) -> n.printTransaction());
	}
	public double verifyTransactionValue(String from) {
		//System.out.println("Inside TransactionList");
		//System.out.println("TransactionList size: "+data.size());
		double accumulatedValue = 0;
		for(int i = 0 ; i< data.size();i++) {
			//System.out.println("inside for");
			if(data.get(i).getFrom().compareToIgnoreCase(from) == 0) {
				accumulatedValue -= data.get(i).getValue();
				System.out.println("Achei accumulatedValue From: "+from+" Value:"+ accumulatedValue );
			}else if(data.get(i).getTo().compareToIgnoreCase(from) == 0) {
				accumulatedValue +=data.get(i).getValue();
				System.out.println("Achei accumulatedValue From: "+from+" Value:"+ accumulatedValue );
			}
			//System.out.println("Inside TransactionList"+ "accumulatedValue in chain: \n From: "+from+" accumulatedValue: "+ accumulatedValue);
		}
		//System.out.println("exiting TransactionList");
		return accumulatedValue;
	}
	
	public int getTransactionListSize() {
		return data.size();
	}
	public ArrayList<Transaction> getData() {
		return data;
	}


	public void setData(ArrayList<Transaction> data) {
		this.data = data;
	}
	
	
}
