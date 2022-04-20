package edu.mooncoder.pyramid;

import edu.mooncoder.pyramid.controllers.JsonToDeckSet;
import edu.mooncoder.pyramid.exceptions.ExceptionWithStatus;
import edu.mooncoder.pyramid.model.enums.Order;

public class EntryPoint {
    public static void main(String[] args) throws ExceptionWithStatus {
        JsonToDeckSet.getInstance().addAllCardsToDeck("{\"0\":\"1♥\",\"1\":\"2♥\",\"2\":\"3♥\",\"3\":\"4♥\",\"\":\"}");
        System.out.println(JsonToDeckSet.getInstance().toString(Order.PREORDER));
        System.out.println(JsonToDeckSet.getInstance().toString(Order.INORDER));
        System.out.println(JsonToDeckSet.getInstance().toString(Order.POSTORDER));
    }
}
