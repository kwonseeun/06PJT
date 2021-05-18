package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })



//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
	
		product.setProdName("testprodName");
		product.setProdDetail("testprodDetail");
		product.setManuDate("testManudate");
		product.setPrice(1000);
		product.setFileName("jpg");
		
		
		productService.addProduct(product);

		product = productService.getProduct(10001);

		//==> console Ȯ��	
		//==> API Ȯ��
		
		/*
		 * Assert.assertEquals("ProdNo", product.getProdNo());
		 * Assert.assertEquals("ProdName", product.getProdName());
		 * Assert.assertEquals("ProdDetail", product.getProdDetail());
		 * Assert.assertEquals("ManuDate", product.getManuDate());
		 * Assert.assertEquals("Price", product.getPrice());
		 * Assert.assertEquals("FileName", product.getFileName());
		 */
		 
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> �ʿ��ϴٸ�...
	//		produt.setProdNo("testProdNo");
	//		product.setProdName("testProdName");
	//		product.setPrice("12345");

		
		product = productService.getProduct(10001);


		//==> console Ȯ��	
		//==> API Ȯ��

		/*
		 * Assert.assertEquals("ȫ�浿", product.getProdName());
		 * Assert.assertEquals("detail", product.getProdDetail());
		 * Assert.assertEquals("2021-05-11", product.getManuDate());
		 * Assert.assertEquals("10,000,000", product.getPrice());
		 * Assert.assertEquals("jpg", product.getFileName());
		 * 
		 * Assert.assertNotNull(productService.getProduct(1));
		 */
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10128);
		Assert.assertNotNull(product);
		
	//	Assert.assertEquals("testprodName", product.getProdName());
	//	Assert.assertEquals("testprodDetail", product.getProdDetail());
	//	Assert.assertEquals(1000, product.getPrice());
	//	Assert.assertEquals("��⵵", product.getFileName());
		
		product.setProdName("�̼���");
		product.setProdDetail("detail �ٲ�");
		product.setManuDate("��¥ �ٲ�");
		product.setPrice(50000);
		product.setFileName("���� �ٲ�");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10128);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		//System.out.println(product);
			
		//==> API Ȯ��
	//	Assert.assertEquals("�̼���", product.getProdName());
	//	Assert.assertEquals("detail �ٲ�", product.getProdDetail());
	//	Assert.assertEquals("��¥ �ٲ�", product.getManuDate());
	//	Assert.assertEquals("�ݾ� �ٲ�", product.getPrice());
	//	Assert.assertEquals("���� �ٲ�", product.getFileName());
	 }
	 
	//@Test
	/*
	 * public void testCheckDuplication() throws Exception{
	 */
		//==> �ʿ��ϴٸ�...
//		User user = new User();
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");
//		
//		userService.addUser(user);
		
		//==> console Ȯ��
		//System.out.println(userService.checkDuplication("testUserId"));
		//System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
	 	
		//==> API Ȯ��
		/*
		 * Assert.assertFalse( productService.checkDuplication("testProdNo") );
		 * Assert.assertTrue(
		 * productService.checkDuplication("testProdNo"+System.currentTimeMillis()) );
		 * 
		 * }
		 */
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10001");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("������");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}