package service_center.model;

import java.util.*;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import service_center.dao.*;
import service_center.interfaces.IServiceCenter;

public class ReceiptHibernate implements IServiceCenter {
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;

@Override
	@Transactional
	public boolean addReceipt(Receipt receipt) {
		em.persist(receipt);

		return true;
	}

	/* ADD CLIENT */
	@Override
	@Transactional
	public boolean addClient(Client client) {
		Client clientEntity = getClient(client.getTelNumber());
		if (clientEntity == null) {
			clientEntity = new Client(client.getfName(), client.getsName(), client.getTelNumber(), client.getEmail(),
					client.getAddress());
			em.persist(clientEntity);
			return true;
		}
		return false;
	}

	private Client getClient(String telNumber) {
		Query query = em.createQuery("SELECT c FROM Client c WHERE c.telNumber = ?1");
		query.setParameter(1, telNumber);
		List<Client> res = query.getResultList();
		if (res == null || res.size() == 0)
			return null;
		return res.get(0);
	}

	/* ADD SHOP */
	@Override
	@Transactional
	public boolean addShop(Shop shop) {
		Shop shopEntity = em.find(Shop.class, shop.getId());
		if (shopEntity == null) {
			shopEntity = new Shop(shop.getName(), shop.getCode());
			em.persist(shop);
		}
		return true;
	}

	/* ADD USER */
	@Override
	@Transactional
	public boolean addUser(User user) {
		User userEntity = getUser(user.getTelNumber());
		if (userEntity == null) {
			userEntity = new User(user.getLogin(), user.getPassword(), user.getfName(), user.getsName(),
					user.getTelNumber());
			em.persist(userEntity);
			return true;
		}
		return false;
	}

	private User getUser(String telNumber) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.telNumber = ?1");
		query.setParameter(1, telNumber);
		List<User> res = query.getResultList();
		if (res == null || res.size() == 0)
			return null;
		return res.get(0);
	}

	/* ADD POSITION */
	@Override
	@Transactional
	public boolean addPosition(Position position) {
		Position positionEntity = getPosition(position.getPositionJob());
		if (positionEntity == null) {
			positionEntity = new Position(position.getAccessLevel(), position.getPositionJob());
			em.persist(positionEntity);
			return true;
		}
		return false;
	}

	private Position getPosition(String positionJob) {
		Query query = em.createQuery("SELECT p FROM Position p WHERE p.positionJob = ?1");
		query.setParameter(1, positionJob);
		List<Position> res = query.getResultList();
		if (res == null || res.size() == 0)
			return null;
		return res.get(0);
	}

	/* ADD PRODUCT */
	@Override
	@Transactional
	public boolean addProduct(Product product) {
		Product productFind = getProductFind(product.getModel(), product.getManufacturer());
		if (productFind != null)
			return false;

		ComplexityRepair complexityRepairEntity = getComplRep(product.getComplexityRepair().getComplexity());
		if (complexityRepairEntity == null) {
			complexityRepairEntity = new ComplexityRepair();
			complexityRepairEntity.setComplexity(product.getComplexityRepair().getComplexity());
			complexityRepairEntity.setTime(product.getComplexityRepair().getTime());
			em.persist(complexityRepairEntity);
		}

		Product productEntity = new Product();
		productEntity.setName(product.getName());
		productEntity.setSerialNumber(product.getSerialNumber());
		productEntity.setWarranty(product.getWarranty());
		productEntity.setManufacturer(product.getManufacturer());
		productEntity.setModel(product.getModel());
		productEntity.setComplexityRepair(complexityRepairEntity);

		em.persist(productEntity);
		System.out.println("Product productEntity " + productEntity.toString());

		return true;
	}

	private Product getProductFind(String model, String manufacturer) {
		Query query = em.createQuery("SELECT p FROM Product p WHERE p.manufacturer = ?1 AND p.model = ?2");
		query.setParameter(1, manufacturer);
		query.setParameter(2, model);
		List<Product> res = query.getResultList();
		if (res == null || res.size() == 0)
			return null;
		return res.get(0);
	}

	/* ADD COMPLEXITY REPAIR */
	@Override
	@Transactional
	public boolean addComplexityRepair(ComplexityRepair complexityRepair) {
		ComplexityRepair complRepEntity = getComplRep(complexityRepair.getComplexity());
		if (complRepEntity == null) {
			complRepEntity = new ComplexityRepair(complexityRepair.getComplexity(), complexityRepair.getTime());
			em.persist(complRepEntity);
			return true;
		}
		return false;
	}

	private ComplexityRepair getComplRep(String complexity) {
		Query query = em.createQuery("SELECT c FROM ComplexityRepair c WHERE c.complexity = ?1");
		query.setParameter(1, complexity);
		System.out.println("getComplRep - " + complexity);
		List<ComplexityRepair> res = query.getResultList();
		System.out.println(" res getComplRep - " + res + " " + res.size());
		if (res == null || res.size() == 0)
			return null;
		return res.get(0);
	}

	/* ADD STATUS */
	@Override
	@Transactional
	public boolean addStatus(Status status) {
		if (status.getStatus().size() == getStatusSize())
			return false;

		Status statusEntity = em.find(Status.class, status.getId());
		if (statusEntity == null) {
			statusEntity = new Status(status.getStatus());
			em.persist(statusEntity);
			return true;
		}
		return false;
	}

	private int getStatusSize() {
		Query query = em.createQuery("SELECT s FROM Status s");
		List<Status> res = query.getResultList();
		if (res == null || res.size() == 0)
			return 0;
		return res.get(res.size() - 1).getStatus().size();
	}

	@Override
	@Transactional
	public boolean addRepiatRepair(RepiatRepair repiatRepair) {
		em.persist(repiatRepair);
		return true;
	}

	@Override
	@Transactional
	public boolean addHistory(History history) {
		em.persist(history);
		return true;
	}

	///////////////////////////////////////////////////////////////////////////////////
	@Override
	@Transactional
	public boolean updateReceipt(Receipt receipt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean updateProduct(Product product) {
		// em.refresh(productEntity);
		return true;
	}

	///////////////////////////////////////////////////////////////////////////////////
	@Override
	public Iterable<Receipt> getAllReceipt() {
		// TODO Auto-generated method stub
		return null;
	}

}
