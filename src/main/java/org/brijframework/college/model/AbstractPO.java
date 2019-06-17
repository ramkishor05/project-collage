package org.brijframework.college.model;

import java.io.Serializable;

public abstract class AbstractPO<I extends Serializable> implements PO<I>,
		Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected I id;

	protected AbstractPO() {
	}

	protected AbstractPO(I id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		if (id != null)
			return id.hashCode() * 31 + getClass().hashCode();
		return super.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!getClass().equals(other.getClass()))
			return false;
		return (id != null) && id.equals(((PO<I>) other).getId());
	}

	public void setId(int id) {
		// TODO Auto-generated method stub

	}
}
