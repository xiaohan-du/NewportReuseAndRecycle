package ase.newportreuseandrecycle.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {

    @Override
    public List<ListingDto> getListings() {
        ListingDto testProduct1 = new ListingDto("Product 1", "Lorem ipsum", 19.99, "http://pamsdailydish.com/wp-content/uploads/2015/04/Bunch-Bananas-2.jpg");
        ListingDto testProduct2 = new ListingDto("Product 2", "some description", 5.99, "http://pamsdailydish.com/wp-content/uploads/2015/04/Bunch-Bananas-2.jpg");
        ListingDto testProduct3 = new ListingDto("Product 3", "abcdefghijklmnop", 2.50, "http://pamsdailydish.com/wp-content/uploads/2015/04/Bunch-Bananas-2.jpg");
        ListingDto testProduct4 = new ListingDto("Product 4", "123456789", 10.00, "http://pamsdailydish.com/wp-content/uploads/2015/04/Bunch-Bananas-2.jpg");

        List<ListingDto> listingDtos = List.of(testProduct1, testProduct2, testProduct3, testProduct4);
        return listingDtos;
    }

}
