package com.journaldev.spring;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.dao.ProductDao;
import com.journaldev.model.Product;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {
	
	Path path;
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/")
	public String home()
	{
		return"home";
	}



	@RequestMapping("/productList")
	public String getProducts(Model model) {
		
		List<Product>  products = productDao.getAllProducts();//to see what is the 
		                               //use of this by commenting this line while
		                              //before running the application.
		                             //you stupid iska use use next line mein clearly
		                            //to get the object of product from the productList
		                    //ie from List<Product>.
		//Product product = productList.get(0);//as only one element and that is inserted
		                       //at the first position
		
		model.addAttribute("products",products);//iska significance and proper use dekhna hai.
		                         //this will attach the product object/model 
		                       //to productList view. ie we are doing nothing
		                      //but just binding the data(product object) from the 
		                    //productDao to the view(productList).
		
		return "productList";//see if with return only written productList than it 
		                //will ask to create  a local variable productList therefore
		                 //to write productList in "" it means kya String class 
		                //automatically imported hoti hai ie is the String class
		           //in lang package.
	}


/*	@RequestMapping("/productList/viewProduct/{productId}")
	public String viewProduct(@PathVariable String productId , Model model) throws IOException
	{
		Product product = productDao.getProductById(productId);
		model.addAttribute(product);
		//model.addAttribute("viewProduct",viewProduct);//no need
		return "viewProduct";//directly returning the view without any operation
	}*/
	
	
	

    @RequestMapping("/productList/viewProduct/{productId}")
    public String viewProduct(@PathVariable String productId, Model model) throws IOException{

        Product product = productDao.getProductById(productId);
        model.addAttribute(product);

        return "viewProduct";
    }
	
    @RequestMapping("/admin")//added after admin.jsp
    public String adminPage()
    {
    	return "admin";
    }
	
    
    @RequestMapping("/admin/productInventory")//added after productInventory.jsp
    public String productInventory(Model model)
    {
    	List<Product> products = productDao.getAllProducts();
    	model.addAttribute("products", products);
    	return "productInventory";
    }
    
    @RequestMapping("/admin/productInventory/addProduct")
    public String addProduct(Model model)
    {
    Product product = new Product();
    product.setProductCategory("instrument");//so that by default these radio buttons
                                          //comes selected
    product.setProductCondition("new");
   product.setProductStatus("active");
    
    model.addAttribute("product", product);
    return "addProduct";
    
    }
   
    @RequestMapping(value= "/admin/productInventory/addProduct",method=RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product,HttpServletRequest request)
    {
    	productDao.addProduct(product);
    	
    	MultipartFile productImage = product.getProductImage();
    	
    	String rootDirectory = request.getSession().getServletContext().getRealPath("/");
    	path=Paths.get(rootDirectory+"\\WEB-INF\\resources\\images"+product.getProductId()+".png");
    	
    	if(productImage!=null && !productImage.isEmpty()) {
    		try {
    			productImage.transferTo(new File(path.toString()));
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    			throw new RuntimeException("Product image saving failed",e);
    		}
    	}
    	return "redirect:/admin/productInventory";
    //	return "ProductInventory";
    }
    
    
    @RequestMapping("/admin/productInventory/deleteProduct/{id}")
    public String deleteProduct(@PathVariable String id, Model model,HttpServletRequest request){

    	String rootDirectory = request.getSession().getServletContext().getRealPath("/");
    	path=Paths.get(rootDirectory+"\\WEB-INF\\resources\\images"+id+".png");
    	
    	if(Files.exists(path)) {
    		try {
    			Files.delete(path);
    		}catch(IOException e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
        productDao.deleteProduct(id);
        return "redirect:/admin/productInventory";
    }
    
    
    @RequestMapping("/admin/productInventory/editProduct/{id}")
    public String editProduct(@PathVariable("id")String id, Model model){
    	Product product = productDao.getProductById(id);
    	model.addAttribute(product);
    	
    	return "editProduct";
    
    }
    
    
    @RequestMapping(value= "/admin/productInventory/editProduct",method=RequestMethod.POST)
    public String editProduct(@ModelAttribute("product")Product product,Model model,HttpServletRequest request)
    {
    MultipartFile productImage= product.getProductImage();
    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
    path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" +product.getProductId()+ ".png");
    
    
    if(productImage!=null && !productImage.isEmpty()) {
		try {
			productImage.transferTo(new File(path.toString()));
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Product image saving failed",e);
		}
	}
    
    
    productDao.editProduct(product);
    
    return "redirect:/admin/productInventory";
    }
}

