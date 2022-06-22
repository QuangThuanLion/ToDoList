package com.todolist.service.implement;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.dto.ProductRequest;
import com.todolist.entity.Product;
import com.todolist.entity.Product_;
import com.todolist.repository.ProductRepository;
import com.todolist.service.ProductService;

/**
 * @author admin
 *
 */
@Service(value = "ProductServiceImpl")
@Transactional(rollbackFor = Exception.class)
@Primary
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 *
	 */
	@Override
	public Product createProduct(final Product product) {
		final Product savedProduct = productRepository.save(product);
		return savedProduct.getId() != null ? savedProduct : null;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findAll() {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		criteriaQuery.select(root);
		
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByByNameProductIn(final String... params) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(root)
					.where(root.get("name")
					.in(params[0], params[1]));
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductPriceGreaterThan(final Integer price) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		//criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("price"), price));
		criteriaQuery.select(root)
					.where(criteriaBuilder
					.greaterThan(root.get("price"), price));
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductPriceLessThan(final Integer price) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(root)
					.where(criteriaBuilder
					.lessThan(root.get("price"), price));
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductLike(final String name) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(root)
					.where(criteriaBuilder
					.like(root.get(Product_.name), "%"+name+"%"));
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductBetween(final Integer... params) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(root)
					.where(criteriaBuilder
					.between(root.get(Product_.price), params[0], params[1]));
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductIsNull() {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(root)
					.where(criteriaBuilder
					.isNull(root.get(Product_.description)));
		
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductIsNotNull() {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(root)
					.where(criteriaBuilder
					.isNotNull(root.get(Product_.description)));
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductIsChainExpression(final String like) {
		//ket hop nhieu dieu kien tren 1 cau query lai voi nhau
		//su dung predicates cua criteria in JPA
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		final Predicate[] predicates = new Predicate[2];
		predicates[0] = criteriaBuilder.isNotNull(root.get(Product_.description));
		predicates[1] = criteriaBuilder.like(root.get(Product_.name), "%"+like+"%");
		criteriaQuery.select(root).where(predicates);
		
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductIsChainExpression(final Integer productPrice, final String name) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		final Predicate greaterThanPrice = criteriaBuilder.greaterThan(root.get(Product_.price), productPrice);
		final Predicate likeProductName = criteriaBuilder.like(root.get(Product_.name), "%"+name+"%");
		
		//expression and
		criteriaQuery.select(root).where(criteriaBuilder.and(greaterThanPrice, likeProductName));
		
		//expression or
		criteriaQuery.select(root).where(criteriaBuilder.or(greaterThanPrice, likeProductName));
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		
		final List<Product> products = typedQuery.getResultList();
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductIsOrdering() {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(root);
		
		//giam dan theo thu tu price
		//criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Product_.price)));
		
		//tang dan theo thu tu price
		//criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Product_.price)));
		
		//giam dan theo thu tu price va tang dan theo name
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Product_.price)), criteriaBuilder.asc(root.get(Product_.name)));
		
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}

	/**
	 *
	 */
	@Override
	public List<Product> findByProductIsGroupBy() {
		//can khoi tao constructor de binding dulieu khi thuc hien multiselect
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.multiselect(
				root.get(Product_.id),
				root.get(Product_.name), 
				root.get(Product_.description),
				criteriaBuilder.sum(root.get(Product_.price)));
		
//		criteriaQuery.select(criteriaBuilder.construct(Product.class, 
//				root.get(Product_.name), 
//				root.get(Product_.description),
//				criteriaBuilder.sum(root.get(Product_.price))));
		//q.select(cb.construct(AuthorValue.class, root.get(Author_.firstName), root.get(Author_.lastName)));
		
		criteriaQuery.where(criteriaBuilder.greaterThan(root.get(Product_.price), 400));
		criteriaQuery.groupBy(root.get(Product_.name));
		criteriaQuery.having(criteriaBuilder.like(root.get(Product_.name), "%Brand%"));
		criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(root.get(Product_.price))));
		
		final TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Product> products = typedQuery.getResultList();
		
		return products;
	}
	
	/**
	 *
	 */
	@Override
	public Long findByProductIsAggregatesCount() {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		final TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		final Long count = typedQuery.getSingleResult();
		return count;
	}

	/**
	 *
	 */
	@Override
	public Long findByProductIsAggregatesAvg() {
		//su dung expression de thuc hien khai bao thay vi truyen trong 1 cau lenh nhu duoi.
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		final Root<Product> root = criteriaQuery.from(Product.class);
		
		//criteriaQuery.select(criteriaBuilder.avg(root.get(Product_.price)));
		
		final Expression<Double> expression = criteriaBuilder.avg(root.get(Product_.price));
		criteriaQuery.select(expression);
		
		final TypedQuery<Double> typedQuery = entityManager.createQuery(criteriaQuery);
		final Double avgDouble = typedQuery.getSingleResult();
		
		return avgDouble.longValue();
	}

	/**
	 * @throws Exception 
	 *
	 */
	@Override
	public ProductRequest updateProduct(ProductRequest productRequest) throws Exception {
		Optional.ofNullable(productRequest.checkNullProductRequest())
				.orElseThrow(() -> new IllegalArgumentException("Item request is null"));
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaUpdate<Product> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Product.class);
		final Root<Product> root = criteriaUpdate.from(Product.class);
		
		criteriaUpdate.set(root.get(Product_.name), productRequest.getProductName());
		criteriaUpdate.set(root.get(Product_.price), productRequest.getProductPrice());
		criteriaUpdate.set(root.get(Product_.description), productRequest.getDescription());
		criteriaUpdate.where(criteriaBuilder.equal(root.get(Product_.id), productRequest.getId()));	
		
		final Optional<Integer> countUpdate = Optional.ofNullable(entityManager.createQuery(criteriaUpdate).executeUpdate());
		return countUpdate.stream().count() > 0 ? productRequest : null;
	}

	/**
	 *
	 */
	@Override
	public void deleteProductById(Integer productId) throws Exception{
		Optional.ofNullable(productId)
				.orElseThrow(() -> new IllegalArgumentException("ProductId required not null"));
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaDelete<Product> criteriaDelete = criteriaBuilder.createCriteriaDelete(Product.class);
		final Root<Product> root = criteriaDelete.from(Product.class);
		
		final Expression<Boolean> expression = criteriaBuilder.equal(root.get(Product_.id), productId);
		criteriaDelete.where(expression);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}
	
	/**
	 *
	 */
	@Override
	public void savedProduct(Product product) {
		entityManager.persist(product);
	}
}
