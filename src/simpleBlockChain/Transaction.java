package simpleBlockChain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Transaction {

	
	private int index;
	private String from;
	private String to;
	private String hash;
	private double value;
	
	public Transaction(int index,String from, String to,double value ) throws NoSuchAlgorithmException {
		this.index = index;
		this.from = from;
		this.to = to;
		this.value = value;
		this.hash= this.calculateHash();
		
	}
	public String calculateHash() throws NoSuchAlgorithmException{
		String string = index+";"+from+">"+to+";"+value;
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashInBytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public void printTransaction() {
		System.out.println("Transaction "+index+ ": " +index+";"+from+">"+to+";"+value);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
}
