package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.FoodOrderDAO;
import com.seatscape.seatscape.dao.TicketDAO;
import com.seatscape.seatscape.exceptions.FoodOrderAlreadyExistsException;
import com.seatscape.seatscape.exceptions.FoodOrderEmptyException;
import com.seatscape.seatscape.exceptions.TicketDoesNotExistException;
import com.seatscape.seatscape.model.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService {

    @Autowired
    private FoodOrderDAO foodOrderDAO;

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private TicketDAO ticketDAO;

    public ResponseEntity<List<FoodOrder>> getAllOrders() {
        try {
            return new ResponseEntity<>(foodOrderDAO.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<FoodOrder>> getFoodOrderById(Integer id) {
        try {
            Optional<FoodOrder> order = foodOrderDAO.findById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<FoodOrder>> getFoodOrdersByShowId(Integer showId) {
        try {
            return new ResponseEntity<>(foodOrderDAO.findByShowId(showId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<FoodOrder>> getFoodOrderByTicketId(Integer ticketId) {
        try {
            return new ResponseEntity<>(Optional.ofNullable(foodOrderDAO.findByTicketId(ticketId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<FoodOrder> placeFoodOrder(FoodOrder foodOrder)
            throws FoodOrderAlreadyExistsException, FoodOrderEmptyException, TicketDoesNotExistException {

        if (foodOrder.getItems() == null || foodOrder.getItems().length == 0)
            throw new FoodOrderEmptyException("The items in your order are empty, please add some items and try again.");

        if (ticketDAO.findById(foodOrder.getTicketId()).isEmpty())
            throw new TicketDoesNotExistException("The Ticket ID supplied is invalid, please check and retry.");

        if (foodOrderDAO.findByTicketId(foodOrder.getTicketId()) == null) {
            foodOrder.setPaid(false);

            int totalValue = 0;
            for (int itemId : foodOrder.getItems()) {
                totalValue += foodItemService.getPriceForItem(itemId);
            }
            foodOrder.setTotalPrice(totalValue);

            try {
                foodOrderDAO.save(foodOrder);
                return new ResponseEntity<>(foodOrder, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new FoodOrderAlreadyExistsException(
                    "A Food Order already exists for this Ticket ID. As per policy, you can only place one order per ticket.");
        }
    }
}
