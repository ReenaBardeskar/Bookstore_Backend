package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.dto.ShippingAddressDTO;
import com.example.bookStoreWebApp.model.ShippingAddress;
import com.example.bookStoreWebApp.model.Users;
import com.example.bookStoreWebApp.repository.ShippingAddressRepository;
import com.example.bookStoreWebApp.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private UserRepository usersRepository;
    
    @Override
    public ShippingAddress saveAddress(ShippingAddress address) {
        return shippingAddressRepository.save(address);
    }

    @Override
    public ShippingAddress findAddressById(int id) {
        return shippingAddressRepository.findById(id).orElse(null);
    }
    
    @Override
    public ShippingAddress findAddressByUserId(int userId) {
        // Fetch the shipping address by UserID
        return shippingAddressRepository.findByUserId(userId).orElse(null);
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
    
    @Override
    public ShippingAddressDTO findAddressByUserName(String userName) {
        // Retrieve user by username
        Optional<Users> optionalUser = usersRepository.findByUsername(userName);

        if (optionalUser.isEmpty()) {
            return null; // User not found
        }

        Users user = optionalUser.get();
        // Retrieve address by user ID
        Optional<ShippingAddress> addressOptional = shippingAddressRepository.findByUserId(user.getUserId());

        return addressOptional.map(ShippingAddressDTO::new).orElse(null);
    }
}
