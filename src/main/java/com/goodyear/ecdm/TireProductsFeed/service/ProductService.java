package com.goodyear.ecdm.TireProductsFeed.service;

import java.util.List;

import com.goodyear.ecdm.TireProductsFeed.model.Brand;
import com.goodyear.ecdm.TireProductsFeed.model.Category;
import com.goodyear.ecdm.TireProductsFeed.model.Disclaimer;
import com.goodyear.ecdm.TireProductsFeed.model.FeatureBenefit;
import com.goodyear.ecdm.TireProductsFeed.model.ProductLine;
import com.goodyear.ecdm.TireProductsFeed.model.ProductSku;
import com.goodyear.ecdm.TireProductsFeed.model.Products;
import com.goodyear.ecdm.TireProductsFeed.model.Rebate;
import com.goodyear.ecdm.TireProductsFeed.model.Warranty;

public interface ProductService {
	public List<Products> getProductsList (String webProductId);

	/**
	 * 
	 * @param pagination enable/disable pagination default false
	 * @param pageNumber
	 * @param pageSize
	 * @param webProductId
	 * @param controlFlags
	 * @param specAttributes
	 * @return
	 */
	public List<ProductSku> listProductSku( boolean pagination, 
								     int pageNumber,
								     int pageSize, 
								     int webProductId, 
								     String controlFlags, 
								     String specAttributes);

	/**
	 * Get product SKU record by product code
	 * @param prodCode
	 * @param specAttributes
	 * @return
	 */
	public ProductSku getProductSkuByProductCode(String prodCode, String specAttributes);


	/**
	 * 
	 * @param pagination
	 * @param pageNumber
	 * @param pageSize
	 * @param brandUid
	 * @param categoryTypes
	 * @param rebateActiveFrom
	 * @param rebateActiveTo
	 * @param rebateMailInAmount
	 * @param rebateCreditCardAmount
	 * @param warrantyTypes
	 * @param flags
	 * @return
	 */
	public List<ProductLine> listProductLine(boolean pagination, 
											 Integer pageNumber, 
											 Integer pageSize, 
											 String brandUid,
											 String categoryTypes, 
											 String rebateActiveFrom, 
											 String rebateActiveTo, 
											 Double rebateMailInAmount,
											 Double rebateCreditCardAmount, 
											 String warrantyTypes, 
											 String flags);

	/**
	 * 
	 * @param webProductId
	 * @return
	 */
	public ProductLine getProductLineByWebProductId(Integer webProductId);

	/**
	 * 
	 * @param uid
	 * @return
	 */
	public List<Brand> listProductLineBrandsById(String uid);

	/**
	 * 
	 * @return
	 */
	public List<FeatureBenefit> listFeatureBenefit();

	/**
	 * 
	 * @return
	 */
	public List<Disclaimer> listProductLineDisclaimers();

	/**
	 * 
	 * @param type 
	 * @return
	 */
	public List<Category> listProductLineCategoriesByType(String type);

	/**
	 * 
	 * @param activeFrom
	 * @param activeTo
	 * @param mailInAmount
	 * @param creditCardAmount
	 * @return
	 */
	public List<Rebate> listProductLineRebates(String activeFrom, String activeTo, Double mailInAmount, Double creditCardAmount);

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Warranty> listProductLineWarranties(String type);
}
