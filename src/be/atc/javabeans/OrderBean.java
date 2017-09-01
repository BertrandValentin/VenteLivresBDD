package be.atc.javabeans;

import java.io.Serializable;
import be.atc.entities.User;

public class OrderBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idOrder;
	private User user;

	public OrderBean() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}