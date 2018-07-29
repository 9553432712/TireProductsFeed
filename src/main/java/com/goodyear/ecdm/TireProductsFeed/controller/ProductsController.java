package com.goodyear.ecdm.TireProductsFeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.goodyear.ecdm.TireProductsFeed.model.Brand;
import com.goodyear.ecdm.TireProductsFeed.model.Category;
import com.goodyear.ecdm.TireProductsFeed.model.Disclaimer;
import com.goodyear.ecdm.TireProductsFeed.model.FeatureBenefit;
import com.goodyear.ecdm.TireProductsFeed.model.ProductLine;
import com.goodyear.ecdm.TireProductsFeed.model.ProductSku;
import com.goodyear.ecdm.TireProductsFeed.model.Products;
import com.goodyear.ecdm.TireProductsFeed.model.Rebate;
import com.goodyear.ecdm.TireProductsFeed.model.Warranty;
import com.goodyear.ecdm.TireProductsFeed.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping({"/v1.0", "/v1.1"})
	@ApiOperation(value = "View a list of available products",response = Iterable.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
	)
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<Products> listProducts() { 
		System.out.println("Request came for List Products..");
        List<Products> productsList = productService.getProductsList("1");
        
        return productsList;
    }
	@RequestMapping(value = "/product/v1.0/mahesh", method = RequestMethod.GET, produces = "application/json")
    public List<Products> listProducts1() { 
		System.out.println("Request came for List Products..");
        List<Products> productsList = productService.getProductsList("1");
        
        return productsList;
    }
	@RequestMapping(value = "/product/v1.0/suresh", method = RequestMethod.GET, produces = "application/json")
    public List<Products> listProducts2() { 
		System.out.println("Request came for List Products..");
        List<Products> productsList = productService.getProductsList("1");
        
        return productsList;
    }
	@RequestMapping(value = "/product/v1.0/aravind", method = RequestMethod.GET, produces = "application/json")
    public List<Products> listProducts3() { 
		System.out.println("Request came for List Products..");
        List<Products> productsList = productService.getProductsList("1");
        
        return productsList;
    }
	
/*	@GetMapping({"/v1.2"})
	@ApiOperation(value = "View a list of available products New",response = Iterable.class)
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<Products> listProductsNew() {      
        List<Products> productsList = productService.getProductsList("1");
        
        return productsList;
    }*/ 
    
	@ApiOperation(value = "View product with the specified ID",response = Iterable.class)
    @RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
