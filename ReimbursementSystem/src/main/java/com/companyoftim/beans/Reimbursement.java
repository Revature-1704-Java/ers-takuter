package com.companyoftim.beans;

public class Reimbursement {
	public int id;
	private String reason;
	private float amount;
	private boolean approval;
	private boolean complete;
	
	public Reimbursement(int id, String reason, float amount, boolean approval, boolean complete) {
		super();
		this.id = id;
		this.reason = reason;
		this.amount = amount;
		this.approval = approval;
		this.complete = complete;
	}
	
	public Reimbursement() {};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reason=" + reason + ", amount=" + amount + ", approval=" + approval
				+ ", complete=" + complete + "]";
	}	
}
