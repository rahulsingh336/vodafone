package com.vf.common.dto;

import java.util.List;

public class Reward {
	
	private List<Credit> credit;
	private List<Voucher> voucher;
	private Customer customer;
	
	
	
	public Reward() {
		super();
	}


	public Reward(List<Credit> credit, List<Voucher> voucher, Customer customer) {
		this.credit = credit;
		this.voucher = voucher;
		this.customer = customer;
	}
	
	
	public Reward(List<Voucher> voucher, Customer customer) {
		this.voucher = voucher;
		this.customer = customer;
	}

	

	public Reward(Customer customer, List<Credit> credit) {
		this.credit = credit;
		this.customer = customer;
	}


	public List<Credit> getCredit() {
		return credit;
	}
	public void setCredit(List<Credit> credit) {
		this.credit = credit;
	}
	public List<Voucher> getVoucher() {
		return voucher;
	}
	public void setVoucher(List<Voucher> voucher) {
		this.voucher = voucher;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reward other = (Reward) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Reward [credit=" + credit + ", voucher=" + voucher
				+ ", customer=" + customer + "]";
	}
	
	
	

}
