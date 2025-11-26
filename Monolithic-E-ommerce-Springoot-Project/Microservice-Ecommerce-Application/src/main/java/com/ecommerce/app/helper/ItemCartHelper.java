package com.ecommerce.app.helper;

import com.ecommerce.app.request.CreateItemCartRequest;
import com.ecommerce.app.entity.ItemCart;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.service.ItemCartService;
import com.ecommerce.app.service.ProductService;
import com.ecommerce.app.service.UserService;
import com.ibm.icu.text.RuleBasedNumberFormat;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

@Component
public class ItemCartHelper {

   ItemCartService itemCartService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public ItemCartHelper(ItemCartService itemCartService) {
        this.itemCartService = itemCartService;
    }
    public String addItemToCart(CreateItemCartRequest createItemCartRequest) {
        ItemCart existingItemCart = itemCartService.findByUserIdAndProductIdAndActiveTrueAndDeletedFalse(createItemCartRequest.getUserId(),
                createItemCartRequest.getProductId());
        Product product = productService.findProductById(createItemCartRequest.getProductId());
        User user = userService.findByIdAndActiveTrueAndDeletedFalse(createItemCartRequest.getUserId());
        ItemCart deletedItemCart=  itemCartService. findByUserIdAndProductIdAndDeletedTrueAndActiveFalse(createItemCartRequest.getUserId(),createItemCartRequest.getProductId());

        if (product==null && user!=null) {
            throw new RuntimeException("Product is not found in existing db.");
        }
        ItemCart newItemCart = null;
        assert product != null;
        if (product.getStockQuantity() <= 0) {

        throw  new RuntimeException("This product is currently out of stock — we’ll notify you once it is restocked.");
        }
        else if (product.getStockQuantity()<createItemCartRequest.getQuantity()) {
            RuleBasedNumberFormat nf =
                    new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.SPELLOUT);
            throw  new RuntimeException("Only"+" " + convertNumberToWord(product.getStockQuantity())+"("+product.getStockQuantity()+")" + " "+"available in stock.");
        }
        else {
            if (existingItemCart != null) {
                BigDecimal addPriceForExistingProductInItemCart = product.getPrice().multiply
                                (BigDecimal.valueOf(createItemCartRequest.getQuantity()))
                        .add(existingItemCart.getPrice());
                existingItemCart.setPrice(addPriceForExistingProductInItemCart);
                existingItemCart.setQuantity(existingItemCart.getQuantity() + createItemCartRequest.getQuantity());
                existingItemCart.setUpdatedOn(LocalDateTime.now());
                itemCartService.addItemTocCart(existingItemCart);
            } else if (deletedItemCart!=null) {
                deletedItemCart.setQuantity(createItemCartRequest.getQuantity());
                BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(createItemCartRequest.getQuantity()));
                deletedItemCart.setPrice(totalPrice);
                deletedItemCart.setDeleted(false);
                deletedItemCart.setActive(true);
                deletedItemCart.setUpdatedOn(LocalDateTime.now());
                itemCartService.addItemTocCart(deletedItemCart);
            } else {
                newItemCart = new ItemCart();
                newItemCart.setQuantity(createItemCartRequest.getQuantity());
                newItemCart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(createItemCartRequest.getQuantity())));
                newItemCart.setCreatedOn(LocalDateTime.now());
                newItemCart.setUser(user);
                newItemCart.setProduct(product);
                itemCartService.addItemTocCart(newItemCart);
            }

        }
            return String.valueOf("Products have been added to your cart successfully.");

        }


    public static String convertNumberToWord(int number) {
        RuleBasedNumberFormat nf =
                new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.SPELLOUT);
        return nf.format(number);
    }

    @Transactional
    public String removeItemToCart(CreateItemCartRequest createItemCartRequest) {
        ItemCart existingItemItemCart = itemCartService.findByUserIdAndProductIdAndActiveTrueAndDeletedFalse(createItemCartRequest.getUserId(), createItemCartRequest.getProductId());
        Product product = productService.findProductById(createItemCartRequest.getProductId());
        User user = userService.findByIdAndActiveTrueAndDeletedFalse(createItemCartRequest.getUserId());
        if (existingItemItemCart != null) {
            {
                if (existingItemItemCart.getQuantity() > 1 && !existingItemItemCart.getQuantity().equals(createItemCartRequest.getQuantity())) {
                    int itemAvailableInCart = existingItemItemCart.getQuantity() - createItemCartRequest.getQuantity();
                    existingItemItemCart.setQuantity(itemAvailableInCart);
                    BigDecimal actualTotalPrice = existingItemItemCart.getPrice();
                    BigDecimal decreasedTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(createItemCartRequest.getQuantity()));
                    existingItemItemCart.setPrice(actualTotalPrice.subtract(decreasedTotalPrice));
                    existingItemItemCart.setUpdatedOn(LocalDateTime.now());
                    itemCartService.addItemTocCart(existingItemItemCart);
                }
                else if (existingItemItemCart.getQuantity() == 1 || existingItemItemCart.getQuantity().equals(createItemCartRequest.getQuantity())) {
                    int itemAvailableInCart = existingItemItemCart.getQuantity() - createItemCartRequest.getQuantity();
                    existingItemItemCart.setQuantity(itemAvailableInCart);
                    BigDecimal actualTotalPrice = existingItemItemCart.getPrice();
                    BigDecimal decreasedTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(createItemCartRequest.getQuantity()));
                    existingItemItemCart.setPrice(actualTotalPrice.subtract(decreasedTotalPrice));
                    existingItemItemCart.setDeleted(true);
                    existingItemItemCart.setActive(false);
                    existingItemItemCart.setUpdatedOn(LocalDateTime.now());
                    itemCartService.addItemTocCart(existingItemItemCart);
                }
                else {
                    throw new RuntimeException("Check your payload request and validate the input data.");
                }
            }
        }
        else {
            throw new RuntimeException("Item is not found in cart.");
        }
        return  "Item has been remove successfully from user cart.";
    }
}
