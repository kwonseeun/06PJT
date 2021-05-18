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
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })



//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
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
		product.setFileName("경기도");
		
		
		productService.addProduct(product);

		product = productService.getProduct(10001);

		//==> console 확인	
		//==> API 확인
		
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
		//==> 필요하다면...
	//		produt.setProdNo("testProdNo");
	//		product.setProdName("testProdName");
	//		product.setPrice("12345");

		
		product = productService.getProduct(10001);


		//==> console 확인	
		//==> API 확인

		/*
		 * Assert.assertEquals("홍길동", product.getProdName());
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
	//	Assert.assertEquals("경기도", product.getFileName());
		
		product.setProdName("이순신");
		product.setProdDetail("detail 바뀜");
		product.setManuDate("날짜 바뀜");
		product.setPrice(50000);
		product.setFileName("파일 바뀜");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10128);
		Assert.assertNotNull(product);
		
		//==> console 확인
		//System.out.println(product);
			
		//==> API 확인
	//	Assert.assertEquals("이순신", product.getProdName());
	//	Assert.assertEquals("detail 바뀜", product.getProdDetail());
	//	Assert.assertEquals("날짜 바뀜", product.getManuDate());
	//	Assert.assertEquals("금액 바뀜", product.getPrice());
	//	Assert.assertEquals("파일 바뀜", product.getFileName());
	 }
	 
	//@Test
	/*
	 * public void testCheckDuplication() throws Exception{
	 */
		//==> 필요하다면...
//		User user = new User();
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
//		
//		userService.addUser(user);
		
		//==> console 확인
		//System.out.println(userService.checkDuplication("testUserId"));
		//System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
	 	
		//==> API 확인
		/*
		 * Assert.assertFalse( productService.checkDuplication("testProdNo") );
		 * Assert.assertTrue(
		 * productService.checkDuplication("testProdNo"+System.currentTimeMillis()) );
		 * 
		 * }
		 */
	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
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
	 	
	 	//==> console 확인
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
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
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
	 	search.setSearchKeyword("자전거");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}