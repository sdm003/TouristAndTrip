package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Item;
import com.example.TouristTrip.model.Message;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface ItemService {
    Message addItem(Item item);

    Item findById(Long id);

    List<Item> getAllItems();

    String addImage(MultipartFile multipartFile) throws IOException;

    Message updateImage(MultipartFile multipartFile, Long itemId) throws IOException;

}
