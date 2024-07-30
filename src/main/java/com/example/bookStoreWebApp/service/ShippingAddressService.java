package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.dto.ShippingAddressDTO;
import com.example.bookStoreWebApp.model.ShippingAddress;

public interface ShippingAddressService {
    
	ShippingAddress saveAddress(ShippingAddress address);
    ShippingAddress findAddressById(int id);
    void deleteAddress(int id);
    void saveShippingAddress(ShippingAddressDTO addressDTO, int userId);
    ShippingAddressDTO findAddressByUserName(String userName);
    ShippingAddress findAddressByUserId(int userId);

}
