package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.dto.ShippingAddressDTO;
import com.example.bookStoreWebApp.model.ShippingAddress;
import com.example.bookStoreWebApp.repository.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddress saveAddress(ShippingAddress address) {
        return shippingAddressRepository.save(address);
    }

    @Override
    public ShippingAddress findAddressById(int id) {
        return shippingAddressRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAddress(int id) {
        shippingAddressRepository.deleteById(id);
    }

    @Override
    public void saveShippingAddress(ShippingAddressDTO addressDTO, int userId) {
        ShippingAddress address = new ShippingAddress();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setUserId(userId);

        saveAddress(address);
    }
}
