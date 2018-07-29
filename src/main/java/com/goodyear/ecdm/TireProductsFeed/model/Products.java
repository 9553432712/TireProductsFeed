package com.goodyear.ecdm.TireProductsFeed.model;

import io.swagger.annotations.ApiModelProperty;

public class Products {
	public Products () {
		
	}
	
	public Products(String product_hierarchy, String webprodid, String prodline_name, String webprodline) {
        super();
        this.product_hierarchy = product_hierarchy;
        this.webprodid = webprodid;
        this.prodline_name = prodline_name;
        this.webprodline = webprodline;
    }
	
	@ApiModelProperty(notes = "Product Hierarchy")
	private String product_hierarchy;
	
	@ApiModelProperty(notes = "WebProdId")
    private String webprodid;
	
	@ApiModelProperty(notes = "Product Line Name")
    private String prodline_name;
	
	@ApiModelProperty(notes = "Web Product Line Name")
    private String webprodline;
     
    public String getProduct_hierarchy() {
		return product_hierarchy;
	}

	public void setProduct_hierarchy(String product_hierarchy) {
		this.product_hierarchy = product_hierarchy;
	}

	public String getWebprodid() {
		return webprodid;
	}

	public void setWebprodid(String webprodid) {
		this.webprodid = webprodid;
	}

	public String getProdline_name() {
		return prodline_name;
	}

	public void setProdline_name(String prodline_name) {
		this.prodline_name = prodline_name;
	}

	public String getWebprodline() {
		return webprodline;
	}

	public void setWebprodline(String webprodline) {
		this.webprodline = webprodline;
	}	
 
    @Override
    public String toString() {
        return "Products [product_hierarchy=" + product_hierarchy + ", webprodid=" + webprodid
                + ", prodline_name=" + prodline_name + ", webprodline=" + webprodline + "]";
    }
}

