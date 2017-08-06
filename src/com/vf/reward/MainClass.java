package com.vf.reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import com.vf.common.dto.Credit;
import com.vf.common.dto.Customer;
import com.vf.common.dto.Reward;
import com.vf.common.dto.Voucher;

public class MainClass {

	public static void main(String[] args) {

		MainClass mainClass = new MainClass();
		mainClass.getRewardsByPerson(new ArrayList<Voucher>(), new ArrayList<Credit>());

	}

	private List<Reward> getRewardsByPerson(List<Voucher> vouchers,
			List<Credit> credits) {

		Map<Customer, List<Voucher>> voucherGroup = buildVoucherGroup(vouchers);
		Map<Customer, List<Credit>> creditGroup = buildCreditGroup(credits);
		
		List<Reward> rewards = new ArrayList<Reward>();

		addRewards(voucherGroup, creditGroup, rewards);

		return rewards;
	}

	private void addRewards(Map<Customer, List<Voucher>> voucherGroup,
			Map<Customer, List<Credit>> creditGroup, List<Reward> rewards) {
		if (voucherGroup.size() >= creditGroup.size()) {

			addVoucherGroup(voucherGroup, creditGroup, rewards);
			
			addCreditGroup(creditGroup, rewards);

		} else {

			extractCreditRewards(voucherGroup, creditGroup, rewards);

			addVoucherRewards(voucherGroup, rewards);

		}
	}

	private void addVoucherRewards(Map<Customer, List<Voucher>> voucherGroup,
			List<Reward> rewards) {
		for (Entry<Customer, List<Voucher>> voucher : voucherGroup
				.entrySet()) {

			rewards.add(new Reward(voucher.getValue(), voucher.getKey()));
		}
	}

	private void extractCreditRewards(
			Map<Customer, List<Voucher>> voucherGroup,
			Map<Customer, List<Credit>> creditGroup, List<Reward> rewards) {
		for (Entry<Customer, List<Credit>> credit : creditGroup.entrySet()) {

			Reward reward = new Reward();

			if (voucherGroup.get(credit.getKey()) != null) {
				reward.setCredit(credit.getValue());
				reward.setCustomer(credit.getKey());
				reward.setVoucher(voucherGroup.get(credit.getKey()));
				voucherGroup.remove(credit.getKey());
				rewards.add(reward);
			} else {
				reward.setCredit(credit.getValue());
				reward.setCustomer(credit.getKey());
				rewards.add(reward);
			}
		}
	}

	private void addCreditGroup(Map<Customer, List<Credit>> creditGroup,
			List<Reward> rewards) {
		for (Entry<Customer, List<Credit>> credit : creditGroup.entrySet()) {

			rewards.add(new Reward(credit.getKey(), credit.getValue()));
		}
	}

	private void addVoucherGroup(Map<Customer, List<Voucher>> voucherGroup,
			Map<Customer, List<Credit>> creditGroup, List<Reward> rewards) {
		for (Entry<Customer, List<Voucher>> voucher : voucherGroup
				.entrySet()) {

			Reward reward = new Reward();

			if (creditGroup.get(voucher.getKey()) != null) {
				reward.setVoucher(voucher.getValue());
				reward.setCustomer(voucher.getKey());
				reward.setCredit(creditGroup.get(voucher.getKey()));
				creditGroup.remove(voucher.getKey());
				rewards.add(reward);
			} else {
				reward.setVoucher(voucher.getValue());
				reward.setCustomer(voucher.getKey());
				rewards.add(reward);
			}
		}
	}

	private Map<Customer, List<Credit>> buildCreditGroup(List<Credit> credits) {
		Map<Customer, List<Credit>> creditGroup = credits.stream().collect(
				Collectors.groupingBy(credit -> new Customer(credit
						.getCustomer().getFirstName(), credit.getCustomer()
						.getLastName())));
		return creditGroup;
	}

	private Map<Customer, List<Voucher>> buildVoucherGroup(
			List<Voucher> vouchers) {
		Map<Customer, List<Voucher>> voucherGroup = vouchers.stream().collect(
				Collectors.groupingBy(voucher -> new Customer(voucher
						.getCustomer().getFirstName(), voucher.getCustomer()
						.getLastName())));
		return voucherGroup;
	}
}
