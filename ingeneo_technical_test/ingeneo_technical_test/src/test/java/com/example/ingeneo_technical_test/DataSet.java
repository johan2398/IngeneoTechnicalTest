/**
 * 
 */
package com.example.ingeneo_technical_test;

import com.example.ingeneo_technical_test.entity.dto.ClientDTO;
import com.example.ingeneo_technical_test.entity.dto.ProductDTO;
import com.example.ingeneo_technical_test.entity.dto.WarehouseDTO;
import com.example.ingeneo_technical_test.entity.dto.ShipDTO;

import com.example.ingeneo_technical_test.entity.dto.converter.WarehouseDTOConverter;

import java.util.Date;

import com.example.ingeneo_technical_test.entity.Client;
import com.example.ingeneo_technical_test.entity.Product;
import com.example.ingeneo_technical_test.entity.Warehouse;
import com.example.ingeneo_technical_test.entity.Ship;
import com.example.ingeneo_technical_test.entity.Discount;
import com.example.ingeneo_technical_test.entity.User;
import com.example.ingeneo_technical_test.enumerations.ProductType;
import com.example.ingeneo_technical_test.enumerations.ShipState;
import com.example.ingeneo_technical_test.enumerations.WarehouseType;

/**
 * @author Johan Casagua
 *
 */
public class DataSet {

	
	public static ClientDTO getClient() {
		var clientDTO = new ClientDTO();
		clientDTO.setName("Johan");
		clientDTO.setLastName("Casagua");
		clientDTO.setEmail("joseca@hotmail.com");
		clientDTO.setAddress("Av si");
		return clientDTO;
	}
	
	public static ProductDTO getProduct() {
		var productDTO = new ProductDTO();
		productDTO.setName("PC Asus");
		productDTO.setPrice(3200000f);
		productDTO.setDescription("Portable Computer Asus 8GB RAM Core I5 7th Gen");
		productDTO.setQuantity(50);
		productDTO.setType(ProductType.TECHNOLOGY);
		return productDTO;
	}
	
	public static WarehouseDTO getWarehouse() {
		var wareHouseDTO = new WarehouseDTO();
		wareHouseDTO.setLocation("Cartagena Port");
		wareHouseDTO.setStorageCapacity("30 Toneladas");
		wareHouseDTO.setName("Main port");
		wareHouseDTO.setPort(null);
		wareHouseDTO.setStore(null);
		wareHouseDTO.setType(WarehouseType.PORT);
		return wareHouseDTO;
	}
	
	public static ShipDTO getShip() {
        var shipDTO = new ShipDTO();

        // Datos de prueba
        shipDTO.setId(1L);
        Product product = new Product();
        shipDTO.setProduct(product);
        shipDTO.setRegistryDate(new Date());
        shipDTO.setDeliveryDate(new Date());
        shipDTO.setShippingPrice(100.0f);
        shipDTO.setGuideNumber("ABC123");
        Client client = new Client();
        shipDTO.setClient(client);
        Warehouse warehouse = WarehouseDTOConverter.convertToEntity(getWarehouse());
        shipDTO.setWarehouse(warehouse);
        Discount discount = new Discount();
        shipDTO.setDiscount(discount);
        shipDTO.setState(ShipState.REGISTRIED);
        shipDTO.setShippingPriceWithDiscount(90.0f);

        return shipDTO;
    }
	
	public static User getUserEntity() {
		var user = new User();
		user.setName("Johan");
		user.setPassword("johansito123");
		user.setEmail("johan@casagua");
		user.setUserName("johan");
		return user;
	}
}