//    public List<Products> showProduct(@PathVariable String id, Model model){
	public List<Products> showProduct(@PathVariable String id){
		System.out.println("Request has come with Proudct Id: " + id);
		
    	List<Products> productsList = productService.getProductsList(id);
    	
    	return productsList;
    }
	
	/**
	 * 
	 * @param pagination
	 * @param pageNumber
	 * @param pageSize
	 * @param webProductId
	 * @param controlFlags
	 * @param specAttributes
	 * @return
	 */
	@ApiOperation(value = "Get list of product SKU records",response = Iterable.class)
    @RequestMapping(value="/Sku", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<ProductSku> listProductSku(@RequestParam(value="pagination") boolean pagination, 
										   @RequestParam(value="pageNumber") int pageNumber,
										   @RequestParam(value="pageSize") int pageSize, 
										   @RequestParam(value="webProductId") int webProductId, 
										   @RequestParam(value="controlFlags") String controlFlags, 
										   @RequestParam(value="specAttributes") String specAttributes){
		
		System.out.println("Request has come to 'Product Controller' for lis produce Skus ");
		
    	List<ProductSku> productSkuList = productService.listProductSku(pagination, pageNumber, pageSize, 
    																	webProductId, controlFlags,specAttributes);
    	
    	return productSkuList;
    }
	
	/**
	 * 
	 * @param prodCode
	 * @param specAttributes
	 * @return
	 */
	@ApiOperation(value = "Get product SKU record by product code",response = ProductSku.class)
    @RequestMapping(value="/Sku/{prodCode}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public ProductSku getProductSkuByProductCode(@PathVariable String prodCode, @RequestParam(value="specAttributes") String specAttributes){
		
		System.out.println("Request has come to 'Product Controller' for ProductSku record ");
		
    	ProductSku productSku = productService.getProductSkuByProductCode(prodCode, specAttributes);
    	
    	return productSku;
    }
	
	
	/**
	 * 
	 * @param prodCode
	 * @param specAttributes
	 * @return
	 */
	@ApiOperation(value = "Get list of product Line records",response = Iterable.class)
    @RequestMapping(value="/Line", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<ProductLine> listProductLine(@RequestParam(value="pagination") boolean pagination, 
			   @RequestParam(value="pageNumber") Integer pageNumber,
			   @RequestParam(value="pageSize") Integer pageSize, 
			   @RequestParam(value="brandUid") String brandUid, 
			   @RequestParam(value="categoryTypes") String categoryTypes, 
			   @RequestParam(value="rebateActiveFrom") String rebateActiveFrom,
			   @RequestParam(value="rebateActiveTo") String rebateActiveTo, 
			   @RequestParam(value="rebateMailInAmount") Double rebateMailInAmount, 
			   @RequestParam(value="rebateCreditCardAmount") Double rebateCreditCardAmount,
			   @RequestParam(value="warrantyTypes") String warrantyTypes, 
			   @RequestParam(value="flags") String flags) {
		
		System.out.println("Request has come to 'Product Controller' for listing 'ProductLines' ");
		
		List<ProductLine> listProductLine = productService.listProductLine(pagination,  pageNumber, pageSize, 
				   brandUid, categoryTypes, rebateActiveFrom, rebateActiveTo,  rebateMailInAmount, 
				   rebateCreditCardAmount, warrantyTypes,  flags);
		
    	
    	return listProductLine;
    }
	
	/**
	 * 
	 * @param webProductId
	 * @return
	 */
	@ApiOperation(value = "Get product Line record by web product id",response = ProductLine.class)
    @RequestMapping(value="/Line/{webProductId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public ProductLine getProductLineByWebProductId(@PathVariable(value = "webProductId") Integer webProductId) {
		
		System.out.println("Request has come to 'Product Controller' for ProductSku record ");
		
		ProductLine productLine = productService.getProductLineByWebProductId(webProductId);
    	
    	return productLine;
    }
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	@ApiOperation(value = "Gets all product Line brands",response = Iterable.class)
    @RequestMapping(value="/Line/Brand", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<Brand> listProductLineBrandsById(@RequestParam(value="uid") String uid) {
		
		System.out.println("Request has come to 'Product Controller' for listing 'ProductLines Brands by Id' ");
		
		List<Brand> productLineBrandList = productService.listProductLineBrandsById(uid);
		
		Brand brand = new Brand();
		brand.setBrandId(new Integer(45));
		productLineBrandList.add(brand);
		
    	
    	return productLineBrandList;
    }

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "Gets all product Line features and benefits",response = Iterable.class)
    @RequestMapping(value="/Line/FeatureBenefit", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<FeatureBenefit> listFeatureBenefit() {
		
		System.out.println("Request has come to 'Product Controller' for listing 'FeatureBenefit' ");
		
		List<FeatureBenefit> featureBenefitList = productService.listFeatureBenefit();
		
    	
    	return featureBenefitList;
    }
	
	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "Gets all product Line disclaimers",response = Iterable.class)
    @RequestMapping(value="/Line/Disclaimer", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<Disclaimer> listProductLineDisclaimers() {
		
		System.out.println("Request has come to 'Product Controller' for listing Disclaimers ");
		
		List<Disclaimer> productLineDisclaimersList = productService.listProductLineDisclaimers();
		
    	
    	return productLineDisclaimersList;
    }
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "Gets all product Line categories",response = Iterable.class)
    @RequestMapping(value="/Line/Category", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<Category> listProductLineCategoriesByType(@RequestParam(value="type") String type) {
		
		System.out.println("Request has come to 'Product Controller' for listing Categories by Type");
		
		List<Category> productLineCategoriesList = productService.listProductLineCategoriesByType(type);
		
    	
    	return productLineCategoriesList;
    }
	
	
	@ApiOperation(value = "Gets all product Line rebates",response = Iterable.class)
    @RequestMapping(value="/Line/Rebate", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<Rebate> listProductLineRebates(@RequestParam(value="activeFrom") String activeFrom, 
			   @RequestParam(value="activeTo") String activeTo,
			   @RequestParam(value="mailInAmount") Double mailInAmount, 
			   @RequestParam(value="creditCardAmount") Double creditCardAmount) {
		
		System.out.println("Request has come to 'Product Controller' for listing 'ProductLines' ");
		
		List<Rebate> listProductLineRebates = productService.listProductLineRebates(activeFrom, activeTo, mailInAmount, creditCardAmount);
		
    	
    	return listProductLineRebates;
    }
	
	@ApiOperation(value = "Gets all product Line warranties",response = Iterable.class)
    @RequestMapping(value="/Line/Warranty", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
	public List<Warranty> listProductLineWarranties(@RequestParam(value="type") String type) {
		
		System.out.println("Request has come to 'Product Controller' for listing Product line Warranties ");
		
		List<Warranty> listProductLineWarranties = productService.listProductLineWarranties(type);
		
    	
    	return listProductLineWarranties;
    }
}