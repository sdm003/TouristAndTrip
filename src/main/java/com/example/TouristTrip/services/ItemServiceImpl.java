package com.example.TouristTrip.services;
import com.example.TouristTrip.entity.Item;
import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.repository.AgreementRepository;
import com.example.TouristTrip.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;

    @Override
    public Message addItem(Item item) {
        itemRepository.save(item);
        return new Message("Item successfully added",item);
    }



    @Override
    public Item findById(Long id) {
       return itemRepository.findById(id).get();
    }


    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    @Override
    public String addImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String modifiedFileName = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
        Path path = Paths.get("C:\\Users\\User\\Pictures\\" + modifiedFileName);
        Files.write(path, bytes);
        return modifiedFileName;
    }
    @Override
    public Message updateImage(MultipartFile file,Long itemId ) throws IOException {
       Item item = itemService.findById(itemId);
        item.setImage(itemService.addImage(file));
        itemRepository.save(item);
        return new Message("Image has been successfully updated",item.getImage());
    }

}
