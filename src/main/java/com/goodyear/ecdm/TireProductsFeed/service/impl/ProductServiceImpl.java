package com.goodyear.ecdm.TireProductsFeed.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

/*    @Autowired
    private AuthAzureUtils authAzureUtils;

    @Autowired
    private RestTemplate restTemplate;*/
    
	@Override
	public List<Products> getProductsList(String webProductId) {
		List<Products> productsList = null;
		System.out.println("here");
		try {
			//AuthenticationResult result = authAzureUtils.getAccessTokenFromUserCredentials(clientId, clientSecret);	// This is for DigitasProductsFeedApplication2
			//AuthenticationResult result = AuthHelper.getAuthSessionObject(context); // This is for DigitasProductsFeedApplication
			
	    	//System.out.println("token: "+result.getAccessToken());
	    	//System.out.println("token: "+result.getExpiresOnDate());
			
			//HttpClient httpClient = HttpClientBuilder.create().build();
			 //RequestConfig.Builder reqConf = RequestConfig.custom();
	         //reqConf.setConnectTimeout(10000);            

	         //   HttpGet httpGet = new HttpGet("http://10.76.44.208:50001/api/product");
	            
	         //   httpGet.addHeader("Authorization", "Bearer "+result.getAccessToken());
	         //   httpGet.setConfig(reqConf.build());
	            
	         //   HttpResponse response1 = httpClient.execute(httpGet);
	         //   HttpEntity entity1 = response1.getEntity();
	         //   InputStream instream = entity1.getContent();
	    
	        //    StringBuilder sb = new StringBuilder();
	        //    BufferedReader r = new BufferedReader(new InputStreamReader(instream), 1000);
	        //    for (String line = r.readLine(); line != null; line = r.readLine()) {
	        //    	sb.append(line);
	        //    }
	        //    instream.close();
	        //    body = sb.toString();
	         //   System.out.println(body);
		
	            productsList = new ArrayList<Products>();
	            productsList.add(new Products("010800380108114016","13850","Endurance®","Endurance®"));
	            
	            String seturl = "https://goodyear-v81temp.kronos.net/wfc/XmlService";
	            
	            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	            CloseableHttpClient client 	= httpClientBuilder.build();
	            URIBuilder uriBuilderObj    = new URIBuilder(seturl);
	            HttpPost postMethod 		= new HttpPost(uriBuilderObj.build());
	            postMethod.setHeader("Content-Type", "application/xml");
	            
	            postMethod.setEntity(new StringEntity("<Kronos_WFC version=\"1.0\">\r\n" + 
	    				"	<Request Object=\"System\" Action=\"Logon\" Username=\"GoodyearAPI\" Password=\"huD1n!1019\"/>\r\n" + 
	    				"	<Request Action = \"CheckStatus\">\r\n" + 
	    				"		<PunchStatus>\r\n" + 
	    				"			<Employee>\r\n" + 
	    				"				<PersonIdentity PersonNumber = \"10021522\"/>\r\n" + 
	    				"			</Employee>\r\n" + 
	    				"		</PunchStatus>\r\n" + 
	    				"	</Request>\r\n" + 
	    				"	<Request Action = \"Load\">\r\n" + 
	    				"		<Schedule QueryDateSpan = \"07/01/2018-07/30/2018\">\r\n" + 
	    				"			<Employees>\r\n" + 
	    				"				<PersonIdentity PersonNumber = \"10021522\"/>\r\n" + 
	    				"			</Employees>\r\n" + 
	    				"		</Schedule>\r\n" + 
	    				"	</Request>\r\n" + 
	    				"	<Request Action =\"Logoff\" Object=\"System\"></Request>\r\n" + 
	    				"</Kronos_WFC>\r\n" + 
	    				""));
	    		CloseableHttpResponse response = client.execute(postMethod);
	    		HttpEntity entity	= response.getEntity();
	    		String responseBody = (entity == null) ? null : EntityUtils.toString(entity, "UTF-8");
	    		System.out.println(responseBody);
	    		
	    		StatusLine statusLine = response.getStatusLine();
	    		if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
	    			System.out.println("test"); 
	    		}
		} catch (Exception e) {
			logger.error(e.getMessage());
	    }
        return productsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listProductSku(boolean, int, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ProductSku> listProductSku(boolean pagination, int pageNumber, int pageSize, int webProductId,
			String controlFlags, String specAttributes) {
		
		List<ProductSku> listProductSkus	= new ArrayList<ProductSku>();
		
		System.out.println("Product Service Impl has been processing the request for listing the ProductSkus");
		
		return listProductSkus;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#getProductSkuByProductCode(java.lang.String, java.lang.String)
	 */
	@Override
	public ProductSku getProductSkuByProductCode(String prodCode, String specAttributes) {
		
		ProductSku productSku	= new ProductSku();
		
		System.out.println("Product Service Impl processing the request for 'Get Product SKU record by product code'");
		
		return productSku;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listProductLine(boolean, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ProductLine> listProductLine(boolean pagination, Integer pageNumber, Integer pageSize, String brandUid,
											String categoryTypes, String rebateActiveFrom, String rebateActiveTo, 
											Double rebateMailInAmount,Double rebateCreditCardAmount, 
											String warrantyTypes, String flags) {
		List<ProductLine> productLineList	= new ArrayList<ProductLine>();
		
		System.out.println("Product Service Impl is processing the request for 'List of Product Line Records'");
		
		return productLineList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#getProductLineByWebProductId(java.lang.Integer)
	 */
	@Override
	public ProductLine getProductLineByWebProductId(Integer webProductId) {
		
		ProductLine productLine	= new ProductLine();
		
		System.out.println("Product Service Impl is processing the request for 'Get ProductLine by Web Product Id");
		
		return productLine;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listProductLineBrandsById(java.lang.String)
	 */
	@Override
	public List<Brand> listProductLineBrandsById(String uid) {
		
		List<Brand> productLineBrandsList	= new ArrayList<Brand>();
		
		System.out.println("Product Service Impl is processing the request for 'Get all ProductLine Brands by Uid'");
		
		return productLineBrandsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listFeatureBenefit()
	 */
	@Override
	public List<FeatureBenefit> listFeatureBenefit() {
		
		List<FeatureBenefit> featureBenefitList	= new ArrayList<FeatureBenefit>();
		
		System.out.println("Product Service Impl is processing the request for list all feature benefits");
		
		return featureBenefitList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listProductLineDisclaimers()
	 */
	@Override
	public List<Disclaimer> listProductLineDisclaimers() {
		
		List<Disclaimer> productLineDisclaimersList	= new ArrayList<Disclaimer>();

		System.out.println("Product Service Impl is processing the request for list all product line disclaimers");

		return productLineDisclaimersList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listProductLineCategories()
	 */
	@Override
	public List<Category> listProductLineCategoriesByType(String type) {

		List<Category> productLineCategoryList	= new ArrayList<Category>();
		
		System.out.println("Product Service Impl is processing the request for list all product line Categories by Type");
		
		return productLineCategoryList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listProductLineRebates(java.lang.String, java.lang.String, java.lang.Double, java.lang.Double)
	 */
	@Override
	public List<Rebate> listProductLineRebates(String activeFrom, String activeTo, Double mailInAmount,
			Double creditCardAmount) {
		List<Rebate> listProductLineRebates	= new ArrayList<Rebate>();
		
		System.out.println("Product Service Impl is processing the request for list all product line Rebates");
		
		return listProductLineRebates;
	}

	/*
	 * (non-Javadoc)
	 * @see com.goodyear.ecdm.TireProductsFeed.service.ProductService#listProductLineWarranties(java.lang.String)
	 */
	@Override
	public List<Warranty> listProductLineWarranties(String type) {
		List<Warranty> listProductLineWarranties	= new ArrayList<Warranty>();
		
		System.out.println("Product Service Impl is processing the request for list all product line Warranties by type");
		
		return listProductLineWarranties;
	}

}
